package trading.trading_bot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trading.trading_bot.entity.Exchange;
import trading.trading_bot.entity.Key;
import trading.trading_bot.model.ExchangeModel;
import trading.trading_bot.model.KeyModel;
import trading.trading_bot.repository.ExchangeRepository;
import trading.trading_bot.service.ExchangeServiceInterface;
import trading.trading_bot.service.KeyServiceInterface;

@Service
public class ExchangeServiceImpl implements ExchangeServiceInterface {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    KeyServiceInterface keyService;

    @Override
    public List<ExchangeModel> getAllConnection(UUID userUuid) {
        List<Key> keyEnList = keyService.getKeysByUserUuid(userUuid);
        List<ExchangeModel> exchangeModelList = new ArrayList<>();

        keyEnList.forEach(key -> {
            UUID keyUuid = key.getKeyUuid();
            String apiKey = key.getApiKey();
            String secretKey = key.getSecretKey();
            Exchange exchange = key.getExchange();

            UUID exchangeUuid = exchange.getExchangeUuid();
            String name = exchange.getName();
            String baseUrl = exchange.getBaseUrl();
            Boolean enable = exchange.getEnable();

            KeyModel keyModel = new KeyModel(keyUuid, apiKey, secretKey);

            ExchangeModel exchangeModel = new ExchangeModel(
                    exchangeUuid,
                    name,
                    baseUrl,
                    enable,
                    keyModel);

            exchangeModelList.add(exchangeModel);
        });

        return exchangeModelList;
    }
}
