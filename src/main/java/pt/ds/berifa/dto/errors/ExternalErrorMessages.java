package pt.ds.berifa.dto.errors;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class ExternalErrorMessages {
    private List<ExternalErrorMessage> errorMessages;
}
