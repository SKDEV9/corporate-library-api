package com.SKDEV9.corporate_library_api.service;

import com.SKDEV9.corporate_library_api.dto.LoanRequestDTO;
import com.SKDEV9.corporate_library_api.model.Book;
import com.SKDEV9.corporate_library_api.model.Loan;
import com.SKDEV9.corporate_library_api.model.User;
import com.SKDEV9.corporate_library_api.repository.BookRepository;
import com.SKDEV9.corporate_library_api.repository.LoanRepository;
import com.SKDEV9.corporate_library_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LoanService loanService;


    @Test
    public void shouldThrowExceptionWhenUserHasTreeLoans() {

        Long userId = 1L;
        Long bookId = 2L;

        LoanRequestDTO dto = new LoanRequestDTO(userId, bookId);

        User fakeUser = new User();
        fakeUser.setId(userId);

        Mockito.when(userRepository.findById(userId))
                        .thenReturn(java.util.Optional.of(fakeUser));

        // Dizendo para o Mock que o usuario já possui 3 livros emprestados
        Mockito.when(loanRepository.countByUserIdAndDevolvidoFalse(userId))
                .thenReturn((3));

        assertThrows(RuntimeException.class, () -> {loanService.createLoan(dto);});
    }


    @Test
    public void shouldCreateLoanSuccessfully() {

        Long userId = 1L;
        Long bookId = 2L;

        LoanRequestDTO dto = new LoanRequestDTO(userId, bookId);

        User fakeUser = new User();
        fakeUser.setId(userId);

        Book fakeBook = new Book();
        fakeBook.setId(bookId);

        fakeBook.setAvailable(true);

        Loan fakeLoan = new Loan();
        fakeLoan.setId(100L);

        Mockito.when(userRepository.findById(userId))
                .thenReturn(java.util.Optional.of(fakeUser));


        Mockito.when(loanRepository.countByUserIdAndDevolvidoFalse(userId))
                        .thenReturn(1);



        Mockito.when(bookRepository.findById(bookId))
                .thenReturn(java.util.Optional.of(fakeBook));


        Mockito.when(loanRepository.save(Mockito.any()))
                .thenReturn(fakeLoan);

        Loan result = loanService.createLoan(dto);

        // Garantindo o sucesso da verificação comparando o ID
        org.junit.jupiter.api.Assertions.assertEquals(100L, result.getId());
    }


    @Test
    public void shouldThrowExceptionWhenLoanNotFound() {

        Long loanId = 1L;

        Mockito.when(loanRepository.findById(loanId))
                .thenReturn(java.util.Optional.empty());


        assertThrows(RuntimeException.class, () -> {loanService.returnBook(loanId);});

    }


    @Test
    public void shouldThrowExceptionWhenBookIsAlreadyReturned() {

        Long loanId = 1L;

        Loan fakeLoan = new Loan();
        fakeLoan.setDevolvido(true);

        Mockito.when(loanRepository.findById(loanId))
                .thenReturn(java.util.Optional.of(fakeLoan));

        assertThrows(RuntimeException.class, () -> {loanService.returnBook(loanId);});
    }


    @Test
    public void shouldReturnBookSuccessfully() {

        Long loanId = 1L;

        Book fakeBook = new Book();

        Loan fakeLoan = new Loan();
        fakeLoan.setBook(fakeBook);
        fakeLoan.setDevolvido(false);

        Mockito.when(loanRepository.findById(loanId))
                .thenReturn(java.util.Optional.of(fakeLoan));


        loanService.returnBook(loanId);

        org.junit.jupiter.api.Assertions.assertTrue(fakeLoan.isDevolvido());

        org.junit.jupiter.api.Assertions.assertTrue(fakeBook.isAvailable());

        verify(loanRepository).save(fakeLoan);
        verify(bookRepository).save(fakeBook);

    }

}
