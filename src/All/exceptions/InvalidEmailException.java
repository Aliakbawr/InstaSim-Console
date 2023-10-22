package All.exceptions;

import java.util.InputMismatchException;

public class InvalidEmailException extends InputMismatchException {
    public InvalidEmailException(String massage) {
        super(massage);
    }

    public InvalidEmailException() {
        super();
    }
}
