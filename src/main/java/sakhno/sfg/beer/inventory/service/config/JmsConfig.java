package sakhno.sfg.beer.inventory.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import sakhno.sfg.beer.inventory.service.web.model.events.AllocateOrderRequest;
import sakhno.sfg.beer.inventory.service.web.model.events.AllocateOrderResult;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    public static final String NEW_INVENTORY_QUEUE = "NEW_INVENTORY_QUEUE";
    public static final String ALLOCATION_ORDER_QUEUE = "ALLOCATION_ORDER_QUEUE";
    public static final String ALLOCATION_ORDER_RESPONSE_QUEUE = "ALLOCATION_ORDER_RESPONSE_QUEUE";
    public static final String DEALLOCATE_ORDER_QUEUE = "DEALLOCATE_ORDER_QUEUE";


    /**
     * Данный метод создает бин, который будет использоваться как конвертер сообщений при работе с JMS.
     * MappingJackson2MessageConverter — это конвертер сообщений, который использует Jackson для преобразования
     * объектов в JSON и обратно.
     * setTargetType(MessageType.TEXT) — указывает, что сообщения будут передаваться в текстовом формате (обычно
     * это JSON).
     * setTypeIdPropertyName("_type") — добавляет в JSON-объект специальное свойство _type, которое используется для
     * определения типа объекта при десериализации.
     * setObjectMapper(objectMapper) - указывает, что объекты будут преобразованы с помощью ObjectMapper который
     * создал Spring
     * @return - конвертер сообщений
     */
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("AllocateOrderRequest", AllocateOrderRequest.class);
        typeIdMappings.put("AllocateOrderResult", AllocateOrderResult.class);
        converter.setTypeIdMappings(typeIdMappings);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
