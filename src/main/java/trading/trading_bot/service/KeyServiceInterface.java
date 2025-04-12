package trading.trading_bot.service;

import java.util.UUID;

import trading.trading_bot.model.KeyModel;

public interface KeyServiceInterface {

    KeyModel getApiKey(UUID userUuid, UUID exchangeUuid);

    KeyModel insertApiKey(UUID userUuid, KeyModel keyModel);

    KeyModel updateApiKey(UUID userUuid, KeyModel keyModel);
}
