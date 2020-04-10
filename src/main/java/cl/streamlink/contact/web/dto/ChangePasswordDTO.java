package cl.streamlink.contact.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordDTO {

    @NotNull
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String oldPassword;

    @NotNull
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
