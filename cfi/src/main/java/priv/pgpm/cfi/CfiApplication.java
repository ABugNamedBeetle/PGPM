package priv.pgpm.cfi;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.logging.otlp.OtlpLoggingAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.otlp.OtlpMetricsExportAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpTracingAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.LogRecordProcessor;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.SdkLoggerProviderBuilder;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.semconv.ResourceAttributes;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;

@SpringBootApplication
@EnableJpaAuditing

public class CfiApplication {

    // OtlpLoggingAutoConfiguration
    // OtlpTracingAutoConfiguration
    // OtlpTracingAutoConfiguration
    public static void main(String[] args) {
        SpringApplication.run(CfiApplication.class, args);
    }

    // @Bean
    // LogRecordProcessor otelLogRecordProcessor() {
    // return BatchLogRecordProcessor
    // .builder(
    // OtlpGrpcLogRecordExporter.builder()
    // .setEndpoint("http://localhost:4317")
    // .build())
    // .build();
    // }

    // @Bean
    // OpenTelemetry openTelemetry(SdkLoggerProvider sdkLoggerProvider,
    // SdkTracerProvider sdkTracerProvider, ContextPropagators contextPropagators) {
    // OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
    // .setLoggerProvider(sdkLoggerProvider)
    // .setTracerProvider(sdkTracerProvider)
    // .setPropagators(contextPropagators)
    // // .setMeterProvider(meterProvider)
    // .build();
    // OpenTelemetryAppender.install(openTelemetrySdk);
    // Runtime.getRuntime().addShutdownHook(new Thread(openTelemetrySdk::close));
    // return openTelemetrySdk;
    // }

    // @Bean
    // SdkLoggerProvider otelSdkLoggerProvider(Environment environment,
    // ObjectProvider<LogRecordProcessor> logRecordProcessors) {
    // String applicationName = environment.getProperty("spring.application.name",
    // "application");
    // Resource springResource =
    // Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME,
    // applicationName));
    // SdkLoggerProviderBuilder builder = SdkLoggerProvider.builder()
    // .setResource(Resource.getDefault().merge(springResource));
    // logRecordProcessors.orderedStream().forEach(builder::addLogRecordProcessor);
    // return builder.build();
    // }

    @Component
    static class OpenTelemetryAppenderInitializer implements InitializingBean {

        private final OpenTelemetry openTelemetry;

        OpenTelemetryAppenderInitializer(OpenTelemetry openTelemetry) {
            this.openTelemetry = openTelemetry;
        }

        @Override
        public void afterPropertiesSet() {
            OpenTelemetryAppender.install(this.openTelemetry);
        }

    }
}
