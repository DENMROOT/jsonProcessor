package com.denm.json.pipeline;

import com.denm.json.model.StepConfig;
import com.denm.json.model.StepType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Streams;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

public class StepConfigDeserializer extends JsonDeserializer<StepConfig> {
    public StepConfig deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        String processor = node.get("processor").asText();
        JsonNode configuration = node.get("configuration");

        Map<String, String> configOptions = Streams
            .stream(configuration.fields())
            .map(entry -> Pair.of(entry.getKey(), entry.getValue().asText()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        StepType stepTypeByName = StepType.getStepTypeByName(processor);

        if (stepTypeByName == null) {
            throw new RuntimeException("Unknown step type");
        }

        StepConfig stepConfig = new StepConfig();
        stepConfig.setProcessor(processor);
        stepConfig.setConfiguration(configOptions);

        return stepConfig;
    }
}
