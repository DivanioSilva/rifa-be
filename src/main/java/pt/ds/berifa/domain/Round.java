package pt.ds.berifa.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Round extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "winner_id")
    private Winner winner;
    private double price;
    @OneToMany
    private List<Bet> bets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Round round = (Round) o;
        return getId() != null && Objects.equals(getId(), round.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
