package dev.vabalas.rest;

import dev.vabalas.pojo.IBANNumberDto;
import dev.vabalas.service.IBANValidationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "rest")
public class IBANValidationController {

    private final IBANValidationService service;

    public IBANValidationController(IBANValidationService service) {
        this.service = service;
    }

    @PostMapping
    public List<IBANNumberDto> validateMany(@RequestBody String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("numbers");

        List<IBANNumberDto> results = new ArrayList<>();
        for (Object number : jsonArray) {
            results.add(new IBANNumberDto(number.toString(), service.validateIBAN(number.toString())));
        }
        return results;
    }

}
