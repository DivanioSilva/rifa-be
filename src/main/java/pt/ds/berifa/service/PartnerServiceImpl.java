package pt.ds.berifa.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.ds.berifa.domain.Partner;
import pt.ds.berifa.dto.PartnerCreateDto;
import pt.ds.berifa.dto.PartnerDtoResponse;
import pt.ds.berifa.dto.PartnerForQueryingDto;
import pt.ds.berifa.dto.PartnerUpdateDto;
import pt.ds.berifa.exceptions.EntityAlreadyExistsException;
import pt.ds.berifa.exceptions.EntityNotFoundException;
import pt.ds.berifa.factory.DynamicQueriesFactory;
import pt.ds.berifa.mapper.PartnerMapper;
import pt.ds.berifa.repository.PartnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService{
    private final PartnerMapper partnerMapper;
    private final PartnerRepository partnerRepository;

    @SneakyThrows
    @Override
    public PartnerDtoResponse create(PartnerCreateDto dto) {
        Optional<Partner> optionalPartner = this.partnerRepository.findByNif(dto.nif());
        if(optionalPartner.isPresent()){
            throw new EntityAlreadyExistsException("Partner already exists.");
        }

        Partner partner = this.partnerMapper.toEntity(dto);
        Partner save = this.partnerRepository.save(partner);
        return this.partnerMapper.toDtoResponse(save);
    }

    @SneakyThrows
    @Override
    public PartnerDtoResponse update(long id, PartnerUpdateDto dto) {
        Optional<Partner> optionalPartner = this.partnerRepository.findByIdAndNif(dto.id(), dto.nif());
        if(optionalPartner.isEmpty()){
            throw new EntityNotFoundException("Partner not found");
        }
        Partner partner = optionalPartner.get();

        this.partnerMapper.partialUpdate(dto, partner);

        Partner updated = this.partnerRepository.save(partner);
        return this.partnerMapper.toDtoResponse(updated);
    }

    @SneakyThrows
    @Override
    public void inactive(long id) {
        Partner partner = getPartnerOptional(id);
        partner.setBlock(true);
        this.partnerRepository.save(partner);
    }

    @SneakyThrows
    @Override
    public void active(long id) {
        Partner partner = getPartnerOptional(id);
        partner.setBlock(false);
        this.partnerRepository.save(partner);
    }

    @SneakyThrows
    @Override
    public boolean isActive(long id) {
        return getPartnerOptional(id).isBlock();
    }

    @Override
    public Page<PartnerDtoResponse> findByDynamicQuery(PartnerForQueryingDto dto, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final BooleanBuilder query = DynamicQueriesFactory.generateDynamicQuery(dto);
        return this.partnerRepository.findAll(query, pageable).map(this.partnerMapper::toDtoResponse);
    }

    @Override
    public Page<PartnerDtoResponse> findAll(int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return this.partnerRepository.findAll(pageable)
                .map(this.partnerMapper::toDtoResponse);
    }

    private Partner getPartnerOptional(long id) throws EntityNotFoundException {
        Optional<Partner> optionalPartner = this.partnerRepository.findById(id);
        if(optionalPartner.isEmpty()){
            throw new EntityNotFoundException("Partner not found");
        }
        return optionalPartner.get();
    }
}
