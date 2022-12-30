package pt.ds.berifa.controller;

import jakarta.validation.constraints.Positive;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.ds.berifa.dto.ClientDto;
import pt.ds.berifa.service.ClientService;


@Validated
@RestController
@RequestMapping(value = "api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto save(@RequestBody ClientDto clientDto){
        return this.clientService.save(clientDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto update(@PathVariable @Positive long id, @RequestBody ClientDto clientDto){
        return this.clientService.update(id, clientDto);
    }

}
