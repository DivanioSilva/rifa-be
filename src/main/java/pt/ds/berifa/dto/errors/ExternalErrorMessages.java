package pt.ds.berifa.dto.errors;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Data
public class ExternalErrorMessages {
    private List<ExternalErrorMessage> errorMessages;
}
