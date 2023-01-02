package pt.ds.berifa.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pt.ds.berifa.domain.Partner;
import pt.ds.berifa.dto.PartnerCreateDto;
import pt.ds.berifa.dto.PartnerDtoResponse;
import pt.ds.berifa.dto.PartnerUpdateDto;
import pt.ds.berifa.exceptions.EntityAlreadyExistsException;
import pt.ds.berifa.exceptions.EntityNotFoundException;
import pt.ds.berifa.mapper.PartnerMapper;
import pt.ds.berifa.repository.PartnerRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService{
    private final PartnerMapper partnerMapper;
    private final PartnerRepository partnerRepository;

    @SneakyThrows
    @Override
    public PartnerDtoResponse create(PartnerCreateDto dto) {
        Optional<Partner> optionalPartner = this.partnerRepository.findByNif(dto.nif());
        if(optionalPartner.isEmpty()){
            throw new EntityAlreadyExistsException("Partner not found");
        }

        Partner partner = this.partnerMapper.toEntity(dto);
        Partner save = this.partnerRepository.save(partner);
        return this.partnerMapper.toDtoResponse(save);
    }

    @SneakyThrows
    @Override
    public PartnerDtoResponse update(PartnerUpdateDto dto) {
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
        Optional<Partner> optionalPartner = this.partnerRepository.findById(id);
        if(optionalPartner.isEmpty()){
            throw new EntityNotFoundException("Partner not found");
        }
        Partner partner = optionalPartner.get();
        partner.setBlock(true);
        this.partnerRepository.save(partner);
    }

    @SneakyThrows
    @Override
    public void active(long id) {
        Optional<Partner> optionalPartner = this.partnerRepository.findById(id);
        if(optionalPartner.isEmpty()){
            throw new EntityNotFoundException("Partner not found");
        }
        Partner partner = optionalPartner.get();
        partner.setBlock(false);
        this.partnerRepository.save(partner);
    }

    @SneakyThrows
    @Override
    public boolean isActive(long id) {
        Optional<Partner> optionalPartner = this.partnerRepository.findById(id);
        if(optionalPartner.isEmpty()){
            throw new EntityNotFoundException("Partner not found");
        }
        return optionalPartner.get().isBlock();
    }
}
