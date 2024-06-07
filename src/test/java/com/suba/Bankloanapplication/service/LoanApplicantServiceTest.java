//package com.suba.Bankloanapplication.service;
//
//import com.suba.Bankloanapplication.dto.LoanApplicantdto;
//import com.suba.Bankloanapplication.model.Customer;
//import com.suba.Bankloanapplication.model.LoanApplicant;
//import com.suba.Bankloanapplication.model.TransactionStatus;
//import com.suba.Bankloanapplication.repository.CustomerRepository;
//import com.suba.Bankloanapplication.repository.LoanApplicantRepository;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//@SpringBootTest
//class LoanApplicantServiceTest {
//    @Mock
//    private CustomerRepository customerRepository;
//    //    @Mock
////    private TransactionRepository transactionRepository;
//    @Mock
//    private JavaMailSender javaMailSender;
//    @Mock
//    private LoanApplicantRepository loanApplicantRepository;
//    @Mock
//    private CustomerService customerService;
//
////    @Mock
////    TransactionService transactionService;
//
//    @InjectMocks
//    private LoanApplicantService loanApplicantService;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testFindAll() {
//        // Mock the behavior of loanApplicantRepository.findAll()
//        List<LoanApplicant> loanApplicants = new ArrayList<>();
//        LoanApplicant loanApplicant1 = new LoanApplicant();
//        loanApplicant1.setId(1);
//        loanApplicant1.setCibilScore(900);
//        LoanApplicant loanApplicant2 = new LoanApplicant();
//        loanApplicant2.setId(2);
//        loanApplicant2.setCibilScore(900);
//        loanApplicants.add(loanApplicant1);
//        loanApplicants.add(loanApplicant2);
//        when(loanApplicantRepository.findAll()).thenReturn(loanApplicants);
//
//        // Call the method under test
//        List<LoanApplicant> result = loanApplicantService.findAll();
//        // Assert that the result matches the expected list
//        assertEquals(loanApplicants, result);
//    }
//
/////->>>>>>>>>>>call cibil
//
//    @Test
//    public void testCallCibil() {
//        LoanApplicant loanApplicant = new LoanApplicant();
//        int actualCibilScore = loanApplicantService.callCibil(120000, 100000, loanApplicant);
//        assertEquals(900, actualCibilScore); // This assertion failed
//    }
//    @Test
//    public void testValidAccountNumber() {
//        // Valid account numbers with lengths between 11 and 16 digits
//        assertTrue(loanApplicantService.isValidAccountNumber("12345678901"));
//        assertTrue(loanApplicantService.isValidAccountNumber("123456789012"));
//        assertTrue(loanApplicantService.isValidAccountNumber("1234567890123"));
//        assertTrue(loanApplicantService.isValidAccountNumber("12345678901234"));
//        assertTrue(loanApplicantService.isValidAccountNumber("123456789012345"));
//        assertTrue(loanApplicantService.isValidAccountNumber("1234567890123456"));
//    }
//
//    @Test
//    public void testInvalidAccountNumber() {
//        // Invalid account numbers with lengths less than 11 or greater than 16 digits
//        assertFalse(loanApplicantService.isValidAccountNumber("123456789")); // Less than 11 digits
//        assertFalse(loanApplicantService.isValidAccountNumber("12345678901234567")); // Greater than 16 digits
//
//        // Invalid account numbers with non-numeric characters
//        assertFalse(loanApplicantService.isValidAccountNumber("1234567890A")); // Contains non-numeric characters
//        assertFalse(loanApplicantService.isValidAccountNumber("1234 56789012")); // Contains spaces
//        assertFalse(loanApplicantService.isValidAccountNumber("12-345-67890123")); // Contains special characters
//    }
//
//
//    @Test
//    public void testEmptyAccountNumber() {
//        // Empty account number
//        assertFalse(loanApplicantService.isValidAccountNumber(""));
//    }
//    ///->Email
//
//
//    @Test
//    public void testFindByEligibility() {
//        // Create some sample applicants
//        LoanApplicant applicant1 = new LoanApplicant();
//        applicant1.setId(1);
//        applicant1.setEligiblityStatus("Eligible");
//        applicant1.setLoanStatus(TransactionStatus.PENDING.name());
//        applicant1.setCibilScore(700);
//
//        // Create another applicant that does not meet the criteria
//        LoanApplicant applicant2 = new LoanApplicant();
//        applicant2.setId(2);
//        applicant2.setEligiblityStatus("Not Eligible");
//        applicant2.setLoanStatus(TransactionStatus.FAILED.name());
//        applicant2.setCibilScore(600);
//
//
//        LoanApplicant applicant3 = new LoanApplicant();
//        applicant3.setId(3);
//        applicant3.setEligiblityStatus("Eligible");
//        applicant3.setLoanStatus(TransactionStatus.PENDING.name());
//        applicant3.setCibilScore(700);
//
//        // Add them to the database or whatever method is appropriate
//
//        // Create a list of expected eligible applicants
//        List<LoanApplicant> expectedEligibleApplicants = Arrays.asList(applicant1,  applicant3);
//        // Mock behavior of loanApplicantRepository
//        when(loanApplicantRepository.findByLoanStatusAndEligiblityStatusAndCibilScoreGreaterThanEqual(
//                ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.anyInt()))
//                .thenReturn(expectedEligibleApplicants);
//        // Call the service method to find eligible applicants
//        List<LoanApplicant> actualEligibleApplicants = loanApplicantService.findByeligibility("Eligible", TransactionStatus.PENDING.name(), 600);
//
//        // Assert that the size of the expected list matches the size of the actual list
//        assertEquals(2, actualEligibleApplicants.size());
//        assertEquals(1, actualEligibleApplicants.get(0).getId());
//
//        // Assert that the contents of the expected list match the contents of the actual list
//        assertTrue(actualEligibleApplicants.containsAll(expectedEligibleApplicants));
//    }
//
//
//}