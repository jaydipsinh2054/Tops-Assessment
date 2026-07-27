package com.glide.service.impl;

import com.glide.dto.AddMoneyRequest;
import com.glide.dto.TransactionResponse;
import com.glide.dto.WalletResponse;
import com.glide.entity.User;
import com.glide.entity.Wallet;
import com.glide.entity.WalletTransaction;
import com.glide.enums.TransactionType;
import com.glide.exception.ResourceNotFoundException;
import com.glide.repository.UserRepository;
import com.glide.repository.WalletRepository;
import com.glide.repository.WalletTransactionRepository;
import com.glide.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;
    private final UserRepository userRepository;

    @Override
    public WalletResponse getWallet() {

        Wallet wallet = getWalletByCurrentUser();

        return mapToWalletResponse(wallet);
    }

    @Override
    public WalletResponse addMoney(AddMoneyRequest request) {

        Wallet wallet = getWalletByCurrentUser();

        wallet.setBalance(wallet.getBalance().add(request.getAmount()));

        walletRepository.save(wallet);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.CREDIT);

        walletTransactionRepository.save(transaction);

        return mapToWalletResponse(wallet);
    }

    @Override
    public List<TransactionResponse> getTransactionHistory() {

        Wallet wallet = getWalletByCurrentUser();

        return walletTransactionRepository
                .findByWalletOrderByTransactionDateDesc(wallet)
                .stream()
                .map(this::mapToTransactionResponse)
                .toList();
    }

    private Wallet getWalletByCurrentUser() {

        User user = getCurrentUser();

        return walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet not found."));
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    private WalletResponse mapToWalletResponse(Wallet wallet) {

        return new WalletResponse(
                wallet.getId(),
                wallet.getUser().getFullName(),
                wallet.getBalance()
        );
    }

    private TransactionResponse mapToTransactionResponse(
            WalletTransaction transaction) {

        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getTransactionDate()
        );
    }
}