package pt.ds.berifa.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "winners")
public class Winner extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "round_id")
    private Round round;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Winner winner = (Winner) o;
        return getId() != null && Objects.equals(getId(), winner.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
