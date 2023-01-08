package pt.ds.berifa.mapper;

import org.mapstruct.*;
import pt.ds.berifa.domain.Prize;
import pt.ds.berifa.domain.Round;
import pt.ds.berifa.dto.RoundResponseDto;
import pt.ds.berifa.dto.RoundForQueryingDto;
import pt.ds.berifa.dto.RoundOperationsDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {PrizeMapper.class})
public interface RoundMapper {
    Round toEntity(RoundOperationsDto roundOperationsDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Round partialUpdate(RoundOperationsDto roundOperationsDto, @MappingTarget Round round);

    Round toEntity(RoundResponseDto roundResponseDto);

    RoundResponseDto toDto(Round round);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Round partialUpdate(RoundResponseDto roundResponseDto, @MappingTarget Round round);

    RoundForQueryingDto toDto1(Round round);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Round partialUpdate1(RoundForQueryingDto roundForQueryingDto, @MappingTarget Round round);

    default List<Long> prizesToPrizeIds1(List<Prize> prizes) {
        return prizes.stream().map(Prize::getId).collect(Collectors.toList());
    }
}
