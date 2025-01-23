package trading.trading_bot.service;

import trading.trading_bot.model.KeyViewModel;

public interface TradingServiceInterface {

    Boolean createConnection(KeyViewModel req);

    String getStatusData();

    String getMarketTicker();

    String getServerTimeData();

    String getBalancesData();

    String getWalletData();
}
