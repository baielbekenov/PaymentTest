package kg.angryelizar.paymenttest.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@Table(name = "accounts")
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    @ToString.Exclude
    private List<Transaction> transactions;
}
