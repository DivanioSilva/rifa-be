package pt.ds.berifa.service;

import org.springframework.data.domain.Page;
import pt.ds.berifa.dto.ClientDto;
import pt.ds.berifa.dto.ClientForQueryingDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto clientDto);
    ClientDto update(long clientId, ClientDto clientDto);
    List<ClientDto> findByDynamicQuery(String firstName, String lastName, String email);
    Page<ClientDto> findByDynamicQuery(ClientForQueryingDto dto, int pageNumber, int pageSize);
    void block(long clientId);
    void unblock(long clientId);
    boolean isBlocked(long clientId);

}
