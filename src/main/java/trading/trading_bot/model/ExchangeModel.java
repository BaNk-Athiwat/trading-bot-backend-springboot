package trading.trading_bot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeModel {

    String exchangeUuid;
    String name;
    String baseUrl;
    Boolean enable;
    KeyModel key;
    List<KeyModel> keyList;
}
