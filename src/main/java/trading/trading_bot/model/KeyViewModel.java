package trading.trading_bot.model;

import lombok.Data;

@Data
public class KeyViewModel {
    private String apiKey;
    private String apiSecret;
    private String exchange;

    public KeyViewModel(String apiKey, String apiSecret, String exchange) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.exchange = exchange;
    }
}
