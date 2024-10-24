package com.scaler.Splitwise.strategy;

import com.scaler.Splitwise.models.Expense;
import com.scaler.Splitwise.models.Transaction;

import java.util.List;

public interface SettleupAlgo {
    public List<Transaction> settleup(List<Expense> expenses);
}
