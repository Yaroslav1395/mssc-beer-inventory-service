package sakhno.sfg.beer.inventory.service.repositories;

import sakhno.sfg.beer.inventory.service.domain.BeerInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface BeerInventoryRepository extends JpaRepository<BeerInventoryEntity, UUID> {

    /**
     * Метод позволяет получить все пиво по UUID, которое есть на складе
     * @param beerId - UUID пива
     * @return - список пива
     */
    List<BeerInventoryEntity> findAllByBeerId(UUID beerId);
}
