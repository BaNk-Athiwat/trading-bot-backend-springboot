package trading.trading_bot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeModel {

    @JsonIgnore
    String excnageUuid;

    String name;
    String baseUrl;
    Boolean enable;
    KeyModel key;
    List<KeyModel> keyList;
}
