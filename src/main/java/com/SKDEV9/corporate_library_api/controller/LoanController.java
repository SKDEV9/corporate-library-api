package com.SKDEV9.corporate_library_api.controller;

import com.SKDEV9.corporate_library_api.dto.LoanRequestDTO;
import com.SKDEV9.corporate_library_api.model.Loan;
import com.SKDEV9.corporate_library_api.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SKDEV9.corporate_library_api.service.LoanService;


@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody @Valid LoanRequestDTO dto) {

        Loan savadLoan = service.createLoan(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savadLoan);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        service.returnBook(id);

        return ResponseEntity.ok().build();
    }
}
