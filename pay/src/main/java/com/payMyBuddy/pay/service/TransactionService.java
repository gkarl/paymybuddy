package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    // Afficher la liste des transaction de l'utilisateur quand login sur Homepage
    // Afficher la liste des transaction de l'utilisateur quand user va sur page Transfer
    // Quand on ajoute un nouveau contact
    // Quand user principale paye un contact sur la page Transfer
    public List<Transaction> findTransactionsOfUserPrincipal(User user) {
        List<Transaction> senderUserList = transactionRepository.findTransactionsBySenderUserEmail(user.getEmail());
        List<Transaction> receiverUserList = transactionRepository.findTransactionsByRecipientUserEmail(user.getEmail());
        List<Transaction> completUserList = Stream.of(senderUserList, receiverUserList).flatMap((x->x.stream())).collect(Collectors.toList());
        return completUserList;
    }

    //
    public Transaction saveTransaction(Transaction transaction) {
        transaction.setDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    // Aprés avoir remplit le formulaire pour payer un contact sur la page Transfer
    // Récupére les argument de la méthode les valeurs entrées dans le formulaire de la transaction et sauve ces valeurs dans les attributs correspondant présent dans le model transaction
    // Calcule et sauve la balance de user sender et receiver (si le receiver est trouvé ou si la balance du sender est supérieur au montant de l'envoie)
    @Transactional
    public void transferAppli(String emailSender, String emailRecipient, LocalDate date, Double amountTransaction, String description) {
        User userSender = userService.findByEmail(emailSender);
        User userRecipient = userService.findByEmail(emailRecipient);
        LocalDate localDate = LocalDate.now();
        if (userRecipient == null) {
            throw new RuntimeException("Aborted transaction, contact user not found");
        }
        else if (userSender.getBalance() - (amountTransaction + (amountTransaction*0.005)) < 0) {
            throw  new RuntimeException("The amount of the transaction exceeds the credit on your account");
        }
        else {
            userSender.setBalance(userSender.getBalance() - (amountTransaction + (amountTransaction * 0.005)));
            userService.save(userSender);
            userRecipient.setBalance(userRecipient.getBalance() + amountTransaction);
            userService.save(userRecipient);

            Transaction transaction = new Transaction();
            transaction.setSenderUser(userSender);
            transaction.setRecipientUser(userRecipient);
            transaction.setDate(localDate);
            transaction.setAmountTransaction(amountTransaction);
            transaction.setDescription(description);
            transactionRepository.save(transaction);

        }
    }


}
