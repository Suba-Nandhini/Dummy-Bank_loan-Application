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
 * AccountDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AccountDetails   {
  @JsonProperty("senderAccountNumber")
  private String senderAccountNumber;

  @JsonProperty("receiverAccountNumber")
  private String receiverAccountNumber;

  @JsonProperty("amount")
  private Double amount;

  public AccountDetails senderAccountNumber(String senderAccountNumber) {
    this.senderAccountNumber = senderAccountNumber;
    return this;
  }

  /**
   * Get senderAccountNumber
   * @return senderAccountNumber
  */
  @ApiModelProperty(value = "")


  public String getSenderAccountNumber() {
    return senderAccountNumber;
  }

  public void setSenderAccountNumber(String senderAccountNumber) {
    this.senderAccountNumber = senderAccountNumber;
  }

  public AccountDetails receiverAccountNumber(String receiverAccountNumber) {
    this.receiverAccountNumber = receiverAccountNumber;
    return this;
  }

  /**
   * Get receiverAccountNumber
   * @return receiverAccountNumber
  */
  @ApiModelProperty(value = "")


  public String getReceiverAccountNumber() {
    return receiverAccountNumber;
  }

  public void setReceiverAccountNumber(String receiverAccountNumber) {
    this.receiverAccountNumber = receiverAccountNumber;
  }

  public AccountDetails amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @ApiModelProperty(value = "")


  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDetails accountDetails = (AccountDetails) o;
    return Objects.equals(this.senderAccountNumber, accountDetails.senderAccountNumber) &&
        Objects.equals(this.receiverAccountNumber, accountDetails.receiverAccountNumber) &&
        Objects.equals(this.amount, accountDetails.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(senderAccountNumber, receiverAccountNumber, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDetails {\n");
    
    sb.append("    senderAccountNumber: ").append(toIndentedString(senderAccountNumber)).append("\n");
    sb.append("    receiverAccountNumber: ").append(toIndentedString(receiverAccountNumber)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

