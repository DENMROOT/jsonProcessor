package com.denm.json.model;

public enum StepType {
    ADD_FIELD("AddField"),
    REMOVE_FIELD("RemoveField"),
    COUNT_NUMBER_OF_FIELDS("CountNumOfFields");

    private String name;

    StepType(String name) {
        this.name = name;
    }

    public static StepType getStepTypeByName(String name) {
        for (StepType type : StepType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
