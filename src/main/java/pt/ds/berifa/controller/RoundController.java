package pt.ds.berifa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.ds.berifa.dto.*;
import pt.ds.berifa.service.RoundService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "api/rounds")
@RestController
public class RoundController {
    private final RoundService roundService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RoundResponseDto save(@Valid @RequestBody RoundOperationsDto dto){
        return this.roundService.create(dto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RoundResponseDto update(@PathVariable @Positive long id, @Valid RoundOperationsDto dto){
        return this.roundService.update(id, dto);
    }

    @PutMapping(value = "/{id}/state/{state}")
    public void changeState(@PathVariable @Positive long id, @PathVariable("state") boolean state){
        this.roundService.changeState(id, state);
    }

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RoundResponseDto> findByCriteriaV2(@RequestParam(required = false)Long id,
                                                   @RequestParam(required = false)Date createAt,
                                                   @RequestParam(required = false)Date updateAt,
                                                   @RequestParam(required = false)List<Long> prizeIds,
                                                   @RequestParam(required = false, defaultValue = "0")double price,
                                                   @RequestParam(required = false, defaultValue = "false")boolean isActive,
                                                   @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                   @RequestParam(required = false, defaultValue = "30") int pageSize){
        RoundForQueryingDto dto = RoundForQueryingDto
                .builder()
                .isActive(isActive)
                .prizeIds(prizeIds)
                .createAt(createAt)
                .updateAt(updateAt)
                .id(id)
                .price(price)
                .build();
        return this.roundService.searchByCriteria(dto, pageNumber, pageSize);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable @Positive long id){
        //this.roundService.delete(id);
    }
}
