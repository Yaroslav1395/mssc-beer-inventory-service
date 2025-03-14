package sakhno.sfg.beer.inventory.service.events;

import lombok.NoArgsConstructor;
import sakhno.sfg.beer.inventory.service.web.model.BeerDto;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
