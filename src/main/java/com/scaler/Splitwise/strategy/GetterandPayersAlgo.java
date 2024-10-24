package com.scaler.Splitwise.strategy;

import com.scaler.Splitwise.models.*;

import java.util.*;

public class GetterandPayersAlgo implements SettleupAlgo {

    @Override
    public List<Transaction> settleup(List<Expense> expenses) {
        List<Transaction> transactions = new ArrayList<>();

        // payers means the users who had to pay the amount to other users
        PriorityQueue<Pair> payers_max_heap = new PriorityQueue<>((a, b)-> Double.compare(b.getAmount(), a.getAmount()));

        // getters means the users who get the money (owe)
        PriorityQueue<Pair> getters_max_heap = new PriorityQueue<>((a, b)-> Double.compare(b.getAmount(), a.getAmount()));

        List<UserExpense> userExpenses = new ArrayList<>();

        // this set contains all users who are involved in this expenses
        Set<User> individualUsers = new HashSet<>();

        for (Expense expense : expenses) {
            for (UserExpense userExpense : expense.getExpenses()) {
                userExpenses.add(userExpense);
                individualUsers.add(userExpense.getUser());
            }
        }

        // now iterate through individual users and segregate the users between payers or getters
        Iterator<User> userIterator = individualUsers.iterator();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            // get the Net amount for this User
            // Net amount = total_Paid_By_user - total_user_has_to_pay
            double total_paid_by_user = 0;
            double total_user_has_to_pay = 0;

            for (UserExpense userExpense : userExpenses) {
                if (userExpense.getUser().equals(user)) {
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID_BY)) {
                        total_paid_by_user += userExpense.getAmount();
                    } else if(userExpense.getUserExpenseType().equals(UserExpenseType.PAY_FOR)) {
                        total_user_has_to_pay += userExpense.getAmount();
                    }
                }
            }

            double netAmount = total_paid_by_user - total_user_has_to_pay;
            if (netAmount < 0) {
                payers_max_heap.add(new Pair(user, Math.abs(netAmount)));
            }
            else {
                getters_max_heap.add(new Pair(user, netAmount));
            }
        }

        while (!payers_max_heap.isEmpty() && !getters_max_heap.isEmpty()) {
            Pair payer = payers_max_heap.poll();
            Pair getter = getters_max_heap.poll();

            // this payers information
            User payerUser = payer.getUser();
            double payerAmount = payer.getAmount();

            // this is getter information
            User getterUser = getter.getUser();
            double getterAmount = getter.getAmount();

            // three cases
            // 1. x < y
            if (getterAmount < payerAmount) {
                double amt = payerAmount - getterAmount;
                // create a new Transaction
                Transaction t1 = new Transaction(amt, payerUser, getterUser);
                transactions.add(t1);
                payers_max_heap.add(new Pair(payerUser, amt));
            }

            // 2. x > y
            else if (getterAmount > payerAmount) {
                double amt = getterAmount - payerAmount;
                Transaction t2 = new Transaction(amt, payerUser, getterUser);
                transactions.add(t2);
                getters_max_heap.add(new Pair(getterUser, amt));
            }

            // 3. x == y equal just settle
            else {
                Transaction t3 = new Transaction(payerAmount, payerUser, getterUser);
                transactions.add(t3);
            }

        }

        return transactions;
    }
}
