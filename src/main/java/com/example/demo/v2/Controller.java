package com.example.demo.v2;

import org.springframework.web.bind.annotation.RestController;

@RestController("RestControllerv2")
public class Controller implements MyService {

    @Override
    public Health getServiceStatus() {
        return new Health();
    }

}
