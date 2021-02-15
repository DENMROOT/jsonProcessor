package com.denm.json.executor;

import com.denm.json.model.PipelineDescriptor;
import com.denm.json.model.StepConfig;
import com.denm.json.model.StepType;
import java.util.Map;

public class PipelineExecutorImpl implements PipelineExecutor {
    @Override
    public void transform(PipelineDescriptor descriptor, Map<String, Object> jsonDocument) {
        for (StepConfig step : descriptor.getSteps()) {
            if (step.getProcessor().equals(StepType.COUNT_NUMBER_OF_FIELDS.name())) {
                return;
            }
            Processor proc = ProcessorFactory.create(step.getProcessor());
            proc.initialize(step.getConfiguration());
            proc.process(jsonDocument);
        }

        for (StepConfig step : descriptor.getSteps()) {
            if (step.getProcessor().equals(StepType.COUNT_NUMBER_OF_FIELDS.name())) {
                Processor proc = ProcessorFactory.create(step.getProcessor());
                proc.initialize(step.getConfiguration());
                proc.process(jsonDocument);
            }

        }
    }
}
