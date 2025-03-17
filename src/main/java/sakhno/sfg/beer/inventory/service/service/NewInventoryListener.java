package sakhno.sfg.beer.inventory.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sakhno.sfg.beer.inventory.service.config.JmsConfig;
import sakhno.sfg.beer.inventory.service.domain.BeerInventoryEntity;
import sakhno.sfg.beer.inventory.service.events.NewInventoryEvent;
import sakhno.sfg.beer.inventory.service.repositories.BeerInventoryRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;

    /**
     * Метод прослушивает очередь поступления на склад пива
     * @param event - объект события поступления пива
     */
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listenBrewingBeer(NewInventoryEvent event) {
        log.info("Новый инвентарный запас от поставщика для пива {} в количестве: {}",
                event.getBeerDto().getBeerName(), event.getBeerDto().getQuantityOnHand());
        beerInventoryRepository.save(BeerInventoryEntity.builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}
