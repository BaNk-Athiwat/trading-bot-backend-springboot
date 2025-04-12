package trading.trading_bot.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trading.trading_bot.entity.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {

    Optional<Key> findByUser_UserUuidAndExchange_ExchangeUuid(UUID userUuid, UUID exchangeUuid);

}
