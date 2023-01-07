package pt.ds.berifa.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.ds.berifa.domain.Prize;
import pt.ds.berifa.dto.PrizeForQueryingDto;
import pt.ds.berifa.dto.PrizeOperationsDto;
import pt.ds.berifa.dto.PrizeResponseDto;
import pt.ds.berifa.exceptions.EntityAlreadyExistsException;
import pt.ds.berifa.factory.DynamicQueriesFactory;
import pt.ds.berifa.mapper.PrizeMapper;
import pt.ds.berifa.repository.PrizeRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrizeServiceImpl implements PrizeService{
    private final PrizeMapper mapper;
    private final PrizeRepository repository;
    @Override
    public PrizeResponseDto create(PrizeOperationsDto dto) {
        final Prize prize = this.mapper.toEntity(dto);
        final Prize saved = this.repository.save(prize);
        return this.mapper.toResponseDto(saved);
    }

    @SneakyThrows
    @Override
    public PrizeResponseDto update(long id, PrizeOperationsDto dto) {
        final Optional<Prize> optionalPrize = this.repository.findById(id);
        if(optionalPrize.isEmpty()) {
            throw new EntityAlreadyExistsException("Prize not exists");
        }
        final Prize prize = this.mapper.partialUpdate(dto, optionalPrize.get());
        return this.mapper.toResponseDto(this.repository.save(prize));
    }

    @SneakyThrows
    @Override
    public void changeState(long id, boolean isSorteado) {
        final Optional<Prize> optionalPrize = this.repository.findById(id);
        if(optionalPrize.isEmpty()) {
            throw new EntityAlreadyExistsException("Prize not exists");
        }
        Prize prize = optionalPrize.get();
        prize.setSorteado(isSorteado);
        this.repository.save(prize);
    }

    @Override
    public void delete(long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Page<PrizeResponseDto> findByCriteria(PrizeForQueryingDto dto, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        BooleanBuilder query = DynamicQueriesFactory.generateDynamicQuery(dto);
        return this.repository.findAll(pageable)
                .map(this.mapper::toResponseDto);
    }
}
