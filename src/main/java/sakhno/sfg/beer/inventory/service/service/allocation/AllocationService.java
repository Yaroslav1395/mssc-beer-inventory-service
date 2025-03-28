package sakhno.sfg.beer.inventory.service.service.allocation;

import sakhno.sfg.beer.inventory.service.web.model.order.BeerOrderDto;

public interface AllocationService {

    /**
     * Метод позволяет распределить заказ на пиво
     * @param beerOrderDto - данные о заказе
     * @return - результат распределения
     */
    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    /**
     * Метод позволяет убрать пиво из очереди на поставку
     * @param beerOrderDto - данные о заказе
     */
    void deallocateOrder(BeerOrderDto beerOrderDto);
}
