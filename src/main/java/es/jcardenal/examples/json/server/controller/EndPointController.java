package es.jcardenal.examples.json.server.controller;

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

        ProductDTO.reset();
        Stream<ProductDTO> stream = Stream.generate(ProductDTO::getInstance);
        return stream.limit(size);

    }
}
