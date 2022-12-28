package pt.ds.berifa.dto.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExternalErrorMessage {
    private String errorCode;
    private String errorMessage;
}
