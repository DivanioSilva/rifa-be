package pt.ds.berifa.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link pt.ds.berifa.domain.Partner} entity
 */
public record PartnerUpdateDto(Long id, String firstName, String lastName, String email, String phoneNumber, long nif,
                               String address) implements Serializable {
}
