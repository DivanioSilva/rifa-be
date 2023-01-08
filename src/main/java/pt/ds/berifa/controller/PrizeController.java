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
import java.util.Date;

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

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PrizeResponseDto> findByCriteriaV2(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) Date createAt,
                                                   @RequestParam(required = false) Date updateAt,
                                                   @RequestParam(required = false) String type,
                                                   @RequestParam(required = false) String description,
                                                   // TODO: 08/01/2023 tenho sempre de adicionar um valor default 
                                                   //@RequestParam(required = false, defaultValue = "0") double price,
                                                   @RequestParam(required = false) String url,
                                                   @RequestParam(required = false, defaultValue = "false") boolean sorteado,
                                                   @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                   @RequestParam(required = false, defaultValue = "30") int pageSize){
        PrizeForQueryingDto dto = PrizeForQueryingDto
                .builder()
                .id(id)
                .updateAt(updateAt)
                .createAt(createAt)
                .type(type)
                .description(description)
                //.price(price)
                .url(url)
                .sorteado(sorteado).build();
        return this.prizeService.findByCriteria(dto, pageNumber, pageSize);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable @Positive long id){
        this.prizeService.delete(id);
    }
}
