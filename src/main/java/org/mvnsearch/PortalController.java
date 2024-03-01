package org.mvnsearch;

import org.mvnsearch.service.PigeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortalController {
    @Value("${name}")
    private String name;
    @Autowired
    private PigeonService pigeonService;

    @GetMapping("/")
    public String index() {
        System.out.println(pigeonService.getPigeon().getName());
        return "Hello " + name;
    }
}
