package trading.trading_bot.service;

import java.util.List;
import java.util.UUID;

import trading.trading_bot.entity.Key;
import trading.trading_bot.model.KeyModel;

public interface KeyServiceInterface {

    KeyModel getKey();

    List<Key> getKeysByUserUuid(UUID userUuid);
}
