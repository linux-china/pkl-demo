package org.mvnsearch;

import org.junit.jupiter.api.Test;
import org.pkl.core.*;

import java.io.StringWriter;
import java.util.List;

public class PklTest {

    @Test
    public void testExportToJson() {
        PModule module = parseModule();
        var pigeon = (PObject) module.get("pigeon");
        var hobbies = (List<String>) pigeon.get("hobbies");
        StringWriter output = new StringWriter();
        ValueRenderer render = ValueRenderers.json(output, "  ", true);
        render.renderDocument(module);
        System.out.println(output);
    }

    public PModule parseModule() {
        PModule module;
        try (var evaluator = Evaluator.preconfigured()) {
            module = evaluator.evaluate(
                    ModuleSource.text("pigeon { age = 30; hobbies = List(\"swimming\", \"surfing\") }"));
        }
        return module;
    }
}
