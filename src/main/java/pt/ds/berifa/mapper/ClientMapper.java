package pt.ds.berifa.mapper;

import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pt.ds.berifa.domain.Client;
import pt.ds.berifa.dto.ClientDto;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDto clientDto);

    ClientDto toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientDto clientDto, @MappingTarget Client client);

    List<ClientDto> toDtos(Iterable<Client> clientRepositoryAll);

}
