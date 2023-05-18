package med.voll.api.infra.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidationException;

@RestControllerAdvice
public class HandlerError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<InnerHandlerError>> handleError400(MethodArgumentNotValidException exception) {
        var error = exception.getFieldErrors();
        
        return ResponseEntity.badRequest().body(error.stream().map(InnerHandlerError::new).toList());
    }

    @ExceptionHandler()
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    private record InnerHandlerError(String field, String msg) {

        public InnerHandlerError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
