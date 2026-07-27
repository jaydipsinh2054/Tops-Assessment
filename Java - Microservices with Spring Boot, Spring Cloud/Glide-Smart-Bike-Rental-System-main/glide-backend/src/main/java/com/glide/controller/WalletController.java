package com.glide.controller;

import com.glide.dto.AddMoneyRequest;
import com.glide.dto.TransactionResponse;
import com.glide.dto.WalletResponse;
import com.glide.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping
    public ResponseEntity<WalletResponse> getWallet() {

        return ResponseEntity.ok(walletService.getWallet());
    }

    @PostMapping("/add-money")
    public ResponseEntity<WalletResponse> addMoney(
            @RequestBody AddMoneyRequest request) {

        WalletResponse response = walletService.addMoney(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory() {

        return ResponseEntity.ok(walletService.getTransactionHistory());
    }
}