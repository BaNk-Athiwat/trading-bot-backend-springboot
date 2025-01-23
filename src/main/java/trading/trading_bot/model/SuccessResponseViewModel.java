package trading.trading_bot.model;

import lombok.Data;

@Data
public class SuccessResponseViewModel {
    private String message;

    public SuccessResponseViewModel(String message) {
        this.message = message;
    }
}
