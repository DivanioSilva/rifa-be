package pt.ds.berifa.dto;

import pt.ds.berifa.domain.Client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Client} entity
 */
public record ClientDto(Long id, Date createAt, Date updateAt, String firstName, String lastName,
                        @NotEmpty(message = "Email is empty")
                        @Email(message = "Malformed email")
                        String email,
                        @NotEmpty(message = "Phone number is empty") String phoneNumber,
                        String nif,
                        boolean isBlock) implements Serializable {
}
