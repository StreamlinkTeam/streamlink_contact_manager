package cl.streamlink.contact.web;

import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.UserDTO;
import io.swagger.annotations.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("ws/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public JSONObject login(//
                            @ApiParam("Username") @RequestParam String username, //
                            @ApiParam("Password") @RequestParam String password) {

        return userService.signin(username, password);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO signup(@ApiParam("Signup User") @RequestBody UserDTO user) {
        return userService.signup(user);
    }


    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.search}", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO search(@ApiParam("Username") @PathVariable String username) {
        return userService.search(username);
    }

    @GetMapping(value = "/current")
    @ApiOperation(value = "${UserController.me}", response = UserDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO whoami(HttpServletRequest req) {

        return userService.whoami(req);
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
    public JSONObject changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        return userService.changeCurrentUserPassword(oldPassword, newPassword);
    }

}
