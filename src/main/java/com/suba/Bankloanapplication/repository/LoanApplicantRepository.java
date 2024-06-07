package com.suba.Bankloanapplication.repository;

import com.suba.Bankloanapplication.model.LoanApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

public interface LoanApplicantRepository extends JpaRepository<LoanApplicant, Integer> {

    List<LoanApplicant> findByLoanStatusAndEligiblityStatusAndCibilScoreGreaterThanEqual(String eligibilityStatus, String loanApplicantStatus, int cibil);

    List<LoanApplicant> findByCustomerAccountNumber(String accountNumber);

    List<LoanApplicant> findByLoanStatusAndEligiblityStatus(String approved, String eligible);

    @Modifying
    @Transactional
    @Query("DELETE FROM LoanApplicant l WHERE l.customerAccountNumber IN :customerAccountNumbers")
    void deleteByCustomerAccountNumberIn(@Param("customerAccountNumbers") List<String> customerAccountNumbers);
}
