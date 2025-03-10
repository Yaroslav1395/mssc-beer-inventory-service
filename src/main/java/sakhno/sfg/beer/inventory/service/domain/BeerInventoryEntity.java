package sakhno.sfg.beer.inventory.service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "beer_inventories")
public class BeerInventoryEntity extends BaseEntity {
    @Column(name="beer_id")
    private UUID beerId;

    private String upc;

    @Column(name = "quantity_on_hand")
    private Integer quantityOnHand = 0;

    @Builder
    public BeerInventoryEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, UUID beerId,
                               String upc, Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
    }
}
