package com.scaler.Splitwise.dto;

import com.scaler.Splitwise.models.Transaction;
import lombok.Data;

import java.util.List;

@Data

public class SettleUpResponseDto {
    private List<Transaction> transactions;
    private String message;
    private ResponseStatus status;
}
