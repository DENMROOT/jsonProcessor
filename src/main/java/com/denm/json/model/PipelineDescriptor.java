package com.denm.json.model;

import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PipelineDescriptor {
    private Set<StepConfig> steps;

    public PipelineDescriptor() {
    }

    public Set<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(Set<StepConfig> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("steps", steps)
            .toString();
    }
}
