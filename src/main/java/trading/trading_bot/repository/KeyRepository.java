package trading.trading_bot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trading.trading_bot.entity.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {

    Key findByUser_UserUuidAndExchange_ExchangeUuid(UUID userUuid, UUID exchangeUuid);

    List<Key> findByUser_UserUuid(UUID userUuid);

}
