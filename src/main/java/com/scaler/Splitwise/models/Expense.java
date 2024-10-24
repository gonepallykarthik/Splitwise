package com.scaler.Splitwise.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity

public class Expense extends BaseModel{
    private String description;
    private double amount;
    @ManyToOne
    private User createdBy;
    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<UserExpense> expenses;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    @ManyToOne
    private Group group;

}
