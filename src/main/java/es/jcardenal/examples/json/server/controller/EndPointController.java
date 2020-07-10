package es.jcardenal.examples.json.server.controller;

import es.jcardenal.examples.json.server.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPointController {

    @GetMapping
    public String rootResponse() {
        return "Hello! Please, use /endpoint or /endpoint?items=`no.` ";
    }

    @GetMapping("/endpoint")
    public ProductDTO[] endpoint(@RequestParam(name = "items", required = false) Integer items) {
        int size = (items != null) ? items : 1;
        ProductDTO[] result = new ProductDTO[size];
        for(int i=0; i<size; i++)
            result[i] = new ProductDTO("094871989999484"+i,
                    "The Product "+(i+1),
                    "This is a test product no. "+(i+1),
                    "GBP",
                    "3.4"+(i % 10));
        return result;
    }
}
