package com.example.demo.v3;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("RestControllerv3")
public class Controller implements MyService {

    @Override
    public Health getServiceStatus() {
        return new Health(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

}
