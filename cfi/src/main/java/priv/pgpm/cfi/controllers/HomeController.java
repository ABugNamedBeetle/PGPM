package priv.pgpm.cfi.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.BasicMarker;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpTracingAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private final ObservationRegistry observationRegistry;
    private final Counter homeRequestCounter;

    public HomeController(ObservationRegistry observationRegistry, MeterRegistry meterRegistry) {
        this.observationRegistry = observationRegistry;
        homeRequestCounter = Counter.builder("home.request.counter").description("Number of Home req catered").register(meterRegistry);
        

    }

    private static int count = 0;

    @GetMapping("home")
    public String getHome() {
        homeRequestCounter.increment();
        count++;
        Observation.createNotStarted("a valid obs", this.observationRegistry)
                .lowCardinalityKeyValue("locale", "en-US")
                .observe(() -> {
                    Marker notifyAdmin = MarkerFactory.getMarker("NOTIFY_ADMIN");
                    
                    
                    MDC.put("user.id", "123");
                    MDC.put("request.id", "req-456") ;
                    LOGGER.info(notifyAdmin, "done " + count);
                    MDC.clear();

                    // LOGGER.atDebug().setMessage("Temperature changed.").addKeyValue("oldT",
                    // 12344).addKeyValue("newT", 565).log();

                    try (MDC.MDCCloseable mdc = MDC.putCloseable("txId",
                        String.format(" Persistence Context Id: [%d], DB Transaction Id: [%s]",123456,7890))) {
                        LOGGER.info("Fetch Post by title");
                    }
                });
        return LocalDateTime.now().toString();
    }
    
    @GetMapping("throw")
    public void throwError() throws Exception {
        String g = LocalDateTime.now().toString();
        throw new Exception("exception thrown at " + g);

    }

}
