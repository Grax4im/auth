package ifrs.edu;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class User extends PanacheEntity{

    private String name;
    private String email;
    private String password;
    private String level;

    public static User create(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setLevel("user");
        newUser.persistAndFlush();
        return newUser;
    }

    public static User findByName(String name){
        return find("name", name).firstResult();
    }
}
