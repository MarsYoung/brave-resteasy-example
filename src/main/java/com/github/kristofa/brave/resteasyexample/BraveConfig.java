package com.github.kristofa.brave.resteasyexample;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.FixedSampleRateTraceFilter;
import com.github.kristofa.brave.LoggingSpanCollector;
import com.github.kristofa.brave.TraceFilter;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.ServiceNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.github.kristofa.brave.http.StringServiceNameProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.github.kristofa.brave.scribe.ScribeSpanCollector;

import java.util.Arrays;

@Configuration
public class BraveConfig {

    @Bean
    @Scope(value = "singleton")
    public Brave brave() {
       Brave.Builder builder = new Brave.Builder("BraveTest");
//       return builder.spanCollector(new KafkaSpanCollector("192.168.99.100:9092"))
         return builder.spanCollector(new ScribeSpanCollector("10.16.12.137", 9410))
        //            return builder.spanCollector(new LoggingSpanCollector())
               //.traceFilters(Arrays.<TraceFilter>asList(new FixedSampleRateTraceFilter(1)))
               .build();
    }

    @Bean
    @Scope(value = "singleton")
    public ServiceNameProvider serviceNameProvider() {
        return new StringServiceNameProvider("brave");
    }

    @Bean
    @Scope(value = "singleton")
    public SpanNameProvider spanNameProvider() {
        return new DefaultSpanNameProvider();
    }
}
