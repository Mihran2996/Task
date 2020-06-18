package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private UserType type;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User(UserType type,String name, String surname, String email, String password) {
        this.type=type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }


}
