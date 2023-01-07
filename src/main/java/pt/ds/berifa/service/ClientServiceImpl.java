package pt.ds.berifa.service;

import com.querydsl.core.BooleanBuilder;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.ds.berifa.domain.Client;
import pt.ds.berifa.domain.QClient;
import pt.ds.berifa.dto.ClientDto;
import pt.ds.berifa.dto.ClientForQueryingDto;
import pt.ds.berifa.exceptions.EntityAlreadyExistsException;
import pt.ds.berifa.exceptions.EntityNotFoundException;
import pt.ds.berifa.factory.DynamicQueriesFactory;
import pt.ds.berifa.mapper.ClientMapper;
import pt.ds.berifa.repository.ClientRepository;

import java.util.List;
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
            throw new EntityAlreadyExistsException("Client already exists");
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
    public List<ClientDto> findByDynamicQuery(String firstName, String lastName, String email) {
        BooleanBuilder expression = new BooleanBuilder();
        if(!StringUtils.isBlank(firstName)){
            expression.and(QClient.client.firstName.eq(firstName));
        }
        if(!StringUtils.isBlank(lastName)){
            expression.and(QClient.client.lastName.eq(lastName));
        }
        if(!StringUtils.isBlank(email)){
            expression.and(QClient.client.email.eq(email));
        }

        Iterable<Client> clientRepositoryAll = this.clientRepository.findAll(expression);
        return clientMapper.toDtos(clientRepositoryAll);
    }

    @Override
    public Page<ClientDto> findByDynamicQuery(ClientForQueryingDto dto, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final BooleanBuilder query = DynamicQueriesFactory.generateDynamicQuery(dto);
        return this.clientRepository.findAll(query, pageable).map(clientMapper::toDto);
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
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    @SneakyThrows
    private Client clientExists(long clientId) {
        return this.clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }
}
