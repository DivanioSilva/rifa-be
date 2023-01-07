package pt.ds.berifa.service;

import org.springframework.data.domain.Page;
import pt.ds.berifa.dto.PrizeForQueryingDto;
import pt.ds.berifa.dto.PrizeOperationsDto;
import pt.ds.berifa.dto.PrizeResponseDto;

public interface PrizeService {
    PrizeResponseDto create(PrizeOperationsDto dto);
    PrizeResponseDto update(long id, PrizeOperationsDto dto);
    void changeState(long id, boolean isSorteado);
    void delete(long id);
    Page<PrizeResponseDto> findByCriteria(PrizeForQueryingDto dto, int pageNumber, int pageSize);
}
