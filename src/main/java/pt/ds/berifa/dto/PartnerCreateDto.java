package pt.ds.berifa.dto;

import pt.ds.berifa.domain.Partner;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Partner} entity
 */
public record PartnerCreateDto(@NotNull @NotEmpty String firstName,
                               @NotNull @NotEmpty
                               String lastName,
                               @NotEmpty(message = "Email is empty")
                               @Email(message = "Malformed email")
                               String email,
                               @NotNull
                               @NotEmpty(message = "Phone number is empty")
                               String phoneNumber,
                               long nif,
                               String address) implements Serializable {
}
