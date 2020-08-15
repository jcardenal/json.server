package es.jcardenal.examples.json.server.controller;

import es.jcardenal.examples.json.server.dto.PriceDTO;
import es.jcardenal.examples.json.server.dto.ProductDTO;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@RestController
public class EndPointController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Logger logger = LoggerFactory.getLogger(EndPointController.class);

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


    @GetMapping("/trigger")
    public String eventing(@RequestParam(name = "items", required = false) Integer items,
                           @RequestParam(name = "ms", required = false) Integer milliseconds) {
        int size = (items != null) ? items : 1;
        int delay = (milliseconds != null) ? milliseconds : 1;

        if (size == 0) throw new RuntimeException("Invalid no. of items");
        if (delay == 0) throw new RuntimeException("Invalid delay between items");

        logger.info("Sending "+size+" Product Events with "+delay+" milliseconds delay in between");

        Observable.interval(delay, TimeUnit.MILLISECONDS).take(items)
                .subscribe(i -> this.simpMessagingTemplate.convertAndSend("/topic/items", getProduct(i.intValue())));

        return "OK: Sent "+ size+" with "+delay+" ms delay in between";
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
