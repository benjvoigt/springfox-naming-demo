package com.example.demo.v1;

import org.springframework.web.bind.annotation.RestController;

@RestController("RestControllerv1")
public class Controller implements MyService {

    @Override
    public Health getServiceStatus() {
        return new Health();
    }

}
