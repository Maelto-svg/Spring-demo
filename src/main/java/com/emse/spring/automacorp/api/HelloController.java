package com.emse.spring.automacorp.api;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/hello")
@Transactional
public class HelloController {
    @GetMapping("/{name}")
    public Message welcome(@PathVariable String name) {
        return new Message("Hello " + name);
    }

    public record Message(String message) {
    }
}