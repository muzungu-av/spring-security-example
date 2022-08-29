package ru.example.restgatewayapi.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.restgatewayapi.services.word.WordService;
import ru.example.restgatewayapi.storage.syllable.ParsedWordResult;

import java.util.LinkedList;

@RestController
@RequestMapping("${paths.auth-api}")
public class WordController {

    private WordService service;

    @Autowired
    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping(path = "/word",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    LinkedList<ParsedWordResult> parse(@RequestBody final String word) {
        try {
            return this.service.parseOneWord(word);
        } catch (Exception e) {
            //todo log
            return new LinkedList<>();
        }
    }

}
