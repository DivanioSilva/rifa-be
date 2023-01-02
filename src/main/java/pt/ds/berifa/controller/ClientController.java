package pt.ds.berifa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.ds.berifa.dto.ClientDto;
import pt.ds.berifa.service.ClientService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "api/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto save(@Valid @RequestBody ClientDto clientDto){
        return this.clientService.save(clientDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto update(@PathVariable @Positive long id, @Valid @RequestBody ClientDto clientDto){
        return this.clientService.update(id, clientDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDto> findByCriteria(@RequestParam(required = false) String firstName,
                                          @RequestParam(required = false) String lastName,
                                          @RequestParam(required = false) String email){
        return this.clientService.findByDynamicQuery(firstName, lastName, email);
    }

}
