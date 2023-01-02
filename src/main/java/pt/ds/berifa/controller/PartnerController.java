package pt.ds.berifa.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ds.berifa.dto.PartnerCreateDto;
import pt.ds.berifa.dto.PartnerDtoResponse;
import pt.ds.berifa.service.PartnerService;

@RequestMapping(name = "partner")
@RestController
public class PartnerController {
    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PartnerDtoResponse create(@RequestBody PartnerCreateDto dto){
        return this.partnerService.create(dto);
    }


}
