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
 * LoanApplicant
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LoanApplicant   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("customerAccountNumber")
  private String customerAccountNumber;

  @JsonProperty("loanType")
  private String loanType;

  @JsonProperty("customerEmail")
  private String customerEmail;

  @JsonProperty("loanStatus")
  private String loanStatus;

  @JsonProperty("loanAmount")
  private Double loanAmount;

  @JsonProperty("rateOfInterest")
  private Float rateOfInterest;

  @JsonProperty("emiAmt")
  private Double emiAmt;

  @JsonProperty("existing_emi_amt")
  private Double existingEmiAmt;

  @JsonProperty("tenure")
  private Integer tenure;

  @JsonProperty("netSalary")
  private Long netSalary;

  @JsonProperty("eligiblityStatus")
  private String eligiblityStatus;

  @JsonProperty("cibilScore")
  private Integer cibilScore;

  public LoanApplicant id(Integer id) {
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

  public LoanApplicant customerAccountNumber(String customerAccountNumber) {
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

  public LoanApplicant loanType(String loanType) {
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

  public LoanApplicant customerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
    return this;
  }

  /**
   * Get customerEmail
   * @return customerEmail
  */
  @ApiModelProperty(value = "")


  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public LoanApplicant loanStatus(String loanStatus) {
    this.loanStatus = loanStatus;
    return this;
  }

  /**
   * Get loanStatus
   * @return loanStatus
  */
  @ApiModelProperty(value = "")


  public String getLoanStatus() {
    return loanStatus;
  }

  public void setLoanStatus(String loanStatus) {
    this.loanStatus = loanStatus;
  }

  public LoanApplicant loanAmount(Double loanAmount) {
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

  public LoanApplicant rateOfInterest(Float rateOfInterest) {
    this.rateOfInterest = rateOfInterest;
    return this;
  }

  /**
   * Get rateOfInterest
   * @return rateOfInterest
  */
  @ApiModelProperty(value = "")


  public Float getRateOfInterest() {
    return rateOfInterest;
  }

  public void setRateOfInterest(Float rateOfInterest) {
    this.rateOfInterest = rateOfInterest;
  }

  public LoanApplicant emiAmt(Double emiAmt) {
    this.emiAmt = emiAmt;
    return this;
  }

  /**
   * Get emiAmt
   * @return emiAmt
  */
  @ApiModelProperty(value = "")


  public Double getEmiAmt() {
    return emiAmt;
  }

  public void setEmiAmt(Double emiAmt) {
    this.emiAmt = emiAmt;
  }

  public LoanApplicant existingEmiAmt(Double existingEmiAmt) {
    this.existingEmiAmt = existingEmiAmt;
    return this;
  }

  /**
   * Get existingEmiAmt
   * @return existingEmiAmt
  */
  @ApiModelProperty(value = "")


  public Double getExistingEmiAmt() {
    return existingEmiAmt;
  }

  public void setExistingEmiAmt(Double existingEmiAmt) {
    this.existingEmiAmt = existingEmiAmt;
  }

  public LoanApplicant tenure(Integer tenure) {
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

  public LoanApplicant netSalary(Long netSalary) {
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

  public LoanApplicant eligiblityStatus(String eligiblityStatus) {
    this.eligiblityStatus = eligiblityStatus;
    return this;
  }

  /**
   * Get eligiblityStatus
   * @return eligiblityStatus
  */
  @ApiModelProperty(value = "")


  public String getEligiblityStatus() {
    return eligiblityStatus;
  }

  public void setEligiblityStatus(String eligiblityStatus) {
    this.eligiblityStatus = eligiblityStatus;
  }

  public LoanApplicant cibilScore(Integer cibilScore) {
    this.cibilScore = cibilScore;
    return this;
  }

  /**
   * Get cibilScore
   * @return cibilScore
  */
  @ApiModelProperty(value = "")


  public Integer getCibilScore() {
    return cibilScore;
  }

  public void setCibilScore(Integer cibilScore) {
    this.cibilScore = cibilScore;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoanApplicant loanApplicant = (LoanApplicant) o;
    return Objects.equals(this.id, loanApplicant.id) &&
        Objects.equals(this.customerAccountNumber, loanApplicant.customerAccountNumber) &&
        Objects.equals(this.loanType, loanApplicant.loanType) &&
        Objects.equals(this.customerEmail, loanApplicant.customerEmail) &&
        Objects.equals(this.loanStatus, loanApplicant.loanStatus) &&
        Objects.equals(this.loanAmount, loanApplicant.loanAmount) &&
        Objects.equals(this.rateOfInterest, loanApplicant.rateOfInterest) &&
        Objects.equals(this.emiAmt, loanApplicant.emiAmt) &&
        Objects.equals(this.existingEmiAmt, loanApplicant.existingEmiAmt) &&
        Objects.equals(this.tenure, loanApplicant.tenure) &&
        Objects.equals(this.netSalary, loanApplicant.netSalary) &&
        Objects.equals(this.eligiblityStatus, loanApplicant.eligiblityStatus) &&
        Objects.equals(this.cibilScore, loanApplicant.cibilScore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerAccountNumber, loanType, customerEmail, loanStatus, loanAmount, rateOfInterest, emiAmt, existingEmiAmt, tenure, netSalary, eligiblityStatus, cibilScore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoanApplicant {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customerAccountNumber: ").append(toIndentedString(customerAccountNumber)).append("\n");
    sb.append("    loanType: ").append(toIndentedString(loanType)).append("\n");
    sb.append("    customerEmail: ").append(toIndentedString(customerEmail)).append("\n");
    sb.append("    loanStatus: ").append(toIndentedString(loanStatus)).append("\n");
    sb.append("    loanAmount: ").append(toIndentedString(loanAmount)).append("\n");
    sb.append("    rateOfInterest: ").append(toIndentedString(rateOfInterest)).append("\n");
    sb.append("    emiAmt: ").append(toIndentedString(emiAmt)).append("\n");
    sb.append("    existingEmiAmt: ").append(toIndentedString(existingEmiAmt)).append("\n");
    sb.append("    tenure: ").append(toIndentedString(tenure)).append("\n");
    sb.append("    netSalary: ").append(toIndentedString(netSalary)).append("\n");
    sb.append("    eligiblityStatus: ").append(toIndentedString(eligiblityStatus)).append("\n");
    sb.append("    cibilScore: ").append(toIndentedString(cibilScore)).append("\n");
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

