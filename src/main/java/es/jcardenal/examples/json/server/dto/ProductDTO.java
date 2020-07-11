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

    static int count = 0;

    static public ProductDTO getInstance() {
            PriceDTO firstPrice = new PriceDTO("GBP", "3.4" + (count % 10));
            PriceDTO secondPrice = new PriceDTO( "EUR",  "2.7" + (count % 10));
            ProductDTO result = new ProductDTO("094871989999484" + count,
                    "The Product " + (count + 1),
                    "This is a test product no. " + (count + 1),
                    "This is an alternative description for product no. " + (count + 1),
                    "IDXXX"+count,
                    firstPrice,
                    secondPrice
                    );
        count++;
        return result;
    }

    static public void reset() {
        count =0;
    }
}
