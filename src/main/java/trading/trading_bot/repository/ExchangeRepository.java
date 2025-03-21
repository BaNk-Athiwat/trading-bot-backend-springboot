package trading.trading_bot.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import trading.trading_bot.entity.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, UUID> {

    @NonNull
    Optional<Exchange> findById(@NonNull UUID exchangeUuid);

}
