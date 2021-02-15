package com.denm.json.model;

import com.denm.json.pipeline.StepConfigDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonDeserialize(using = StepConfigDeserializer.class)
public class StepConfig {
    private String processor;
    private Map<String, String> configuration;

    public StepConfig() {
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("processor", processor)
            .append("configuration", configuration)
            .toString();
    }
}
