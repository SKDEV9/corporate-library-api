package com.SKDEV9.corporate_library_api.repository;

import com.SKDEV9.corporate_library_api.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    int countByUserIdAndDevolvidoFalse(Long userId);

}
