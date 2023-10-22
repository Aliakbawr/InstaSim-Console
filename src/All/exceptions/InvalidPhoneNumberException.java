package All.exceptions;

import java.util.InputMismatchException;

public class InvalidPhoneNumberException extends InputMismatchException {

    public InvalidPhoneNumberException(String massage) {
        super(massage);
    }

    public InvalidPhoneNumberException() {
        super();
    }
}
