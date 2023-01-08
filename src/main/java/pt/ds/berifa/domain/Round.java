package pt.ds.berifa.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Table(name = "rounds")
@Entity
public class Round extends BaseEntity{
    @OneToMany(fetch = FetchType.LAZY)
    private List<Prize> prizes;
    private double price;
    private boolean isActive;

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
