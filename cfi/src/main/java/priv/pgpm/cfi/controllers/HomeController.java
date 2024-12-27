package priv.pgpm.cfi.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpTracingAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private final ObservationRegistry observationRegistry;
    

    public HomeController(ObservationRegistry observationRegistry) {
		this.observationRegistry = observationRegistry;
	}
    private static int count = 0;

    @GetMapping("home")
    public String getHome() {
        count++;
         Observation.createNotStarted("a valid obs", this.observationRegistry)
			.lowCardinalityKeyValue("locale", "en-US")
			.observe(() -> {
				LOGGER.info("done " + count);
			});
        return LocalDateTime.now().toString();
    }

    @GetMapping("throw")
    public void throwError() throws Exception {
        String g = LocalDateTime.now().toString();
        throw new Exception("exception thrown at "+ g);
        
    }
    
    
}
