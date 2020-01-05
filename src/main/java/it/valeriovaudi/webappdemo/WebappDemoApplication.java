package it.valeriovaudi.webappdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.UUID;

@SpringBootApplication
public class WebappDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebappDemoApplication.class, args);
    }

    @Bean
    public String serviceId() {
        return UUID.randomUUID().toString();
    }

    @Bean
    public RouterFunction roue(String serviceId) {
        return RouterFunctions.route()
                .GET("/hello/{name}",
                        (serverRequest ->
                                ServerResponse.ok()
                                        .body(String.format("Hello %s from Service instance: %s", serverRequest.pathVariable("name"), serviceId))))
                .build();
    }
}

@Controller
class IndexController {

    private final String serviceId;

    IndexController(String serviceId) {
        this.serviceId = serviceId;
    }

    @GetMapping("/index")
    public void index(Model model) {
        model.addAttribute("message", String.format("Hellofrom Service instance: %s", serviceId));
    }
}