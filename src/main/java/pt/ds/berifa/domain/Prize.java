package pt.ds.berifa.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "prizes")
public class Prize extends BaseEntity{
    private String type;
    private String description;
    private double price;
    private String url;
    private boolean sorteado; // TODO: 08/01/2023 tenho dúvidas se esse campo não tem de estar no round
    @Lob
    private byte[] imageData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Prize prize = (Prize) o;
        return getId() != null && Objects.equals(getId(), prize.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
