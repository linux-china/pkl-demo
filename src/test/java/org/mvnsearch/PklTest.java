package org.mvnsearch;

import org.junit.jupiter.api.Test;
import org.mvnsearch.config.AppConfig;
import org.pkl.config.java.ConfigEvaluator;
import org.pkl.core.*;

import java.io.StringWriter;

public class PklTest {

    @Test
    public void testExportToJson() {
        PModule module = parseModule("pigeon { age = 2; name = \"surfing\" }");
        var pigeon = (PObject) module.get("pigeon");
        StringWriter output = new StringWriter();
        ValueRenderer render = ValueRenderers.json(output, "  ", true);
        render.renderDocument(module);
        System.out.println(output);
    }

    @Test
    public void testValidate() {
        PModule module = parseModule("pigeon { age = 30; name = \"surfing\" }");
        String schemaText = """
                pigeon = Pigeon
                class Pigeon {
                  name: String
                  age: UInt16
                }
                """;
        ModuleSchema schema = parseSchema(schemaText);
    }

    @Test
    public void testConfig() {
        ModuleSource moduleSource = ModuleSource.text("pigeon { age = 30; name = \"surfing\" }");
        var evaluator = ConfigEvaluator.preconfigured();
        var config = evaluator.evaluate(moduleSource);
        var appConfig = config.as(AppConfig.class);
        PObject pigeon =(PObject) appConfig.getPigeon();
        System.out.println(pigeon.getProperty("age"));
    }

    public ModuleSchema parseSchema(String schemaText) {
        ModuleSchema schema;
        try (var evaluator = Evaluator.preconfigured()) {
            schema = evaluator.evaluateSchema(
                    ModuleSource.text(schemaText));
        }
        return schema;
    }

    public PModule parseModule(String configuration) {
        PModule module;
        try (var evaluator = Evaluator.preconfigured()) {
            module = evaluator.evaluate(
                    ModuleSource.text(configuration));
        }
        return module;
    }
}
