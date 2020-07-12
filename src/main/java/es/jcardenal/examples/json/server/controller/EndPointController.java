package es.jcardenal.examples.json.server.controller;

import es.jcardenal.examples.json.server.dto.PriceDTO;
import es.jcardenal.examples.json.server.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class EndPointController {

    @GetMapping
    public String rootResponse() {
        return "Hello! Please, use /endpoint or /endpoint?items=`no.`<br />" +
                "Use `items=0` to receive a 500 status";
    }

    @GetMapping("/endpoint")
    public Stream<ProductDTO> endpoint(@RequestParam(name = "items", required = false) Integer items) {
        int size = (items != null) ? items : 1;

        if (size == 0) throw new RuntimeException("Invalid no. of items");

        return Stream.iterate(0, i -> i + 1).limit(size).map(this::getProduct);
    }
    
    private ProductDTO getProduct(int i) {
        PriceDTO firstPrice = new PriceDTO("GBP", "3.4" + (i % 10));
        PriceDTO secondPrice = new PriceDTO( "EUR",  "2.7" + (i % 10));
        return new ProductDTO("094871989999484" + i,
                "The Product " + (i + 1),
                "This is a test product no. " + (i + 1),
                "This is an alternative description for product no. " + (i + 1),
                "IDXXX"+i,
                firstPrice,
                secondPrice
        );
    }
    
}
