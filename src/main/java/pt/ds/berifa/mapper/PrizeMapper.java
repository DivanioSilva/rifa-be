package pt.ds.berifa.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import pt.ds.berifa.domain.Prize;
import pt.ds.berifa.dto.PrizeForQueryingDto;
import pt.ds.berifa.dto.PrizeResponseDto;
import pt.ds.berifa.dto.PrizeOperationsDto;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PrizeMapper {
    Prize toEntity(PrizeOperationsDto prizeOperationsDto);

    PrizeOperationsDto toDto(Prize prize);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Prize partialUpdate(PrizeOperationsDto prizeOperationsDto, @MappingTarget Prize prize);

    PrizeResponseDto toResponseDto(Prize prize);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Prize partialUpdate(PrizeResponseDto prizeResponseDto, @MappingTarget Prize prize);

}
