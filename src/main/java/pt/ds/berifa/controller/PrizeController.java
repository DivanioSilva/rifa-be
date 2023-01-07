package pt.ds.berifa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.ds.berifa.dto.PrizeForQueryingDto;
import pt.ds.berifa.dto.PrizeOperationsDto;
import pt.ds.berifa.dto.PrizeResponseDto;
import pt.ds.berifa.service.PrizeService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RequiredArgsConstructor
@RequestMapping(value = "api/prize")
@RestController
public class PrizeController {
    private final PrizeService prizeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PrizeResponseDto save(@Valid @RequestBody PrizeOperationsDto dto){
        return this.prizeService.create(dto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PrizeResponseDto update(@PathVariable @Positive long id, @Valid PrizeOperationsDto dto){
        return this.prizeService.update(id, dto);
    }

    @PutMapping(value = "/{id}/state/{state}")
    public void changeState(@PathVariable @Positive long id, @PathVariable("state") boolean state){
        this.prizeService.changeState(id, state);
    }

    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PrizeResponseDto> findByCriteria(@RequestBody PrizeForQueryingDto dto,
                                                 @RequestParam(required = false) int pageNumber,
                                                 @RequestParam(required = false) int pageSize){
        // TODO: 07/01/2023 not working yet 
        return this.prizeService.findByCriteria(dto, pageNumber, pageSize);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable @Positive long id){
        this.prizeService.delete(id);
    }
}
