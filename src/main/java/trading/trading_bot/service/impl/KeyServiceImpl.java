package trading.trading_bot.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        // KeyModel keyModel = new KeyModel(
        // key.getKeyUuid(),
        // key.getExchange().getName(),
        // key.getApiKey(),
        // key.getSecretKey());
        KeyModel keyModel = new KeyModel();
        return keyModel;
    }

    @Override
    public List<KeyModel> getKeysByUserUuid(UUID userUuid) {
        List<Key> keyEnList = keyRepository.findByUser_UserUuid(userUuid);
        List<KeyModel> keyModelList = keyEnList.stream().map(keyEn -> {
            KeyModel keyModel = new KeyModel(
                    keyEn.getKeyUuid().toString(),
                    keyEn.getUser().getUserUuid().toString(),
                    keyEn.getExchange().getExchangeUuid().toString(),
                    keyEn.getApiKey(),
                    keyEn.getSecretKey());
            return keyModel;
        }).collect(Collectors.toList());

        return keyModelList;

    }
}
