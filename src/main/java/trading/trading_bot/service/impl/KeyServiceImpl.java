package trading.trading_bot.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trading.trading_bot.entity.Exchange;
import trading.trading_bot.entity.Key;
import trading.trading_bot.entity.User;
import trading.trading_bot.exception.ResourceNotFoundException;
import trading.trading_bot.model.KeyModel;
import trading.trading_bot.repository.ExchangeRepository;
import trading.trading_bot.repository.KeyRepository;
import trading.trading_bot.repository.UserRepository;
import trading.trading_bot.service.KeyServiceInterface;

@Service
public class KeyServiceImpl implements KeyServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    KeyRepository keyRepository;

    @Override
    public KeyModel getApiKey(UUID userUuid, UUID exchangeUuid) {
        Optional<Key> optKey = keyRepository.findByUser_UserUuidAndExchange_ExchangeUuid(
                userUuid,
                exchangeUuid);

        if (!optKey.isPresent()) {
            return null;
        }

        Key keyEn = optKey.get();
        return new KeyModel(
                keyEn.getKeyUuid().toString(),
                keyEn.getUser().getUserUuid().toString(),
                keyEn.getExchange().getExchangeUuid().toString(),
                keyEn.getApiKey(),
                keyEn.getSecretKey());
    }

    @Override
    public KeyModel insertApiKey(KeyModel keyModel) {
        User userEn = userRepository.getReferenceById(
                UUID.fromString(keyModel.getUserUuid()));
        Exchange exchangeEn = exchangeRepository.getReferenceById(
                UUID.fromString(keyModel.getExchangeUuid()));

        Key keyEn = new Key();
        keyEn.setApiKey(keyModel.getApiKey());
        keyEn.setSecretKey(keyModel.getSecretKey());
        keyEn.setExchange(exchangeEn);
        keyEn.setUser(userEn);
        keyRepository.save(keyEn);
        return null;
    }

    @Override
    public KeyModel updateApiKey(KeyModel keyModel) {
        Optional<Key> optKey = keyRepository.findByUser_UserUuidAndExchange_ExchangeUuid(
                UUID.fromString(keyModel.getUserUuid()),
                UUID.fromString(keyModel.getExchangeUuid()));

        if (!optKey.isPresent()) {
            throw new ResourceNotFoundException("Api Key not found");
        }

        Key keyEn = optKey.get();
        keyEn.setApiKey(keyModel.getApiKey());
        keyEn.setSecretKey(keyModel.getSecretKey());
        keyRepository.save(keyEn);
        return null;
    }
}
