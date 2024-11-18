package com.financial.api.demo.entidades.transacoesBancarias;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMoeda {

    @JsonProperty("baseCurrency")
    private String baseCurrency;


}