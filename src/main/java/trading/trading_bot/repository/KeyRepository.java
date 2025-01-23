package trading.trading_bot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trading.trading_bot.entity.Key;

public interface KeyRepository extends JpaRepository<Key, UUID> {

}
