package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandlerError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
        var error = exception.getFieldErrors();
        
        return ResponseEntity.badRequest().body(error.stream().map(InnerHandlerError::new).toList());
    }

    private record InnerHandlerError(String field, String msg) {

        public InnerHandlerError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
