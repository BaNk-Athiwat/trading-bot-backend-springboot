package trading.trading_bot.service;

import java.util.UUID;

import trading.trading_bot.model.KeyModel;

public interface KeyServiceInterface {

    KeyModel getApiKey(UUID userUuid, UUID exchangeUuid);

    KeyModel insertApiKey(KeyModel keyModel);

    KeyModel updateApiKey(KeyModel keyModel);
}
