package com.denm.json.pipeline;

import com.denm.json.model.PipelineDescriptor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PipelineServiceImpl implements PipelineService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PipelineDescriptor readPipeline(String input) {
        PipelineDescriptor pipeline = null;
        try {
            pipeline = objectMapper.readValue(input, PipelineDescriptor.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error during deserialization:" + e );
        }

        // check validation
        validatePipeline(pipeline);

        return pipeline;
    }

    private boolean validatePipeline(PipelineDescriptor pipeline) {
        //TODO validate pipeline
        return true;
    }
}
