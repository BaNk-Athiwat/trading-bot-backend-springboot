package trading.trading_bot.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trading.trading_bot.entity.Exchange;
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
    public List<ExchangeModel> getAllExchangeConnection(UUID userUuid) {

        List<KeyModel> keyModelList = keyService.getKeysByUserUuid(userUuid);

        List<ExchangeModel> exchangeModelList = keyModelList.stream().map(keyModel -> {

            Exchange exchangeEn = exchangeRepository.findById(UUID.fromString(keyModel.getExchangeUuid()))
                    .orElseThrow(() -> new RuntimeException(
                            "Exchange Not Found with exchangeUuid: " + keyModel.getExchangeUuid()));

            ExchangeModel exchangeModel = new ExchangeModel(
                    exchangeEn.getExchangeUuid().toString(),
                    exchangeEn.getName(),
                    exchangeEn.getBaseUrl(),
                    exchangeEn.getEnable(),
                    keyModel,
                    null);
            return exchangeModel;
        }).collect(Collectors.toList());

        return exchangeModelList;
    }
}
