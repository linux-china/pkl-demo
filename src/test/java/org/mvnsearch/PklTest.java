package org.mvnsearch;

import org.junit.jupiter.api.Test;
import org.mvnsearch.config.AppConfig;
import org.pkl.config.java.Config;
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
        String moduleText = "pigeon { age = 30; name = \"surfing\" }";
        String schemaText = """
                pigeon = Pigeon
                class Pigeon {
                  name: String
                  age: UInt16
                }
                """;
        try (var evaluator = Evaluator.preconfigured()) {
            var schema = evaluator.evaluateSchema(ModuleSource.text(schemaText));
            var module = evaluator.evaluate(ModuleSource.text(moduleText));
        }
    }

    @Test
    public void testConfig() {
        ModuleSource moduleSource = ModuleSource.text("pigeon { age = 30; name = \"surfing\" }");
        Config config;
        try (var evaluator = ConfigEvaluator.preconfigured()) {
            config = evaluator.evaluate(moduleSource);
        }
        var pigeon = config.get("pigeon").as(AppConfig.Pigeon.class);
        System.out.println(pigeon.getAge());
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
