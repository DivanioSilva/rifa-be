package pt.ds.berifa.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.ds.berifa.domain.Prize;
import pt.ds.berifa.domain.Round;
import pt.ds.berifa.dto.RoundResponseDto;
import pt.ds.berifa.dto.RoundForQueryingDto;
import pt.ds.berifa.dto.RoundOperationsDto;
import pt.ds.berifa.exceptions.EntityAlreadyExistsException;
import pt.ds.berifa.exceptions.EntityNotFoundException;
import pt.ds.berifa.factory.DynamicQueriesFactory;
import pt.ds.berifa.mapper.RoundMapper;
import pt.ds.berifa.repository.PrizeRepository;
import pt.ds.berifa.repository.RoundRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoundServiceImpl implements RoundService{
    private final RoundRepository roundRepository;
    private final RoundMapper roundMapper;
    private final PrizeRepository prizeRepository;
    @Override
    public RoundResponseDto create(RoundOperationsDto dto) {
        Round round = this.roundMapper.toEntity(dto);
        return this.roundMapper.toDto(this.roundRepository.save(round));
    }

    @SneakyThrows
    @Override
    public RoundResponseDto update(long id, RoundOperationsDto dto) {
        Round round = this.getRound(id);
        this.roundMapper.partialUpdate(dto, round);
        Round save = this.roundRepository.save(round);
        return this.roundMapper.toDto(save);
    }

    @SneakyThrows
    @Override
    public RoundResponseDto addPrizes(long roundId, List<Long> prizesIds) {
        Round round = this.getRound(roundId);
        List<Prize> prizes = this.getPrizes(prizesIds);
        round.setPrizes(prizes);
        Round save = this.roundRepository.save(round);
        return this.roundMapper.toDto(save);
    }

    @SneakyThrows
    @Override
    public boolean isActive(long roundId) {
        Round round = this.getRound(roundId);
        return round.isActive();
    }

    @SneakyThrows
    @Override
    public void changeState(long roundId, boolean isActive) {
        Round round = this.getRound(roundId);
        round.setActive(isActive);
        this.roundRepository.save(round);
    }

    @Override
    public Page<RoundResponseDto> searchByCriteria(RoundForQueryingDto dto, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        BooleanBuilder query = DynamicQueriesFactory.generateDynamicQuery(dto);
        return this.roundRepository.findAll(query, pageable).map(this.roundMapper::toDto);
    }

    private Round getRound(long id) throws EntityAlreadyExistsException {
        final Optional<Round> optionalPrize = this.roundRepository.findById(id);
        if(optionalPrize.isEmpty()) {
            throw new EntityAlreadyExistsException("Round not exists");
        }
        return optionalPrize.get();
    }

    private List<Prize>getPrizes(List<Long> prizesIds) throws EntityNotFoundException {
        List<Prize> prizes = this.prizeRepository.findAllById(prizesIds);
        if(prizes.isEmpty()){
            throw new EntityNotFoundException("One or more prizes not exists");
        }
        return prizes;
    }
}
