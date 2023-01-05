package pt.ds.berifa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.ds.berifa.dto.*;
import pt.ds.berifa.service.PartnerService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@Validated
@RequestMapping(value = "api/partner")
@RestController
public class PartnerController {
    private final PartnerService partnerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PartnerDtoResponse create(@Valid @RequestBody PartnerCreateDto dto){
        return this.partnerService.create(dto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PartnerDtoResponse update(@PathVariable @Positive long id, @Valid @RequestBody PartnerUpdateDto dto){
        return this.partnerService.update(id, dto);
    }

    @PostMapping(value = "/search",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PartnerDtoResponse> findByCriteria(@RequestBody PartnerForQueryingDto dto,
                                          @RequestParam(required = false) int pageNumber,
                                          @RequestParam(required = false) int pageSize){
        return this.partnerService.findByDynamicQuery(dto, pageNumber, pageSize);
    }

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PartnerDtoResponse> findAll(@RequestParam(required = false) int pageNumber,
                                            @RequestParam(required = false) int pageSize){
        return this.partnerService.findAll(pageNumber, pageSize);
    }

}
