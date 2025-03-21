package trading.trading_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyModel {

    @JsonIgnore
    String keyUuid;

    String userUuid;
    String exchangeUuid;
    String apiKey;
    String secretKey;
}
