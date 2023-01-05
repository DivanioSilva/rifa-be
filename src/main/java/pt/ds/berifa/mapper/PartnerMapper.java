package pt.ds.berifa.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import pt.ds.berifa.domain.Partner;
import pt.ds.berifa.dto.PartnerDtoResponse;
import pt.ds.berifa.dto.PartnerCreateDto;
import pt.ds.berifa.dto.PartnerUpdateDto;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PartnerMapper {
    Partner toEntity(PartnerCreateDto partnerCreateDto);

    PartnerCreateDto toDto(Partner partner);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Partner partialUpdate(PartnerCreateDto partnerCreateDto, @MappingTarget Partner partner);


    PartnerDtoResponse toDtoResponse(Partner associated);

    Partner partnerUpdateDtoToEntity(PartnerUpdateDto partnerUpdateDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Partner partialUpdate(PartnerUpdateDto partnerUpdateDto, @MappingTarget Partner partner);

    PartnerUpdateDto toDto1(Partner partner);
}
