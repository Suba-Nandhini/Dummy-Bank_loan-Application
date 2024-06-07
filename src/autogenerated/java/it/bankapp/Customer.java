package it.bankapp;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;


import java.util.*;

/**
 * Customer
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Customer   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("email")
  private String email;

  @JsonProperty("netSalary")
  private Long netSalary;

  @JsonProperty("phoneNumber")
  private String phoneNumber;

  @JsonProperty("panCard")
  private String panCard;

  @JsonProperty("accountNumber")
  private String accountNumber;

  @JsonProperty("residentYears")
  private Integer residentYears;

  @JsonProperty("workingYears")
  private Integer workingYears;

  @JsonProperty("havingAccount")
  private Boolean havingAccount;

  @JsonProperty("customerType")
  private String customerType;

  @JsonProperty("balance")
  private Double balance;

  /**
   * Gets or Sets loanStatus
   */
  public enum LoanStatusEnum {
    PENDING("PENDING"),
    
    APPROVED("APPROVED"),
    
    COMPLETED("COMPLETED"),
    
    FAILED("FAILED");

    private String value;

    LoanStatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LoanStatusEnum fromValue(String value) {
      for (LoanStatusEnum b : LoanStatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("loanStatus")
  private LoanStatusEnum loanStatus;

  @JsonProperty("debit")
  private Double debit;

  public Customer id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Customer name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Customer email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer netSalary(Long netSalary) {
    this.netSalary = netSalary;
    return this;
  }

  /**
   * Get netSalary
   * @return netSalary
  */
  @ApiModelProperty(value = "")


  public Long getNetSalary() {
    return netSalary;
  }

  public void setNetSalary(Long netSalary) {
    this.netSalary = netSalary;
  }

  public Customer phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @ApiModelProperty(value = "")


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Customer panCard(String panCard) {
    this.panCard = panCard;
    return this;
  }

  /**
   * Get panCard
   * @return panCard
  */
  @ApiModelProperty(value = "")


  public String getPanCard() {
    return panCard;
  }

  public void setPanCard(String panCard) {
    this.panCard = panCard;
  }

  public Customer accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
  */
  @ApiModelProperty(value = "")


  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Customer residentYears(Integer residentYears) {
    this.residentYears = residentYears;
    return this;
  }

  /**
   * Get residentYears
   * @return residentYears
  */
  @ApiModelProperty(value = "")


  public Integer getResidentYears() {
    return residentYears;
  }

  public void setResidentYears(Integer residentYears) {
    this.residentYears = residentYears;
  }

  public Customer workingYears(Integer workingYears) {
    this.workingYears = workingYears;
    return this;
  }

  /**
   * Get workingYears
   * @return workingYears
  */
  @ApiModelProperty(value = "")


  public Integer getWorkingYears() {
    return workingYears;
  }

  public void setWorkingYears(Integer workingYears) {
    this.workingYears = workingYears;
  }

  public Customer havingAccount(Boolean havingAccount) {
    this.havingAccount = havingAccount;
    return this;
  }

  /**
   * Get havingAccount
   * @return havingAccount
  */
  @ApiModelProperty(value = "")


  public Boolean getHavingAccount() {
    return havingAccount;
  }

  public void setHavingAccount(Boolean havingAccount) {
    this.havingAccount = havingAccount;
  }

  public Customer customerType(String customerType) {
    this.customerType = customerType;
    return this;
  }

  /**
   * Get customerType
   * @return customerType
  */
  @ApiModelProperty(value = "")


  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerType) {
    this.customerType = customerType;
  }

  public Customer balance(Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @ApiModelProperty(value = "")


  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Customer loanStatus(LoanStatusEnum loanStatus) {
    this.loanStatus = loanStatus;
    return this;
  }

  /**
   * Get loanStatus
   * @return loanStatus
  */
  @ApiModelProperty(value = "")


  public LoanStatusEnum getLoanStatus() {
    return loanStatus;
  }

  public void setLoanStatus(LoanStatusEnum loanStatus) {
    this.loanStatus = loanStatus;
  }

  public Customer debit(Double debit) {
    this.debit = debit;
    return this;
  }

  /**
   * Get debit
   * @return debit
  */
  @ApiModelProperty(value = "")


  public Double getDebit() {
    return debit;
  }

  public void setDebit(Double debit) {
    this.debit = debit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.id, customer.id) &&
        Objects.equals(this.name, customer.name) &&
        Objects.equals(this.email, customer.email) &&
        Objects.equals(this.netSalary, customer.netSalary) &&
        Objects.equals(this.phoneNumber, customer.phoneNumber) &&
        Objects.equals(this.panCard, customer.panCard) &&
        Objects.equals(this.accountNumber, customer.accountNumber) &&
        Objects.equals(this.residentYears, customer.residentYears) &&
        Objects.equals(this.workingYears, customer.workingYears) &&
        Objects.equals(this.havingAccount, customer.havingAccount) &&
        Objects.equals(this.customerType, customer.customerType) &&
        Objects.equals(this.balance, customer.balance) &&
        Objects.equals(this.loanStatus, customer.loanStatus) &&
        Objects.equals(this.debit, customer.debit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, netSalary, phoneNumber, panCard, accountNumber, residentYears, workingYears, havingAccount, customerType, balance, loanStatus, debit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    netSalary: ").append(toIndentedString(netSalary)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    panCard: ").append(toIndentedString(panCard)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    residentYears: ").append(toIndentedString(residentYears)).append("\n");
    sb.append("    workingYears: ").append(toIndentedString(workingYears)).append("\n");
    sb.append("    havingAccount: ").append(toIndentedString(havingAccount)).append("\n");
    sb.append("    customerType: ").append(toIndentedString(customerType)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    loanStatus: ").append(toIndentedString(loanStatus)).append("\n");
    sb.append("    debit: ").append(toIndentedString(debit)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

