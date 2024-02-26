package be.akimts.test.testingspring.bll.exceptions;

public class TitleAlreadyTakenException extends RuntimeException{

    public TitleAlreadyTakenException() {
        super("title already taken");
    }
}
