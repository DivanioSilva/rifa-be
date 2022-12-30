package pt.ds.berifa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import pt.ds.berifa.domain.Client;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Client} entity
 */
public record ClientDto(Long id, Date createAt, Date updateAt, String firstName, String lastName,
                        @NotEmpty(message = "Email is empty")
                        @Email(message = "Malformed email")
                        String email,
                        @NotEmpty(message = "Phone number is empty") String phoneNumber, boolean isBlock) implements Serializable {
}
