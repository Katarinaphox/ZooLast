package error;

public class AnimalCreationException extends RuntimeException{
public AnimalCreationException() {
	super("Nickname can't be empty");
}
public AnimalCreationException(String message) {
    super(message);
}
}


