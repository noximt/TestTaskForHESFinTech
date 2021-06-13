package by.yauheni.dto;

import by.yauheni.entity.Role;
import by.yauheni.entity.Status;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NewUserDTO {
    private long id;

    @NotBlank(message = "Required field")
    @NotEmpty(message = "Required field")
    @Size(min = 3, max = 16, message = "Min size 3, max size 16")
    @Pattern(regexp = "[A-z]+",message = "Must contains only latin symbols")
    private String username;

    @NotBlank(message = "Required field")
    @NotEmpty(message = "Required field")
    @Size(min = 3, max = 16, message = "Min size 3, max size 16")
    @Pattern(regexp = "([0-9A-z]+)", message = "Must contains latin latters and numbers")
    private String password;

    @NotBlank(message = "Required field")
    @NotEmpty(message = "Required field")
    @Pattern(regexp = "[A-z]+",message = "Must contains only latin symbols")
    private String firstName;

    @NotBlank(message = "Required field")
    @NotEmpty(message = "Required field")
    @Pattern(regexp = "[A-z]+",message = "Must contains only latin symbols")
    private String lastName;

    private Role role;
    private Status state;
}
