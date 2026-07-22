package com.cognizant.springlearn;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler — catches validation and parse errors from ALL controllers.
 *
 * @ControllerAdvice applies this class across every @RestController.
 *
 * handleMethodArgumentNotValid → @Valid violations on @RequestBody → HTTP 400
 * handleHttpMessageNotReadable → malformed JSON or wrong field type → HTTP 400
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** Called when @Valid finds constraint violations on a @RequestBody. */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        LOGGER.info("Start handleMethodArgumentNotValid");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + " : " + fe.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        LOGGER.debug("Validation errors: {}", errors);
        LOGGER.info("End handleMethodArgumentNotValid");
        return new ResponseEntity<>(body, headers, status);
    }

    /** Called when JSON is malformed or a field has the wrong type. */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        LOGGER.info("Start handleHttpMessageNotReadable");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", "Bad Request");

        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) ex.getCause();
            cause.getPath().forEach(ref ->
                    body.put("message",
                            "Incorrect format for field '" + ref.getFieldName() + "'"));
        } else {
            body.put("message", ex.getMessage());
        }

        LOGGER.debug("Message not readable: {}", body);
        LOGGER.info("End handleHttpMessageNotReadable");
        return new ResponseEntity<>(body, headers, status);
    }
}
