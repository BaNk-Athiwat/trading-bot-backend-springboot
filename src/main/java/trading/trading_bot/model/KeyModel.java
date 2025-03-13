package trading.trading_bot.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyModel {

    @JsonIgnore
    UUID keyUuid;

    String exchange;
    String apiKey;
    String secretKey;

    public KeyModel(UUID keyUuid, String apiKey, String secretKey) {
        this.keyUuid = keyUuid;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }
}
