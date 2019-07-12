package com.example.demo.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1")
public interface MyService {

    @ApiOperation(value = "Get current service status", httpMethod = "GET", tags = {"Status"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Health.class)})
    @GetMapping(path = "/mystatus-one")
    Health getServiceStatus();

}
