package trading.trading_bot.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchanges")
public class Exchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "exchange_uuid", updatable = false, nullable = false)
    private UUID exchangeUuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "base_url", nullable = true)
    private String baseUrl;

    @Column(name = "enable", nullable = false, columnDefinition = "boolean default true")
    private Boolean enable;

    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY)
    private Set<Key> keys;
}
