//package com.suba.Bankloanapplication.service;
//
//import com.suba.Bankloanapplication.config.CustomerLoanDetails;
//import com.suba.Bankloanapplication.model.Customer;
//import com.suba.Bankloanapplication.model.LoanApplicant;
//import com.suba.Bankloanapplication.repository.CustomerRepository;
//import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//class CustomerLoanServiceTest {
//    @Mock
//    private CustomerRepository CustomerRepository;
//    @Mock
//    private LoanApplicantRepository LoanApplicantRepository;
//    @InjectMocks
//    private CustomerLoanService CustomerLoanService;
//    public CustomerLoanServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//    @Test
//    void testGetCustomerLoanDetailsByAccountNumber(){
//        //1)Mock customer data
//        Customer customer=new Customer();
//        customer.setAccountNumber("123456789");
//        LoanApplicant loan1=new LoanApplicant();
//        loan1.setLoanType("Home");
//        loan1.setCustomerAccountNumber("123456789");
//        LoanApplicant loan2=new LoanApplicant();
//        loan2.setLoanType("Vehicle");
//        loan2.setCustomerAccountNumber("123456789");
//        List<LoanApplicant> loans=new ArrayList<>();
//        loans.add(loan1);
//        loans.add(loan2);
//        when(CustomerRepository.findByAccountNumber("123456789")).thenReturn(customer);
//        when(LoanApplicantRepository.findByCustomerAccountNumber("123456789")).thenReturn(loans);
//        CustomerLoanDetails customerLoanDetails=CustomerLoanService.getCustomerLoanDetailsByAccountNumber("123456789");
//
//        assertNotNull(customerLoanDetails);
//        assertEquals(customer,customerLoanDetails.getCustomer());
//        assertEquals(loans,customerLoanDetails.getLoans());
//        assertEquals(2,customerLoanDetails.getLoans().size());
//        assertEquals("Home",customerLoanDetails.getLoans().get(0).getLoanType());
//
//
//
//
//    }
//
//
//    @Test
//    void testGetCustomerLoanDetailsByAccountNumber_CustomerNotFound(){
//        when(CustomerRepository.findByAccountNumber(anyString())).thenReturn(null);
//        assertThrows(EntityNotFoundException.class,()-> CustomerLoanService.getCustomerLoanDetailsByAccountNumber("12345678"));
//
//    }
//
//
//}