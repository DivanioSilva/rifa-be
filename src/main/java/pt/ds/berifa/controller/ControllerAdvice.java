package pt.ds.berifa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pt.ds.berifa.dto.errors.ExternalErrorMessage;
import pt.ds.berifa.dto.errors.ExternalErrorMessages;
import pt.ds.berifa.exceptions.EntityAlreadyExistsException;
import pt.ds.berifa.exceptions.EntityNotFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        ExternalErrorMessages messages = ExternalErrorMessages.builder().errorMessages(errorMessages).build();
        log.error(messages.toString());
        return messages;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    protected ExternalErrorMessages handleConstraintViolationError(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        List<ExternalErrorMessage> errorMessages = ex.getConstraintViolations()
                .stream()
                .map(x -> ExternalErrorMessage
                        .builder()
                        .errorMessage(x.getMessage()).build()).collect(Collectors.toList());
        ExternalErrorMessages messages = ExternalErrorMessages.builder().errorMessages(errorMessages).build();
        log.error(messages.toString());
        return messages;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityAlreadyExistsException.class})
    protected ExternalErrorMessage handleClientAlreadyExistsError(EntityAlreadyExistsException ex, WebRequest request){
        ExternalErrorMessage message = ExternalErrorMessage.builder().errorMessage(ex.getMessage()).build();
        log.error(message.toString());
        return message;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    protected ExternalErrorMessage handleClientNotFoundError(EntityNotFoundException ex, WebRequest request){
        ExternalErrorMessage message = ExternalErrorMessage.builder().errorMessage(ex.getMessage()).build();
        log.error(message.toString());
        return message;
    }

}
