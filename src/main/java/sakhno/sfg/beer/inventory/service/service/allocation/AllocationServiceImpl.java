package sakhno.sfg.beer.inventory.service.service.allocation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakhno.sfg.beer.inventory.service.domain.BeerInventoryEntity;
import sakhno.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import sakhno.sfg.beer.inventory.service.web.model.order.BeerOrderDto;
import sakhno.sfg.beer.inventory.service.web.model.order.BeerOrderLineDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllocationServiceImpl implements AllocationService{
    private final BeerInventoryRepository beerInventoryRepository;

    /**
     * Метод позволяет распределить заказ на пиво
     * @param beerOrderDto - данные о заказе
     * @return - результат распределения
     */
    @Override
    public Boolean allocateOrder(BeerOrderDto beerOrderDto) {
        log.info("Распределение заказа с id: {}", beerOrderDto.getId());
        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLine -> {
            if ((((beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0)
                    - (beerOrderLine.getQuantityAllocated() != null ? beerOrderLine.getQuantityAllocated() : 0)) > 0)) {
                allocateBeerOrderLine(beerOrderLine);
            }
            totalOrdered.set(totalOrdered.get() + beerOrderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() + (beerOrderLine.getQuantityAllocated() != null ? beerOrderLine.getQuantityAllocated() : 0));
        });

        log.info("Заказ на: {} единиц, распределено на: {} единиц.", totalOrdered, totalAllocated);
        return totalOrdered.get() == totalAllocated.get();
    }

    /**
     * Метод позволяет убрать пиво из очереди на поставку
     * @param beerOrderDto - данные о заказе
     */
    @Override
    public void deallocateOrder(BeerOrderDto beerOrderDto) {
        beerOrderDto.getBeerOrderLines().forEach(beerOrderLine -> {
            BeerInventoryEntity beerInventory = BeerInventoryEntity.builder()
                    .beerId(beerOrderLine.getBeerId())
                    .upc(beerOrderLine.getUpc())
                    .quantityOnHand(beerOrderLine.getOrderQuantity())
                    .build();

            BeerInventoryEntity savedBeerInventory = beerInventoryRepository.save(beerInventory);
            log.info("Отмена распределения заказа пива с id: {} на: {} единиц", savedBeerInventory.getId(), savedBeerInventory.getQuantityOnHand());
        });
    }

    /**
     * Метод распределяет каждую позицию пива из заказа
     * @param beerOrderLine - позиция заказа
     */
    private void allocateBeerOrderLine(BeerOrderLineDto beerOrderLine) {
        List<BeerInventoryEntity> beerInventoryEntityList = beerInventoryRepository.findAllByUpc(
                beerOrderLine.getUpc());

        beerInventoryEntityList.forEach(beerInventory -> {
            int inventory = (beerInventory.getQuantityOnHand() == null) ? 0 : beerInventory.getQuantityOnHand();
            int orderQty = (beerOrderLine.getOrderQuantity() == null) ? 0 : beerOrderLine.getOrderQuantity();
            int allocatedQty = (beerOrderLine.getQuantityAllocated() == null) ? 0 : beerOrderLine.getQuantityAllocated();
            int qtyToAllocate = orderQty - allocatedQty;

            if(inventory >= qtyToAllocate) {
                inventory = inventory - qtyToAllocate;
                beerOrderLine.setQuantityAllocated(orderQty);
                beerInventory.setQuantityOnHand(inventory);
                beerInventoryRepository.save(beerInventory);
            } else if (inventory > 0) {
                beerOrderLine.setQuantityAllocated(allocatedQty + inventory);
                beerInventory.setQuantityOnHand(0);
            }
            if(beerInventory.getQuantityOnHand() != null && beerInventory.getQuantityOnHand() == 0) {
                beerInventoryRepository.delete(beerInventory);
            }
        });
    }
}
