package sakhno.sfg.beer.inventory.service.service.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sakhno.sfg.beer.inventory.service.config.JmsConfig;
import sakhno.sfg.beer.inventory.service.service.allocation.AllocationService;
import sakhno.sfg.beer.inventory.service.web.model.events.DeallocateOrderRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeallocateListener {
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    private void deallocateListen(DeallocateOrderRequest deallocateOrderRequest) {
        allocationService.deallocateOrder(deallocateOrderRequest.getBeerOrderDto());
    }
}
