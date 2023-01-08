package pt.ds.berifa.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link pt.ds.berifa.domain.Round} entity
 */
public record RoundOperationsDto(double price, boolean isActive) implements Serializable {
}
