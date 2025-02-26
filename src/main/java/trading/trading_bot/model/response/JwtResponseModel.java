package trading.trading_bot.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponseModel {
    private String token;
    private String type = "Bearer";

    public JwtResponseModel(String token) {
        this.token = token;
    }
}
