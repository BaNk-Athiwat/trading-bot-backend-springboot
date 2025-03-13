package trading.trading_bot.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeModel {
    @JsonIgnore
    UUID excnageUuid;

    String name;
    String baseUrl;
    Boolean enable;
    KeyModel key;
}
