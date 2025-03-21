package sakhno.sfg.beer.inventory.service.service.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import sakhno.sfg.beer.inventory.service.config.JmsConfig;
import sakhno.sfg.beer.inventory.service.service.allocation.AllocationService;
import sakhno.sfg.beer.inventory.service.web.model.events.AllocateOrderRequest;
import sakhno.sfg.beer.inventory.service.web.model.events.AllocateOrderResult;

@Component
@RequiredArgsConstructor
@Slf4j
public class AllocationListener {
    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    /**
     * Метод прослушивает очередь на распределение заказа и отправляет в очередь сообщение с результатом
     * распределения
     * @param allocateOrderRequest - объект с данными о заказе
     */
    @JmsListener(destination = JmsConfig.ALLOCATION_ORDER_QUEUE)
    private void listenAllocationRequest(AllocateOrderRequest allocateOrderRequest) {
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.beerOrderDto(allocateOrderRequest.getBeerOrderDto());

        try {
            Boolean allocationResult = allocationService.allocateOrder(allocateOrderRequest.getBeerOrderDto());
            builder.pendingInventory(!allocationResult);
            builder.allocationError(false);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("Размещение провалилось для заказа с id: {}", allocateOrderRequest.getBeerOrderDto().getId());
            builder.allocationError(true);
        }
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATION_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
