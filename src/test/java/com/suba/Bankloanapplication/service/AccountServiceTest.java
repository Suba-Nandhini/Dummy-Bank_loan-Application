//package com.suba.Bankloanapplication.service;
//
//import com.suba.Bankloanapplication.model.AccountDeletionRequest;
//import com.suba.Bankloanapplication.model.Customer;
//import com.suba.Bankloanapplication.repository.AccountDeletionRequestRepository;
//import com.suba.Bankloanapplication.repository.CustomerRepository;
//import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class AccountServiceTest {
//    @Mock
//    private LoanApplicantRepository loanApplicantRepository;
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private AccountDeletionRequestRepository accountDeletionRequestRepository;
//
//    @Mock
//    private LoanApplicantService loanApplicantService;
//    @InjectMocks
//    private AccountService accountService;
//    public AccountServiceTest(){
//        MockitoAnnotations.openMocks(this);
//
//    }
//    @Test
//    public void requestAccountDeletionTest_Customer_Not_Found(){
//        String accountNumber="123456789012";
//        when(customerRepository.findByAccountNumber(accountNumber)).thenReturn(null);
//        String result=accountService.requestAccountDeletion(accountNumber);
//        assertEquals(result, "Customer not found");
//
//    }
//    @Test
//    public void requestAccountDeletionTest_Customer_already_has_an_active_request(){
//        String accountNumber="123456789012";
//        Customer customer=new Customer();
//        when(customerRepository.findByAccountNumber(accountNumber)).thenReturn(customer);
//
//        when(accountDeletionRequestRepository.findByCustomerAndStatusAndStatusNot(customer, "PENDING", "COMPLETED"))
//                .thenReturn(Optional.of(new AccountDeletionRequest()));
//        String result = accountService.requestAccountDeletion(accountNumber);
//        assertEquals("Customer already has an active request", result);
//    }
//    @Test
//    public void requestAccountDeletionTest_Successful_account_deletion(){
//        Customer customer=new Customer();
//        String accountNumber="123456789012";
//        when(customerRepository.findByAccountNumber(accountNumber)).thenReturn(customer);
//        when(accountDeletionRequestRepository.findByCustomerAndStatusAndStatusNot(customer, "PENDING", "COMPLETED"))
//                .thenReturn(Optional.empty());
//        String result = accountService.requestAccountDeletion(accountNumber);
//        assertEquals("Account deletion request submitted successfully", result);
//        verify(accountDeletionRequestRepository, times(1)).save(any(AccountDeletionRequest.class));
//    }
//
//
//
//}