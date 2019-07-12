package com.example.demo.v3;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Health {

    @ApiModelProperty(value = "Unique id", example = "ade02824-32ba-4157-8122-807a48b13268",
            required = true)
    private String id;

    @ApiModelProperty(value = "Unique id of the other", example = "ade02824-32ba-4157-8122-807a48b13268",
            required = true)
    private String otherId;

    @ApiModelProperty(value = "Unique id of the third", example = "ade02824-32ba-4157-8122-807a48b13268",
            required = true)
    private String thirdId;

}
