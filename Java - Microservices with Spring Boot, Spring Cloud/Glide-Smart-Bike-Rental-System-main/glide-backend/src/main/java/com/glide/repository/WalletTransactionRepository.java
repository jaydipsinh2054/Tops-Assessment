package com.glide.repository;

import com.glide.entity.Wallet;
import com.glide.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository
        extends JpaRepository<WalletTransaction, Long> {

    List<WalletTransaction> findByWalletOrderByTransactionDateDesc(Wallet wallet);

}