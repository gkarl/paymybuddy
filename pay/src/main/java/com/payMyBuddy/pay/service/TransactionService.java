package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.TransactionRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAllTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findTransactionOfUserPrincipal(User user) {
        List<Transaction> senderUserList = transactionRepository.findTransactionsBySenderUserEmail(user.getEmail());
        List<Transaction> receiverUserList = transactionRepository.findTransactionsByRecipientUserEmail(user.getEmail());
        List<Transaction> completUserList = Stream.of(senderUserList, receiverUserList).flatMap((x->x.stream())).collect(Collectors.toList());
        return completUserList;
    }

    public Transaction saveTransaction(Transaction transaction) {
        transaction.setDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    public void transfer(String emailSender, String emailRecipient, LocalDate date, Double amountTransaction, String description) {
        User userSender = userRepository.findByEmail(emailSender);
        User userRecipient = userRepository.findByEmail(emailRecipient);
        LocalDate localDate = LocalDate.now();
        if (userRecipient == null) {
            throw new RuntimeException("Aborted transaction, contact user not found");
        }
        else if (userSender.getBalance() - (amountTransaction + (amountTransaction*0.05)) < 0) {
            throw  new RuntimeException("The amount of the transaction exceeds the credit on your account");
        }
        else {
            userSender.setBalance(userSender.getBalance() - (amountTransaction + (amountTransaction * 0.05)));
            userRepository.save(userSender);

            userRecipient.setBalance(userRecipient.getBalance() + amountTransaction);
            userRepository.save(userRecipient);

            Transaction transaction = new Transaction();
            transaction.setSenderUser(userSender);
            transaction.setRecipientUser(userRecipient);
            transaction.setDate(localDate);
            transaction.setAmount(amountTransaction);
            transaction.setDescription(description);
            transactionRepository.save(transaction);
        }
    }
}
