package pt.ds.berifa.service;

import pt.ds.berifa.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto clientDto);
    ClientDto update(long clientId, ClientDto clientDto);
    List<ClientDto> findByDynamicQuery(String firstName, String lastName, String email);
    void block(long clientId);
    void unblock(long clientId);
    boolean isBlocked(long clientId);

}
