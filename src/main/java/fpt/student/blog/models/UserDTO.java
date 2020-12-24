package fpt.student.blog.models;

import fpt.student.blog.entities.Role;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotBlank(message = "name is required!")
    private String name;

    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters!")
    private String password;

}
