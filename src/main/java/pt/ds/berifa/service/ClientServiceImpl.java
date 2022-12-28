package pt.ds.berifa.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pt.ds.berifa.domain.Client;
import pt.ds.berifa.dto.ClientDto;
import pt.ds.berifa.exceptions.ClientAlreadyExistsException;
import pt.ds.berifa.exceptions.ClientNotFoundException;
import pt.ds.berifa.mapper.ClientMapper;
import pt.ds.berifa.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @SneakyThrows
    @Override
    public ClientDto save(final ClientDto dto) {
        Optional<Client> optionalClientSearchResult = this.clientRepository
                .findClient(dto.firstName(), dto.lastName(), dto.email());

        if(optionalClientSearchResult.isPresent()) {
            throw new ClientAlreadyExistsException("Client already exists");
        }

        final Client client = this.clientMapper.toEntity(dto);
        final Client save = this.clientRepository.save(client);
        return this.clientMapper.toDto(save);
    }

    @SneakyThrows
    @Override
    public ClientDto update(long clientId, ClientDto dto) {
        Client client = this.clientExists(dto);
        this.clientMapper.partialUpdate(dto, client);
        Client save = this.clientRepository.save(client);
        return clientMapper.toDto(save);
    }

    @Override
    public ClientDto findByDynamicQuery(ClientDto clientDto) {
        BooleanExpression expression = Expressions.asBoolean(true);

        return null;
    }

    @Override
    public void block(long clientId) {
        Client client = this.clientExists(clientId);
        client.setBlock(true);
        this.clientRepository.save(client);
    }

    @Override
    public void unblock(long clientId) {
        Client client = this.clientExists(clientId);
        client.setBlock(false);
        this.clientRepository.save(client);
    }

    @Override
    public boolean isBlocked(long clientId) {
        return this.clientExists(clientId).isBlock();
    }

    @SneakyThrows
    private Client clientExists(ClientDto dto) {
        return this.clientRepository.findClient(dto.firstName(), dto.lastName(), dto.email())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    @SneakyThrows
    private Client clientExists(long clientId) {
        return this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }
}
