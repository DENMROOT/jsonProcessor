package com.denm.json.executor;

import com.denm.json.model.StepType;
import java.util.Objects;

public class ProcessorFactory {
    public static Processor create(String processorName) {
        StepType processorType = StepType.getStepTypeByName(processorName);

        switch (Objects.requireNonNull(processorType)) {
            case ADD_FIELD:
                return new AddProcessorImpl();
            case REMOVE_FIELD:
                return new RemoveProcessorImpl();
            case COUNT_NUMBER_OF_FIELDS:
                return new NumProcessorImpl();
            default:
                throw new RuntimeException("Error");
        }
    }
}
