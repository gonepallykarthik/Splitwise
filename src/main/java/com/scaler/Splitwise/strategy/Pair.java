package com.scaler.Splitwise.strategy;

import com.scaler.Splitwise.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Pair {
    private User user;
    private double amount;
}
