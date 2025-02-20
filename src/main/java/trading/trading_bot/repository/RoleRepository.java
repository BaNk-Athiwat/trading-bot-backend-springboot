package trading.trading_bot.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trading.trading_bot.entity.Role;
import trading.trading_bot.entity.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(ERole name);

}
