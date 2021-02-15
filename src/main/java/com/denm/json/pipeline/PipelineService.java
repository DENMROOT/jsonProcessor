package com.denm.json.pipeline;

import com.denm.json.model.PipelineDescriptor;

public interface PipelineService {
    PipelineDescriptor readPipeline(String input);
}
