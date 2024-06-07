package it.bankapp;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.bankapp.Customer;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;


import java.util.*;

/**
 * AccountDeletionRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AccountDeletionRequest   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("customer")
  private Customer customer;

  @JsonProperty("status")
  private String status;

  @JsonProperty("date")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime date;

  public AccountDeletionRequest id(Integer id) {
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

  public AccountDeletionRequest customer(Customer customer) {
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

  public AccountDeletionRequest status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public AccountDeletionRequest date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDeletionRequest accountDeletionRequest = (AccountDeletionRequest) o;
    return Objects.equals(this.id, accountDeletionRequest.id) &&
        Objects.equals(this.customer, accountDeletionRequest.customer) &&
        Objects.equals(this.status, accountDeletionRequest.status) &&
        Objects.equals(this.date, accountDeletionRequest.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, status, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDeletionRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

