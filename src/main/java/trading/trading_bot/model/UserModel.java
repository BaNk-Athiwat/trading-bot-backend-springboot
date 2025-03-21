package trading.trading_bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trading.trading_bot.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public UserModel(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole().getName().toString().replaceFirst("^ROLE_", "").toLowerCase();
    }
}
