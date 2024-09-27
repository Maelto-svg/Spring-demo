package com.emse.spring.automacorp.hello;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyUserService implements UserService{
    private final ConsoleGreetingService consoleGreetingService;

    public DummyUserService(ConsoleGreetingService consoleGreetingService){
        this.consoleGreetingService=consoleGreetingService;
    }

    @Override
    public void greetAll(List<String> name) {
        for(String s : name){
            this.consoleGreetingService.greet(s);
        }
    }
}
