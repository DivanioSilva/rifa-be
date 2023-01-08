package pt.ds.berifa.service;

import org.springframework.data.domain.Page;
import pt.ds.berifa.dto.RoundResponseDto;
import pt.ds.berifa.dto.RoundForQueryingDto;
import pt.ds.berifa.dto.RoundOperationsDto;

import java.util.List;

public interface RoundService {
    RoundResponseDto create(RoundOperationsDto dto);
    RoundResponseDto update(long id, RoundOperationsDto dto);
    RoundResponseDto addPrizes(long roundId, List<Long> prizesIds);
    boolean isActive(long roundId);
    void changeState(long roundId, boolean isActive);
    Page<RoundResponseDto> searchByCriteria(RoundForQueryingDto dto, int pageNumber, int pageSize);

}
