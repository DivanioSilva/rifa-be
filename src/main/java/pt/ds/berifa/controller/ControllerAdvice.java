package pt.ds.berifa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pt.ds.berifa.dto.errors.ExternalErrorMessage;
import pt.ds.berifa.dto.errors.ExternalErrorMessages;
import pt.ds.berifa.exceptions.ClientAlreadyExistsException;
import pt.ds.berifa.exceptions.ClientNotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ExternalErrorMessages handleValidationError(MethodArgumentNotValidException ex, WebRequest request){
        List<ExternalErrorMessage> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                //.map(x -> x.getDefaultMessage())
                .map(
                        x -> ExternalErrorMessage
                                .builder()
                                .errorMessage(x.getDefaultMessage())
                                .build())
                .collect(Collectors.toList());
        return ExternalErrorMessages.builder().errorMessages(errorMessages).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ClientAlreadyExistsException.class})
    protected ExternalErrorMessage handleClientAlreadyExistsError(ClientAlreadyExistsException ex, WebRequest request){
        return ExternalErrorMessage.builder().errorMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientNotFoundException.class)
    protected ExternalErrorMessage handleClientNotFoundError(ClientNotFoundException ex, WebRequest request){
        return ExternalErrorMessage.builder().errorMessage(ex.getMessage()).build();
    }

}
