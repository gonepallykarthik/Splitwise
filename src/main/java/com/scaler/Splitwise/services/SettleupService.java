package com.scaler.Splitwise.services;

import com.scaler.Splitwise.exceptions.GroupNotFoundException;
import com.scaler.Splitwise.models.Expense;
import com.scaler.Splitwise.models.Group;
import com.scaler.Splitwise.models.Transaction;
import com.scaler.Splitwise.repositories.ExpenseRepository;
import com.scaler.Splitwise.repositories.GroupRepository;
import com.scaler.Splitwise.strategy.GetterandPayersAlgo;
import com.scaler.Splitwise.strategy.SettleupAlgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleupService {

    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;
    private SettleupAlgo settleupAlgo;

    @Autowired
    public SettleupService(GroupRepository groupRepository, ExpenseRepository expenseRepository) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.settleupAlgo = new GetterandPayersAlgo();
    }

    public List<Transaction> settleGroup(int groupId) throws GroupNotFoundException {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (groupOptional.isEmpty()) {
            throw new GroupNotFoundException("group not found");
        }

        Group group = groupOptional.get();
//        List<Expense> expenses = group.getExpenses();
        List<Expense> expenses = expenseRepository.findByGroup(group);

        return settleupAlgo.settleup(expenses);
    }
}
