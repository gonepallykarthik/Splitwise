package com.scaler.Splitwise.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Transaction {
    private double amount;
    private User from;
    private User to;
}
