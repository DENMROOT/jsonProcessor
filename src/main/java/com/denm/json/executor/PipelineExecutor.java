package com.denm.json.executor;

import com.denm.json.model.PipelineDescriptor;
import java.util.Map;

public interface PipelineExecutor {
    void transform(PipelineDescriptor descriptor, Map<String, Object> jsonDocument);
}
