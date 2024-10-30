package lk.ijse.green_shadow_backend.exception;

public class EntryNotFoundException extends RuntimeException{
    public EntryNotFoundException(String entity, String message) {
        super(entity +" with reference "+message+" not found.");
    }
}
