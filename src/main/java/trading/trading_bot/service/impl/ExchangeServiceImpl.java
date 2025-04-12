package trading.trading_bot.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trading.trading_bot.entity.Exchange;
import trading.trading_bot.entity.Key;
import trading.trading_bot.model.ExchangeModel;
import trading.trading_bot.model.KeyModel;
import trading.trading_bot.repository.ExchangeRepository;
import trading.trading_bot.repository.KeyRepository;
import trading.trading_bot.repository.UserRepository;
import trading.trading_bot.service.ExchangeServiceInterface;
import trading.trading_bot.service.KeyServiceInterface;

@Service
public class ExchangeServiceImpl implements ExchangeServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    KeyRepository keyRepository;

    @Autowired
    KeyServiceInterface keyService;

    @Override
    public List<ExchangeModel> getExchangeList(UUID userUuid) {

        List<Exchange> exchangeList = exchangeRepository.findAll();

        List<ExchangeModel> exchangeModelList = exchangeList.stream().map(exchange -> {
            KeyModel keyModel = null;
            for (Key key : exchange.getKeys()) {
                if (key != null && key.getUser().getUserUuid().equals(userUuid)) {
                    keyModel = new KeyModel();
                    keyModel.setUserUuid(key.getUser().getUserUuid().toString());
                    keyModel.setExchangeUuid(exchange.getExchangeUuid().toString());
                }
            }
            ExchangeModel exchangeModel = new ExchangeModel(
                    exchange.getExchangeUuid().toString(),
                    exchange.getName(),
                    exchange.getBaseUrl(),
                    exchange.getEnable(),
                    keyModel,
                    null);
            return exchangeModel;
        }).collect(Collectors.toList());

        return exchangeModelList;
    }

    // This method is getting the exchange status (Every exchange)
    // It is not implemented yet
    @Override
    public ExchangeModel getExchangeStatus(UUID userUuid, String exchangeUuid) {
        return new ExchangeModel();
    }
}
