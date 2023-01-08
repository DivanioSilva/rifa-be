package pt.ds.berifa.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link pt.ds.berifa.domain.Round} entity
 */
@Builder
public record RoundForQueryingDto(Long id, Date createAt, Date updateAt, List<Long> prizeIds, double price,
                                  boolean isActive) implements Serializable {
}
