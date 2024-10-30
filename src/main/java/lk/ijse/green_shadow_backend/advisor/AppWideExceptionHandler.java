package lk.ijse.green_shadow_backend.advisor;

import lk.ijse.green_shadow_backend.exception.EntryNotFoundException;
import lk.ijse.green_shadow_backend.exception.InvalidUserRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.management.relation.InvalidRoleInfoException;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class AppWideExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        log.error("An error has occurred: {}", e.getMessage());
        return new ResponseEntity<>("An error has occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        log.error("Bad Request: {}", e.getMessage());
        return new ResponseEntity<>("Bad Request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException e){
        log.error("An error occurred while processing the image: {}", e.getMessage());
        return new ResponseEntity<>("An error occurred while processing the image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRoleInfoException.class)
    public ResponseEntity<String> handleInvalidRoleInfoException(InvalidRoleInfoException e){
        log.error("Invalid Role Info Exception occurred: {}", e.getMessage());
        return new ResponseEntity<>("Invalid Role Info Exception occurred: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e){
        log.error("Username Not Found: {}", e.getMessage());
        return new ResponseEntity<>("Username Not Found: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<String> handleEntryNotFoundException(EntryNotFoundException e){
        log.error("Entry Not Found: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserRoleException.class)
    public ResponseEntity<String> handleInvalidUserRoleException(InvalidUserRoleException e){
        log.error("Invalid User Role: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleEnumConversionError(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body("Invalid ENUM value: " + ex.getValue());
    }
}
