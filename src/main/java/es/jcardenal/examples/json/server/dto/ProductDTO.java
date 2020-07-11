package es.jcardenal.examples.json.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String ean;
    private String name;
    private String description;
    private String alternateDescription;
    private String internalID;
    private PriceDTO firstPrice;
    private PriceDTO secondPrice;
}
