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
 * LoanApplicantdto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LoanApplicantdto   {
  @JsonProperty("customerAccountNumber")
  private String customerAccountNumber;

  @JsonProperty("loanType")
  private String loanType;

  @JsonProperty("loanAmount")
  private Double loanAmount;

  @JsonProperty("existing_emi_amt")
  private Long existingEmiAmt;

  @JsonProperty("tenure")
  private Integer tenure;

  public LoanApplicantdto customerAccountNumber(String customerAccountNumber) {
    this.customerAccountNumber = customerAccountNumber;
    return this;
  }

  /**
   * Get customerAccountNumber
   * @return customerAccountNumber
  */
  @ApiModelProperty(value = "")


  public String getCustomerAccountNumber() {
    return customerAccountNumber;
  }

  public void setCustomerAccountNumber(String customerAccountNumber) {
    this.customerAccountNumber = customerAccountNumber;
  }

  public LoanApplicantdto loanType(String loanType) {
    this.loanType = loanType;
    return this;
  }

  /**
   * Get loanType
   * @return loanType
  */
  @ApiModelProperty(value = "")


  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public LoanApplicantdto loanAmount(Double loanAmount) {
    this.loanAmount = loanAmount;
    return this;
  }

  /**
   * Get loanAmount
   * @return loanAmount
  */
  @ApiModelProperty(value = "")


  public Double getLoanAmount() {
    return loanAmount;
  }

  public void setLoanAmount(Double loanAmount) {
    this.loanAmount = loanAmount;
  }

  public LoanApplicantdto existingEmiAmt(Long existingEmiAmt) {
    this.existingEmiAmt = existingEmiAmt;
    return this;
  }

  /**
   * Get existingEmiAmt
   * @return existingEmiAmt
  */
  @ApiModelProperty(value = "")


  public Long getExistingEmiAmt() {
    return existingEmiAmt;
  }

  public void setExistingEmiAmt(Long existingEmiAmt) {
    this.existingEmiAmt = existingEmiAmt;
  }

  public LoanApplicantdto tenure(Integer tenure) {
    this.tenure = tenure;
    return this;
  }

  /**
   * Get tenure
   * @return tenure
  */
  @ApiModelProperty(value = "")


  public Integer getTenure() {
    return tenure;
  }

  public void setTenure(Integer tenure) {
    this.tenure = tenure;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoanApplicantdto loanApplicantdto = (LoanApplicantdto) o;
    return Objects.equals(this.customerAccountNumber, loanApplicantdto.customerAccountNumber) &&
        Objects.equals(this.loanType, loanApplicantdto.loanType) &&
        Objects.equals(this.loanAmount, loanApplicantdto.loanAmount) &&
        Objects.equals(this.existingEmiAmt, loanApplicantdto.existingEmiAmt) &&
        Objects.equals(this.tenure, loanApplicantdto.tenure);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerAccountNumber, loanType, loanAmount, existingEmiAmt, tenure);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoanApplicantdto {\n");
    
    sb.append("    customerAccountNumber: ").append(toIndentedString(customerAccountNumber)).append("\n");
    sb.append("    loanType: ").append(toIndentedString(loanType)).append("\n");
    sb.append("    loanAmount: ").append(toIndentedString(loanAmount)).append("\n");
    sb.append("    existingEmiAmt: ").append(toIndentedString(existingEmiAmt)).append("\n");
    sb.append("    tenure: ").append(toIndentedString(tenure)).append("\n");
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

