package pt.ds.berifa.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link pt.ds.berifa.domain.Round} entity
 */
public record RoundResponseDto(Long id, Date createAt, Date updateAt, List<PrizeResponseDto> prizes, double price,
                               boolean isActive) implements Serializable {
}
