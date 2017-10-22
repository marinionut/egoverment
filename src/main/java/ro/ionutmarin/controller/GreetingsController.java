package ro.ionutmarin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.ionutmarin.model.Greetings;
import ro.ionutmarin.service.GreetingsService;
import ro.ionutmarin.util.XmlConverter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ionut on 10/21/2017.
 */
@Controller
@RequestMapping("/greetings")
public class GreetingsController {
    @Autowired
    GreetingsService greetingsService;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public @ResponseBody
    ResponseEntity sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        Greetings greetings = new Greetings(counter.incrementAndGet(), String.format(template, name));

        try {
            greetingsService.save(greetings);
            return ResponseEntity.status(HttpStatus.OK).body(greetings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(greetings);
        }
    }
}

