package trading.trading_bot.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseModel {
    private Integer status;
    private String message;
    private Object data;
}
