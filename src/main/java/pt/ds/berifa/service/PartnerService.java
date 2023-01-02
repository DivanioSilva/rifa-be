package pt.ds.berifa.service;

import pt.ds.berifa.dto.PartnerDtoResponse;
import pt.ds.berifa.dto.PartnerCreateDto;
import pt.ds.berifa.dto.PartnerUpdateDto;

public interface PartnerService {
    PartnerDtoResponse create(PartnerCreateDto dto);
    PartnerDtoResponse update(PartnerUpdateDto dto);
    void inactive(long id);
    void active(long id);
    boolean isActive(long id);

}
