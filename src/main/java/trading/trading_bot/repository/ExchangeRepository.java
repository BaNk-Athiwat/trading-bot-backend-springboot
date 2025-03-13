package trading.trading_bot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trading.trading_bot.entity.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, UUID> {

}
