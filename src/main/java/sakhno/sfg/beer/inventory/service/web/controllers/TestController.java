package sakhno.sfg.beer.inventory.service.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TestController {

    @GetMapping("api/v1/inventory/test")
    ResponseEntity<String> listBeersById(){
        return ResponseEntity.ok("value");
    }
}
