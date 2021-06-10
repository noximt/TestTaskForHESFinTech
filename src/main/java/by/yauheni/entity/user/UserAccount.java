package by.yauheni.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public UserAccount(String username, String password, String firstName, String lastName, Role role, Status status) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
    }
}
