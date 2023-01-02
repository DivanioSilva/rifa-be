package pt.ds.berifa.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link pt.ds.berifa.domain.Client} entity
 */
public record ClientForQueryingDto(Long id, Date createAt, Date updateAt, String firstName, String lastName,
                                   String email, String phoneNumber, boolean isBlock, long nif) implements Serializable {
}
