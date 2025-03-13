package trading.trading_bot.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import trading.trading_bot.entity.Key;
import trading.trading_bot.model.KeyModel;
import trading.trading_bot.repository.KeyRepository;
import trading.trading_bot.security.UserDetailsImpl;
import trading.trading_bot.service.KeyServiceInterface;

@Service
public class KeyServiceImpl implements KeyServiceInterface {

    @Autowired
    KeyRepository keyRepository;

    @Override
    public KeyModel getKey() {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UUID userUuid = userDetailsImpl.getUserUuid();
        UUID exchangeUuid = UUID.fromString("a");
        Key key = keyRepository.findByUser_UserUuidAndExchange_ExchangeUuid(userUuid, exchangeUuid);
        KeyModel keyModel = new KeyModel(
                key.getKeyUuid(),
                key.getExchange().getName(),
                key.getApiKey(),
                key.getSecretKey());
        return keyModel;
    }

    @Override
    public List<Key> getKeysByUserUuid(UUID userUuid) {
        return keyRepository.findByUser_UserUuid(userUuid);

    }
}
