package fpt.student.blog.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {
    @NotBlank(message = "name is required!")
    @Size(max = 255, message = "Length is over limited!")
    private String name;

    @NotBlank(message = "password is required!")
    @Size(max = 255, message = "Length is over limited!")
    private String password;
}
