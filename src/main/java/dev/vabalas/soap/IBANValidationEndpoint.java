package dev.vabalas.soap;

import dev.vabalas.service.IBANValidationService;
import dev.vabalas.soap_iban_validator.IBANNumber;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import dev.vabalas.soap_iban_validator.GetIBANValidationRequest;
import dev.vabalas.soap_iban_validator.GetIBANValidationResponse;

@Endpoint
public class IBANValidationEndpoint {
    private static final String NAMESPACE_URI = "http://vabalas.dev/soap-iban-validator";

    private final IBANValidationService service;

    public IBANValidationEndpoint(IBANValidationService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIBANValidationRequest")
    @ResponsePayload
    public GetIBANValidationResponse getCountry(@RequestPayload GetIBANValidationRequest request) {
        GetIBANValidationResponse response = new GetIBANValidationResponse();

        IBANNumber ibanNumber = new IBANNumber();
        ibanNumber.setNumber(request.getNumber());
        ibanNumber.setValid(service.validateIBAN(request.getNumber()));

        response.setIBANNumber(ibanNumber);
        return response;
    }
}
