package com.scaler.Splitwise.repositories;

import com.scaler.Splitwise.models.Expense;
import com.scaler.Splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findByGroup(Group group);
}
