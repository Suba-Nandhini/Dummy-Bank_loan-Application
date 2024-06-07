package it.bankapp;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;


import java.util.*;

/**
 * Customerdto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Customerdto   {
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

  @JsonProperty("balance")
  private Double balance;

  @JsonProperty("havingAccount")
  private Boolean havingAccount;

  public Customerdto name(String name) {
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

  public Customerdto email(String email) {
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

  public Customerdto netSalary(Long netSalary) {
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

  public Customerdto phoneNumber(String phoneNumber) {
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

  public Customerdto panCard(String panCard) {
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

  public Customerdto accountNumber(String accountNumber) {
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

  public Customerdto residentYears(Integer residentYears) {
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

  public Customerdto workingYears(Integer workingYears) {
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

  public Customerdto balance(Double balance) {
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

  public Customerdto havingAccount(Boolean havingAccount) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customerdto customerdto = (Customerdto) o;
    return Objects.equals(this.name, customerdto.name) &&
        Objects.equals(this.email, customerdto.email) &&
        Objects.equals(this.netSalary, customerdto.netSalary) &&
        Objects.equals(this.phoneNumber, customerdto.phoneNumber) &&
        Objects.equals(this.panCard, customerdto.panCard) &&
        Objects.equals(this.accountNumber, customerdto.accountNumber) &&
        Objects.equals(this.residentYears, customerdto.residentYears) &&
        Objects.equals(this.workingYears, customerdto.workingYears) &&
        Objects.equals(this.balance, customerdto.balance) &&
        Objects.equals(this.havingAccount, customerdto.havingAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, netSalary, phoneNumber, panCard, accountNumber, residentYears, workingYears, balance, havingAccount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customerdto {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    netSalary: ").append(toIndentedString(netSalary)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    panCard: ").append(toIndentedString(panCard)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    residentYears: ").append(toIndentedString(residentYears)).append("\n");
    sb.append("    workingYears: ").append(toIndentedString(workingYears)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    havingAccount: ").append(toIndentedString(havingAccount)).append("\n");
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

