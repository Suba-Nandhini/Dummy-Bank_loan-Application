package com.suba.Bankloanapplication.service;
import com.suba.Bankloanapplication.exception.CustomerException;
import com.suba.Bankloanapplication.exception.LoanApplicantException;
import com.suba.Bankloanapplication.mapper.LoanApplicantMapper;
import com.suba.Bankloanapplication.model.*;
import com.suba.Bankloanapplication.repository.CustomerRepository;
import com.suba.Bankloanapplication.repository.LoanApplicantRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class LoanApplicantService {

    private static final Logger log = LoggerFactory.getLogger(LoanApplicantService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanApplicantRepository loanApplicantRepository;
    @Autowired
    private CustomerService customerService;
    private final JavaMailSender javaMailSender;

    public LoanApplicantService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public List<LoanApplicant> findAll() {

        return loanApplicantRepository.findAll();
    }
    public String AddApplication(it.bankapp.LoanApplicantdto loanApplicantdto) throws LoanApplicantException,CustomerException {
        if (!isValidAccountNumber(loanApplicantdto.getCustomerAccountNumber())) {
            throw new LoanApplicantException("Invalid account number");
        }

        if (loanApplicantdto.getTenure() == 0 || loanApplicantdto.getLoanAmount() == 0) {
            throw new LoanApplicantException("Invalid tenure or loan amount");
        }
        Customer customer1=customerRepository.findByAccountNumber(loanApplicantdto.getCustomerAccountNumber());
        if(customer1==null){
           throw new LoanApplicantException("Customer not found for the provided account number");

        }

        String customerType = customerService.getCustomerTypeByAccountNumber(loanApplicantdto.getCustomerAccountNumber());
        LoanApplicant loanApplicant = LoanApplicantMapper.LoanApplicantdtotoLoanApplicant(loanApplicantdto,customer1);
        // Validate account number
        
        if(!processLoanType(loanApplicantdto,customerType,loanApplicant)){
            throw new LoanApplicantException("Invalid loan type or tenure exceeds limit");
        }
        loanApplicantRepository.save(loanApplicant);
        handleEligibility(loanApplicant);

        double bankBalance = getBankBalance(customer1.getAccountNumber());
        if (bankBalance < 2000) {
            notifyMinimumBalance(customer1);
        }

        return "Loan Application Submitted Successfully";

    }

    private boolean processLoanType(it.bankapp.LoanApplicantdto loanApplicantdto, String customerType, LoanApplicant loanApplicant) {
        double loanAmount=loanApplicant.getLoanAmount();
        String loanType=loanApplicant.getLoanType();
        int tenure=loanApplicant.getTenure();
        if(loanType.equalsIgnoreCase("Home")){
            return processHomeLoan(loanApplicantdto,customerType,loanApplicant,loanAmount,tenure);
        }
        else if (loanType.equalsIgnoreCase("Personal") || loanType.equalsIgnoreCase("Education")) {
            return processPersonalOrEducationLoan(loanApplicantdto, customerType, loanApplicant, loanAmount, tenure, loanType);
        } else if (loanType.equalsIgnoreCase("Vehicle")) {
            return processVehicleLoan(loanApplicantdto, customerType, loanApplicant, loanAmount, tenure);
        }return false;
    }

    private boolean processVehicleLoan(it.bankapp.LoanApplicantdto loanApplicantdto, String customerType, LoanApplicant loanApplicant, double loanAmount, int tenure) {
        float rateOfInterest = 8.5f / (12 * 100);
        int maxTenure = customerType.equalsIgnoreCase(CustomerType.PRIVILEGE.name()) ? 30 : 12;

        if (tenure > maxTenure) {
            return false;
        }

        if (customerType.equalsIgnoreCase(CustomerType.PRIVILEGE.name())) {
            rateOfInterest -= 1.0f / (12 * 100);
        }

        double emi = calculateEMI(loanAmount, rateOfInterest, tenure);
        loanApplicant.setRateOfInterest(rateOfInterest * 12 * 100);
        loanApplicant.setEmiAmt(emi);

        updateLoanApplicantDetails(loanApplicant);

        return true;
    }

    private boolean processPersonalOrEducationLoan(it.bankapp.LoanApplicantdto loanApplicantdto, String customerType, LoanApplicant loanApplicant, double loanAmount, int tenure, String loanType) {
        float rateOfInterest = loanType.equalsIgnoreCase("Personal") ? 5.5f : 4.5f;
        int maxTenure = loanType.equalsIgnoreCase("Education") ? (customerType.equalsIgnoreCase(CustomerType.PRIVILEGE.name()) ? 180 : 120) : 60;

        if (tenure > maxTenure) {
            return false;
        }

        if (customerType.equalsIgnoreCase(CustomerType.PRIVILEGE.name())) {
            rateOfInterest -= 1.0f;
        }

        rateOfInterest /= (12 * 100);
        double emi = calculateEMI(loanAmount, rateOfInterest, tenure);
        loanApplicant.setRateOfInterest(rateOfInterest * 12 * 100);
        loanApplicant.setEmiAmt(emi);

        updateLoanApplicantDetails(loanApplicant);

        return true;
    }
    private double calculateEMI(double loanAmount, float rateOfInterest, int tenure) {
        double emi = (loanAmount * rateOfInterest * Math.pow(1 + rateOfInterest, tenure)) / (Math.pow(1 + rateOfInterest, tenure) - 1);
        return Math.round(emi * 100) / 100.0;
    }

    private void updateLoanApplicantDetails(LoanApplicant loanApplicant) {
        double availableSalary = loanApplicant.getNetSalary() - (loanApplicant.getEmiAmt() + loanApplicant.getExisting_emi_amt());
        int cibilScore = callCibil(availableSalary, loanApplicant.getNetSalary(), loanApplicant);
        loanApplicant.setCibilScore(cibilScore);

        if (loanApplicant.getEmiAmt() + loanApplicant.getExisting_emi_amt() < loanApplicant.getNetSalary() / 2) {
            loanApplicant.setEligiblityStatus("Eligible");
        } else {
            loanApplicant.setEligiblityStatus("Not Eligible");
        }
    }

    private void handleEligibility(LoanApplicant loanApplicant) {
        if (loanApplicant.getEligiblityStatus().equals("Not Eligible")) {
            Customer customer = customerRepository.findByAccountNumber(loanApplicant.getCustomerAccountNumber());
            String reason = loanApplicant.getCibilScore() <= 500 ? "Your CIBIL score is lower than the minimum requirement." : "Your Debt-to-Income ratio is too high.";
            sendEmail(customer.getEmail(), "Loan Application Status Update", reason);
            loanApplicant.setLoanStatus(TransactionStatus.FAILED.name());
        }
        loanApplicantRepository.save(loanApplicant);
    }

    private void notifyMinimumBalance(Customer customer) {
        String msg = "Dear Customer,\nYour bank balance is below the minimum required balance of 2000. Please ensure to maintain the minimum balance.\nSincerely,\nNandhini Bank of India";
        sendEmail(customer.getEmail(), "Minimum Bank Balance Notification", msg);
    }


    private boolean processHomeLoan(it.bankapp.LoanApplicantdto loanApplicantdto, String customerType, LoanApplicant loanApplicant, double loanAmount, int tenure) {
        float rateOfInterest = 9.5f / (12 * 100);
        int maxTenure = customerType.equalsIgnoreCase(CustomerType.PRIVILEGE.name()) ? 300 : 180;

        if (tenure > maxTenure) {
            return false;
        }

        if (customerType.equalsIgnoreCase(CustomerType.PRIVILEGE.name())) {
            rateOfInterest -= 1.0f / (12 * 100);
        }

        double emi = calculateEMI(loanAmount, rateOfInterest, tenure);
        loanApplicant.setRateOfInterest(rateOfInterest * 12 * 100);
        loanApplicant.setEmiAmt(emi);

        updateLoanApplicantDetails(loanApplicant);

        return true;
    }


    public int callCibil(double availableIncome,long netSalary,LoanApplicant loanApplicant) {
        double scorePercentage = (double) availableIncome / netSalary;
        int cibilScore = (int) (300 + scorePercentage * 600); // Scale to range from 300 to 900

        // Ensure the calculated CIBIL score falls within the range of 300 to 900
        return Math.min(Math.max(cibilScore, 300), 900);

    }

    public boolean isValidAccountNumber(String customerAccountNumber) {
        String accountNumberRegex="\\d{11,16}";
        return Pattern.matches(accountNumberRegex,customerAccountNumber);
    }
    public void sendEmail(String email,String subject,String reason){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(reason,false);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public List<LoanApplicant> findByeligibility(String eligibility,String loanStatus,int cibil){
        List<LoanApplicant> applicants=loanApplicantRepository.findByLoanStatusAndEligiblityStatusAndCibilScoreGreaterThanEqual(loanStatus, eligibility, cibil);
        System.out.println("Applicants found: " + applicants.size());
        return applicants;
    }

    public List<LoanApplicant> getAllLoans() {
        return loanApplicantRepository.findAll();
    }

    public String approveLoan(int id) throws CustomerException,LoanApplicantException{
        Optional<LoanApplicant> optionalLoanApplicant = loanApplicantRepository.findById(id);
        if (optionalLoanApplicant.isPresent()) {
            LoanApplicant loanApplicant = optionalLoanApplicant.get();

            double balance=getBankBalance(loanApplicant.getCustomerAccountNumber());
            if(balance>=2000){

                double loanamount=loanApplicant.getLoanAmount();
                Customer customer = customerRepository.findByAccountNumber(loanApplicant.getCustomerAccountNumber());

                if (customer != null) {
                    loanamount+=customer.getBalance();
                    customer.setBalance(loanamount);
                    customerRepository.save(customer);
                    loanApplicant.setCustomerEmail(customer.getEmail());
                    String msg="Dear Customer,"+ "\n"+
                             "We are pleased to inform you that your loan application has been approved"+"\n"
                            + "Loan Details:"+"\n"
                            + "Loan Amount: $" + loanApplicant.getLoanAmount()+ "\n"
                            + "Loan Tenure: " + loanApplicant.getTenure() + " months"+"\n"
                            + "Monthly EMI: $" + loanApplicant.getEmiAmt() + "\n"
                            + "Please feel free to contact us if you have any questions or need further assistance"+"\n"
                            + "Best regards"+" \n"
                            + "Nandhini Bank of India ";
                    sendEmail(customer.getEmail(),"Loan Approval Notification",msg);

                    AccountDetails accountDetails=new AccountDetails();
                    accountDetails.setSenderAccountNumber("123456789012");
                    accountDetails.setReceiverAccountNumber(customer.getAccountNumber());
                    accountDetails.setAmount(loanApplicant.getLoanAmount());
                    if(customerService.creditAccount(accountDetails)){


                        loanApplicant.setLoanStatus(TransactionStatus.APPROVED.name());
                        loanApplicantRepository.save(loanApplicant);

                    }

                    return "Loan Approved for " + id;
                }
                else {

                    throw new CustomerException( "Customer not found for account number: " + loanApplicant.getCustomerAccountNumber());
                }
            }
            else{
                throw new LoanApplicantException("Insufficient balance to approve the loan. Please credit the required amount.");

            }
        }
        else {
            throw new LoanApplicantException("Loan not found for ID: " + id);
        }
    }
    public double getBankBalance(String accountNumber)throws CustomerException {
        log.info("getBankBalance method start");
        Customer customer = null;

            customer = customerRepository.findByAccountNumber(accountNumber);
            if (customer != null) {
                if (customer.getBalance() < 2000) {
                    String msg="Dear Customer," + "\n"+"Your bank balance is below the minimum required balance of 2000. Please ensure to maintain the minimum balance." + "\n" +"Sincerely," + "\n" +"Nandhini Bank of India";
                    sendEmail(customer.getEmail(),"Minimum Bank Balance Notification",msg);
                }
                return customer.getBalance();
            } else {
                // case where customer is not found
                throw new CustomerException("Customer not found for account number: " + accountNumber);
            }

    }
    public List<LoanApplicant> findEligibleCustomersWithApprovedLoan() {
        
        List<LoanApplicant> eligibleApplicants = loanApplicantRepository.findByLoanStatusAndEligiblityStatus(TransactionStatus.APPROVED.name(), "Eligible");

        return eligibleApplicants;
    }
}



