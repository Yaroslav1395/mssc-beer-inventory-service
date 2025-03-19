package sakhno.sfg.beer.inventory.service.web.controllers;

import sakhno.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import sakhno.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import sakhno.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @GetMapping("api/v1/inventory/beer/{beerId}")
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId){
        log.info("Поиск пива на складе по идентификатору:{}", beerId);

        return beerInventoryRepository.findAllByBeerId(beerId)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }
}
