package trading.trading_bot.service;

import java.util.List;
import java.util.UUID;

import trading.trading_bot.model.ExchangeModel;

public interface ExchangeServiceInterface {

    public List<ExchangeModel> getAllConnection(UUID userUuid);

}
