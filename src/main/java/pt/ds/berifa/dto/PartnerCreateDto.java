package pt.ds.berifa.dto;

import org.hibernate.validator.constraints.Length;
import pt.ds.berifa.domain.Partner;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Partner} entity
 */
public record PartnerCreateDto(@NotNull @NotEmpty String firstName,
                               @NotNull @NotEmpty
                               String lastName,
                               @NotNull @NotEmpty
                               String email,
                               @NotNull @NotEmpty
                               String phoneNumber,
                               @Size()
                               long nif,
                               String address) implements Serializable {
}
