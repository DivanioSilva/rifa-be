package pt.ds.berifa.service;

import pt.ds.berifa.dto.PrizeOperationsDto;
import pt.ds.berifa.dto.PrizeResponseDto;

public interface PrizeService {
    PrizeResponseDto create(PrizeOperationsDto dto);
    PrizeResponseDto update(long id, PrizeOperationsDto dto);
    void changeState(long id, boolean isSorteado);
    void delete(long id);
}
