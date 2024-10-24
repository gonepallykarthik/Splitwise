package com.scaler.Splitwise.controllers;

import com.scaler.Splitwise.dto.ResponseStatus;
import com.scaler.Splitwise.dto.SettleUpRequestDto;
import com.scaler.Splitwise.dto.SettleUpResponseDto;
import com.scaler.Splitwise.models.Transaction;
import com.scaler.Splitwise.services.SettleupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SettleUpController {

    @Autowired
    private SettleupService settleupService;

    public SettleUpResponseDto settleGroup(SettleUpRequestDto request) {
        SettleUpResponseDto response = new SettleUpResponseDto();

        try {

            List<Transaction> transactions = settleupService.settleGroup(request.getGroupId());
            response.setTransactions(transactions);
            response.setMessage("Success settled");
            response.setStatus(ResponseStatus.SUCCESS);

        } catch (Exception e) {
            response.setTransactions(null);
            response.setMessage(e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
