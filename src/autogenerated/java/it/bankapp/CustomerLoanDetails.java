package it.bankapp;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.bankapp.Customer;
import it.bankapp.LoanApplicant;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;


import java.util.*;

/**
 * CustomerLoanDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CustomerLoanDetails   {
  @JsonProperty("customer")
  private Customer customer;

  @JsonProperty("loans")
  @Valid
  private List<LoanApplicant> loans = null;

  public CustomerLoanDetails customer(Customer customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  */
  @ApiModelProperty(value = "")

  @Valid

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public CustomerLoanDetails loans(List<LoanApplicant> loans) {
    this.loans = loans;
    return this;
  }

  public CustomerLoanDetails addLoansItem(LoanApplicant loansItem) {
    if (this.loans == null) {
      this.loans = new ArrayList<>();
    }
    this.loans.add(loansItem);
    return this;
  }

  /**
   * Get loans
   * @return loans
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<LoanApplicant> getLoans() {
    return loans;
  }

  public void setLoans(List<LoanApplicant> loans) {
    this.loans = loans;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerLoanDetails customerLoanDetails = (CustomerLoanDetails) o;
    return Objects.equals(this.customer, customerLoanDetails.customer) &&
        Objects.equals(this.loans, customerLoanDetails.loans);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, loans);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerLoanDetails {\n");
    
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    loans: ").append(toIndentedString(loans)).append("\n");
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

