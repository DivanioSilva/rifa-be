package pt.ds.berifa.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link pt.ds.berifa.domain.Prize} entity
 */
@Builder
public record PrizeForQueryingDto(Long id, Date createAt, Date updateAt, String type, String description, double price,
                                  String url, boolean sorteado, byte[] imageData) implements Serializable {
}
