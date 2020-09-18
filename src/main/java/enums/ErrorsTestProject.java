package enums;

public enum ErrorsTestProject {
    NEGATIVE_AGE("Age can't be negative or zero"),
    TEXT_EXCEED_LIMITS("the input value cannot exceed 100 characters"),
    DNI_IS_EMPTY("dni field is mandatory"),
    EXISTING_PERSON("the person you are trying to create already exists"),
    UNEXISTENT_PERSON("the profile you are trying to update doesn't exists");

    ErrorsTestProject(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }
}
