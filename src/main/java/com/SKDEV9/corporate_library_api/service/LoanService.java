package com.SKDEV9.corporate_library_api.service;

import com.SKDEV9.corporate_library_api.dto.LoanRequestDTO;
import com.SKDEV9.corporate_library_api.model.Book;
import com.SKDEV9.corporate_library_api.model.Loan;
import com.SKDEV9.corporate_library_api.model.User;
import com.SKDEV9.corporate_library_api.repository.BookRepository;
import com.SKDEV9.corporate_library_api.repository.LoanRepository;
import com.SKDEV9.corporate_library_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    @Transactional
    public Loan createLoan(LoanRequestDTO dto) {

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));


        int livrosAtivos = loanRepository.countByUserIdAndDevolvidoFalse(user.getId());

        if (livrosAtivos >= 3) {
            throw new RuntimeException("Limite atingido! O usuario já possui 3 livros emprestados!");
        }


        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));


        if (!book.isAvailable()) {
            throw new RuntimeException("Este livro já está emprestado!");
        }


        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setDataDevolucao(LocalDate.now().plusDays(14));
        loan.setDevolvido(false);


        book.setAvailable(false);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }


    @Transactional
    public void returnBook(Long loandId) {

        Loan loan = loanRepository.findById(loandId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));


        if (loan.isDevolvido()) {
            throw new RuntimeException("Este livro já foi devolvido!");
        }

        loan.setDevolvido(true);
        loanRepository.save(loan);


        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
    }

}
