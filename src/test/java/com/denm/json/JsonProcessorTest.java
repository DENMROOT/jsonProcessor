package com.denm.json;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.denm.json.executor.PipelineExecutor;
import com.denm.json.executor.PipelineExecutorImpl;
import com.denm.json.model.PipelineDescriptor;
import com.denm.json.pipeline.PipelineService;
import com.denm.json.pipeline.PipelineServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class JsonProcessorTest {
    String pipeline = "{\n"
        + "    \"steps\": [\n"
        + "        {\n"
        + "            \"processor\": \"AddField\",\n"
        + "            \"configuration\": {\n"
        + "                \"fieldName\": \"firstName\",\n"
        + "                \"fieldValue\": \"George\"\n"
        + "            }\n"
        + "        },\n"
        + "        {\n"
        + "            \"processor\": \"CountNumOfFields\",\n"
        + "            \"configuration\": {\n"
        + "                \"countFieldName\": \"numOfFields\"\n"
        + "            }\n"
        + "        }\n"
        + "    ]\n"
        + "}\n";

    String example = "{\n"
        + "    \"firstField\" : \"1\",\n"
        + "    \"secondField\" : \"2\",\n"
        + "    \"thirdField\" : \"3\"\n"
        + "}";

    @Test
    public void test_pipelineIsRead() {
        PipelineService pipelineService = new PipelineServiceImpl();

        PipelineDescriptor pipelineDescriptor = pipelineService.readPipeline(this.pipeline);

        assertNotNull(pipelineDescriptor);
    }

    @Test
    public void test_pipelineIsProcessed() throws JsonProcessingException {
        PipelineService pipelineService = new PipelineServiceImpl();
        PipelineDescriptor pipelineDescriptor = pipelineService.readPipeline(this.pipeline);

        PipelineExecutor executor = new PipelineExecutorImpl();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> exampleJson = objectMapper.readValue(example, HashMap.class);

        executor.transform(pipelineDescriptor, exampleJson);

        assertNotNull(exampleJson);

        assertTrue(exampleJson.containsKey("firstField"));
        assertTrue(exampleJson.containsValue("1"));

        assertTrue(exampleJson.containsKey("secondField"));
        assertTrue(exampleJson.containsValue("2"));

        assertTrue(exampleJson.containsKey("thirdField"));
        assertTrue(exampleJson.containsValue("3"));

        assertTrue(exampleJson.containsKey("firstName"));
        assertTrue(exampleJson.containsValue("George"));

        assertTrue(exampleJson.containsKey("numOfFields"));
        assertTrue(exampleJson.containsValue(4));
    }

}
