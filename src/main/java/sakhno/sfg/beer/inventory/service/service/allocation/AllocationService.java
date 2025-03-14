package sakhno.sfg.beer.inventory.service.service.allocation;

import sakhno.sfg.beer.inventory.service.web.model.order.BeerOrderDto;

public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
