package sakhno.sfg.beer.inventory.service.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sakhno.sfg.beer.inventory.service.web.model.BeerDto;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = 105476142078104064L;
    private BeerDto beerDto;
}
