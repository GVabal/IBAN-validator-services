package dev.vabalas.service;

import dev.vabalas.repository.IBANCountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.regex.Pattern;

@Service
public class IBANValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IBANValidationService.class);
    private final IBANCountryRepository repository;

    public IBANValidationService(IBANCountryRepository repository) {
        this.repository = repository;
    }

    public boolean validateFormat(String number) {
        LOGGER.info("Validating format for IBAN " + number);
        return Pattern.matches("[A-z]{2}\\d{2}.+", number);
    }

    public boolean validateCountry(String number) {
        LOGGER.info("Validating country for IBAN " + number);
        String symbol = number.substring(0, 2);
        return repository.getCountries().containsKey(symbol);
    }

    public boolean validateLength(String number) {
        LOGGER.info("Validating length for IBAN " + number);
        return number.length() == repository.getCountries().get(number.substring(0, 2)).getLength();
    }

    public boolean validateStructure(String number) {
        LOGGER.info("Validating structure for IBAN " + number);
        return Pattern.matches(repository.getCountries().get(number.substring(0, 2)).getStructure(), number.substring(4));
    }

    public boolean validateMod97(String number) {
        LOGGER.info("Validating mod97 for IBAN " + number);
        BigInteger bigInteger = new BigInteger(convertIBANToNumber(number));
        return bigInteger.mod(new BigInteger("97")).equals(BigInteger.ONE);
    }

    public boolean validateIBAN(String number) {
        LOGGER.info("Validating IBAN " + number);
        return validateFormat(number) &&
                validateCountry(number) &&
                validateLength(number) &&
                validateStructure(number) &&
                validateMod97(number);
    }

    public String convertIBANToNumber(String number) {
        LOGGER.info("Converting IBAN " + number + " to number");
        number = number.substring(4) + number.substring(0, 4);
        StringBuilder result = new StringBuilder();
        for (Character c : number.toUpperCase().toCharArray()) {
            switch (c) {
                case '0':
                    result.append(0);
                    break;
                case '1':
                    result.append(1);
                    break;
                case '2':
                    result.append(2);
                    break;
                case '3':
                    result.append(3);
                    break;
                case '4':
                    result.append(4);
                    break;
                case '5':
                    result.append(5);
                    break;
                case '6':
                    result.append(6);
                    break;
                case '7':
                    result.append(7);
                    break;
                case '8':
                    result.append(8);
                    break;
                case '9':
                    result.append(9);
                    break;
                case 'A':
                    result.append(10);
                    break;
                case 'B':
                    result.append(11);
                    break;
                case 'C':
                    result.append(12);
                    break;
                case 'D':
                    result.append(13);
                    break;
                case 'E':
                    result.append(14);
                    break;
                case 'F':
                    result.append(15);
                    break;
                case 'G':
                    result.append(16);
                    break;
                case 'H':
                    result.append(17);
                    break;
                case 'I':
                    result.append(18);
                    break;
                case 'J':
                    result.append(19);
                    break;
                case 'K':
                    result.append(20);
                    break;
                case 'L':
                    result.append(21);
                    break;
                case 'M':
                    result.append(22);
                    break;
                case 'N':
                    result.append(23);
                    break;
                case 'O':
                    result.append(24);
                    break;
                case 'P':
                    result.append(25);
                    break;
                case 'Q':
                    result.append(26);
                    break;
                case 'R':
                    result.append(27);
                    break;
                case 'S':
                    result.append(28);
                    break;
                case 'T':
                    result.append(29);
                    break;
                case 'U':
                    result.append(30);
                    break;
                case 'V':
                    result.append(31);
                    break;
                case 'W':
                    result.append(32);
                    break;
                case 'X':
                    result.append(33);
                    break;
                case 'Y':
                    result.append(34);
                    break;
                case 'Z':
                    result.append(35);
                    break;
            }
        }
        return result.toString();
    }
}
