package dev.vabalas.pojo;

public class IBANNumberDto {

    private final String number;
    private final boolean valid;

    public IBANNumberDto(String number, boolean valid) {
        this.number = number;
        this.valid = valid;
    }

    public String getNumber() {
        return number;
    }

    public boolean isValid() {
        return valid;
    }

}
