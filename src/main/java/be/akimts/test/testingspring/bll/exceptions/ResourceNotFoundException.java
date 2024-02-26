package be.akimts.test.testingspring.bll.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final String ressourceType;
    private final Object searchedWith;

    public ResourceNotFoundException(String ressourceType, Object searchedWith) {
        super("cant find " + ressourceType + " with " + searchedWith.toString());
        this.ressourceType = ressourceType;
        this.searchedWith = searchedWith;
    }
}
