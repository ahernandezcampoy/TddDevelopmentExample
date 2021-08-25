package com.tutorials.tdddevelopment.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Account {

    public BigDecimal getBalance() {
        return BigDecimal.ZERO;
    }

}
