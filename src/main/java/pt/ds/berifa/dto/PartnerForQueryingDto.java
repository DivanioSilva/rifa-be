package pt.ds.berifa.dto;

import pt.ds.berifa.domain.Partner;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Partner} entity
 */
public record PartnerForQueryingDto(Long id,
                                    Date createAt,
                                    Date updateAt,
                                    String firstName,
                                    String lastName,
                                    String email,
                                    String phoneNumber,
                                    boolean isBlock,
                                    long nif,
                                    String address) implements Serializable {
}
