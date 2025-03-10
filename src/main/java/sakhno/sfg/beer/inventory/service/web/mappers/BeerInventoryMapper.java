package sakhno.sfg.beer.inventory.service.web.mappers;

import sakhno.sfg.beer.inventory.service.domain.BeerInventoryEntity;
import sakhno.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;


@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    /**
     * Метод позволяет преобразовать DTO данных пива по складу в сущность
     * @param beerInventoryDto - DTO данных по пиву на складе
     * @return - сущность данных пива по складу
     */
    BeerInventoryEntity beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDto);

    /**
     * Метод позволяет преобразовать сущность данных пива по складу в DTO
     * @param beerInventoryEntity - сущность данных пива по складу
     * @return - DTO данных по пиву на складе
     */
    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventoryEntity beerInventoryEntity);
}
