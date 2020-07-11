package es.jcardenal.examples.json.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceDTO {
    private String currency;
    private String price;
}
