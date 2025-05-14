package desafio.itau.springboot.controller;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.itau.springboot.service.TransactionService;
import desafio_itau.dto.TransactionRequest;
import desafio_itau.springboot.model.Transaction;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

private final TransactionService transactionService;

public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
}

@PostMapping
public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request) {
    if(request.getDataHora().isAfter(OffsetDateTime.now())) {
        return ResponseEntity.unprocessableEntity().build();
    }
    transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
    return ResponseEntity.status(HttpStatus.CREATED).build();
}

@DeleteMapping
public ResponseEntity<Void> clearTransactions() {
    transactionService.clearTransactions();
    return ResponseEntity.ok().build();
}
    
}