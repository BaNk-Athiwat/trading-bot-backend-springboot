package trading.trading_bot.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
