package pt.ds.berifa.dto;

import pt.ds.berifa.domain.Prize;

import java.io.Serializable;

/**
 * A DTO for the {@link Prize} entity
 */
public record PrizeOperationsDto(String type,
                                 String description,
                                 double price,
                                 String url,
                                 boolean sorteado,
                                 byte[] imageData) implements Serializable {
}
