package pt.ds.berifa.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bets")
public class Bet extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne
    @JoinColumn(name = "associated_id")
    private Partner associated;
    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bet bet = (Bet) o;
        return getId() != null && Objects.equals(getId(), bet.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
