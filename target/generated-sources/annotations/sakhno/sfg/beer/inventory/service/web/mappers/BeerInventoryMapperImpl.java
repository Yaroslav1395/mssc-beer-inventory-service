package sakhno.sfg.beer.inventory.service.web.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sakhno.sfg.beer.inventory.service.domain.BeerInventoryEntity;
import sakhno.sfg.beer.inventory.service.web.model.BeerInventoryDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-31T21:59:01+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class BeerInventoryMapperImpl implements BeerInventoryMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerInventoryEntity beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDto) {
        if ( beerInventoryDto == null ) {
            return null;
        }

        BeerInventoryEntity.BeerInventoryEntityBuilder beerInventoryEntity = BeerInventoryEntity.builder();

        beerInventoryEntity.id( beerInventoryDto.getId() );
        beerInventoryEntity.createdDate( dateMapper.asTimestamp( beerInventoryDto.getCreatedDate() ) );
        beerInventoryEntity.lastModifiedDate( dateMapper.asTimestamp( beerInventoryDto.getLastModifiedDate() ) );
        beerInventoryEntity.beerId( beerInventoryDto.getBeerId() );
        beerInventoryEntity.quantityOnHand( beerInventoryDto.getQuantityOnHand() );

        return beerInventoryEntity.build();
    }

    @Override
    public BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventoryEntity beerInventoryEntity) {
        if ( beerInventoryEntity == null ) {
            return null;
        }

        BeerInventoryDto.BeerInventoryDtoBuilder beerInventoryDto = BeerInventoryDto.builder();

        beerInventoryDto.id( beerInventoryEntity.getId() );
        beerInventoryDto.createdDate( dateMapper.asOffsetDateTime( beerInventoryEntity.getCreatedDate() ) );
        beerInventoryDto.lastModifiedDate( dateMapper.asOffsetDateTime( beerInventoryEntity.getLastModifiedDate() ) );
        beerInventoryDto.beerId( beerInventoryEntity.getBeerId() );
        beerInventoryDto.quantityOnHand( beerInventoryEntity.getQuantityOnHand() );

        return beerInventoryDto.build();
    }
}
