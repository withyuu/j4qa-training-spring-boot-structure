package com.ascend.training.j4qa.structure.controllers;

import java.util.List;

import com.ascend.training.j4qa.structure.Application;
import com.ascend.training.j4qa.structure.configs.ApplicationConfiguration;
import com.ascend.training.j4qa.structure.constants.ResponseStatus;
import com.ascend.training.j4qa.structure.exceptions.ToyNotFoundException;
import com.ascend.training.j4qa.structure.models.StandardResponse;
import com.ascend.training.j4qa.structure.entities.Toy;
import com.ascend.training.j4qa.structure.services.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ToyController {

    private ToyService toyService;

    private ApplicationConfiguration configurations;

    @Autowired
    public ToyController(ToyService toyService, ApplicationConfiguration configurations) {
        this.toyService = toyService;
        this.configurations = configurations;
    }

    @GetMapping("/toys")
    // for some older project @RequestMapping(path = "/toys", method = RequestMethod.GET)
    public StandardResponse getToyList() {
        List<Toy> toyList = toyService.getToyList();
        return new StandardResponse(ResponseStatus.SUCCESS, toyList);
    }

    @GetMapping("/toys/{id}")
    public StandardResponse getToy(@PathVariable("id") String toyId) {
        Toy t = toyService.getToy(Long.parseLong(toyId));
        return new StandardResponse(ResponseStatus.SUCCESS, t);
    }

    @PostMapping("/toys")
    public StandardResponse add(@Valid @RequestBody Toy toy) {
        toyService.createToy(toy);
        return new StandardResponse(ResponseStatus.SUCCESS, "DONE");
    }

    @GetMapping("/help")
    public String help() {
        return "Any question, please contact " + configurations.getContact();
    }

    @ExceptionHandler
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardResponse handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(field -> String.format("Field '%s': %s", field.getField(), field.getDefaultMessage()))
                .findFirst()
                .orElse(exception.getMessage());

        return new StandardResponse(ResponseStatus.ERROR, errorMsg);
    }

    @ExceptionHandler
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.NOT_FOUND)
    public StandardResponse handleException(ToyNotFoundException exception) {
        return new StandardResponse(ResponseStatus.ERROR, "Toy not found");
    }
}
