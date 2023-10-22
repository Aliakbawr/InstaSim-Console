package All.exceptions;

import java.util.InputMismatchException;

public class InvalidUsernameException extends InputMismatchException {
    public InvalidUsernameException(String massage) {super(massage);}
    public InvalidUsernameException() {super();}
}

