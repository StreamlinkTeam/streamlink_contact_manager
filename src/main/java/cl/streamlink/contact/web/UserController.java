package cl.streamlink.contact.web;

import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.security.ContactUserDetails;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.Constants;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.AvatarDTO;
import cl.streamlink.contact.web.dto.ChangePasswordDTO;
import cl.streamlink.contact.web.dto.UserDTO;
import io.swagger.annotations.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("ws/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/login")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public JSONObject login(@RequestBody JSONObject user) throws ContactApiException {

        return userService.signIn(user.get("username").toString(), user.get("password").toString());
    }

    @PostMapping("")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO signup(@ApiParam("Signup User") @RequestBody UserDTO user) throws ContactApiException {
        return userService.signUp(user);
    }


    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + Constants.ROLE_ADMIN + "')")
    @ApiOperation(value = "Create User Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public UserDTO updateUser(@Valid @RequestBody UserDTO user, @RequestParam(value = "userReference") String userReference) throws ContactApiException {

        return userService.updateUser(user, userReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + Constants.ROLE_ADMIN + "')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = JSONObject.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public JSONObject deleteUser(@RequestParam("userReference") String userReference) throws ContactApiException {

        return userService.deleteUser(userReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get User Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public UserDTO getUser(@RequestParam(value = "userReference") String userReference) throws ContactApiException {
        return userService.getUser(userReference);
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "Get All Users", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "/current")
    @ApiOperation(value = "${UserController.me}", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> whoami() throws ContactApiException {

        ContactUserDetails current = userService.getCurrentUser();

        if (current.isResource())
            return ResponseEntity.ok(resourceService.getResourceByEmail(current.getUsername()));
        else
            return ResponseEntity.ok(userService.getUser(current.getReference()));

    }

    @PutMapping(value = "current/password",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Change Password Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully"),
            @ApiResponse(code = 400, message = "Password Don't Match"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public JSONObject changePassword(@RequestBody @Valid ChangePasswordDTO passwordDTO) throws ContactApiException {
        return userService.changeCurrentUserPassword(passwordDTO);
    }

    @PutMapping(value = "current/avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AvatarDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public AvatarDTO addUserAvatar(@RequestPart(value = "avatar") MultipartFile avatar) throws IOException, ContactApiException {

        return userService.addCurrentUserAvatar(avatar);
    }

    @DeleteMapping(value = "current/avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = JSONObject.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeUserAvatar(@RequestParam String reference) throws ContactApiException {

        return userService.removeCurrentUserAvatar(reference);
    }

    @GetMapping(value = "current/avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Users avatars")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AvatarDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public AvatarDTO getUserAvatar() throws ContactApiException {
        return userService.getCurrentUserAvatar();
    }


    @PutMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AvatarDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public AvatarDTO addUserAvatar(@RequestParam String userReference, @RequestPart(value = "avatar") MultipartFile avatar) throws IOException, ContactApiException {

        return userService.addUserAvatar(avatar, userReference);
    }

    @DeleteMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = JSONObject.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeUserAvatar(@RequestParam String reference, @RequestParam String userReference) throws ContactApiException {

        return userService.removeUserAvatar(reference, userReference);
    }

    @GetMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Users avatars")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AvatarDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public AvatarDTO getUserAvatar(@RequestParam String userReference) throws ContactApiException {
        return userService.getUserAvatar(userReference);
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<UserDTO> getUsers(Pageable pageable, @RequestParam boolean fromAngular,
                                  @RequestParam(required = false) String value,
                                  @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);

        }

        return userService.searchUsers(value, pageable);

    }

    @GetMapping(value = "count")
    public long usersCount(){
        return userService.usersCount();
    }


    @PutMapping(value = "/password")
    public JSONObject changeUserPassword(
            @RequestParam("userReference") String userReference,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword) throws ContactApiException {
        return userService.changeUserPassword(userReference, oldPassword, newPassword);
    }

}
