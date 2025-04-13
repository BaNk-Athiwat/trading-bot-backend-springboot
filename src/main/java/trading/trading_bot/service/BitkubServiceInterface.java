package trading.trading_bot.service;

import java.util.UUID;

public interface BitkubServiceInterface {
    public Object getStatus();

    public Object getServerTime();

    public Object getMarketTicker();

    public Object getBalances(UUID userUuid, UUID exchangeUuid);
}
