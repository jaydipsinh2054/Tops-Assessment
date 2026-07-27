package com.glide.service;

import com.glide.dto.AddMoneyRequest;
import com.glide.dto.TransactionResponse;
import com.glide.dto.WalletResponse;

import java.util.List;

public interface WalletService {

    WalletResponse getWallet();

    WalletResponse addMoney(AddMoneyRequest request);

    List<TransactionResponse> getTransactionHistory();
}