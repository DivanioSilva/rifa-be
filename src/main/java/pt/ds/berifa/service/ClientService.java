package pt.ds.berifa.service;

import pt.ds.berifa.dto.ClientDto;

public interface ClientService {
    ClientDto save(ClientDto clientDto);
    ClientDto update(long clientId, ClientDto clientDto);
    ClientDto findByDynamicQuery(ClientDto clientDto);
    void block(long clientId);
    void unblock(long clientId);
    boolean isBlocked(long clientId);

}
