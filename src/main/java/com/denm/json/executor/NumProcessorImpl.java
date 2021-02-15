package com.denm.json.executor;

import java.util.Map;

public class NumProcessorImpl implements Processor {
    Map<String, String> config;

    @Override
    public void initialize(Map<String, String> configuration) {
        this.config = configuration;
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        jsonDocument.put(
            config.get("countFieldName"),
            jsonDocument.size());
    }
}
