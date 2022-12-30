package pt.ds.berifa.domain;


import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date createAt;
    private Date updateAt;

    public Date getCreateAt() {
        return createAt;
    }

    @PrePersist
    private void setCreateAt() {
        this.createAt = new Date();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    @PreUpdate
    private void setUpdateAt() {
        this.updateAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
