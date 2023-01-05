package pt.ds.berifa.service;

import org.springframework.data.domain.Page;
import pt.ds.berifa.dto.PartnerDtoResponse;
import pt.ds.berifa.dto.PartnerCreateDto;
import pt.ds.berifa.dto.PartnerForQueryingDto;
import pt.ds.berifa.dto.PartnerUpdateDto;

import java.util.List;

public interface PartnerService {
    PartnerDtoResponse create(PartnerCreateDto dto);
    PartnerDtoResponse update(long id, PartnerUpdateDto dto);
    void inactive(long id);
    void active(long id);
    boolean isActive(long id);

    Page<PartnerDtoResponse> findByDynamicQuery(PartnerForQueryingDto dto, int pageNumber, int pageSize);

    Page<PartnerDtoResponse> findAll(int page, int size);

}
