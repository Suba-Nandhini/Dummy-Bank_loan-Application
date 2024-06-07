package it.bankapp;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;


import java.util.*;

/**
 * Transaction
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Transaction   {
  @JsonProperty("customerId")
  private Integer customerId;

  @JsonProperty("senderAccountNumber")
  private String senderAccountNumber;

  @JsonProperty("receiverAccountNumber")
  private String receiverAccountNumber;

  @JsonProperty("transactionTime")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime transactionTime;

  @JsonProperty("amount")
  private Double amount;

  /**
   * Gets or Sets transactionType
   */
  public enum TransactionTypeEnum {
    CREDIT("CREDIT"),
    
    DEBIT("DEBIT");

    private String value;

    TransactionTypeEnum(String value) {
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
    public static TransactionTypeEnum fromValue(String value) {
      for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("transactionType")
  private TransactionTypeEnum transactionType;

  public Transaction customerId(Integer customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  */
  @ApiModelProperty(value = "")


  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Transaction senderAccountNumber(String senderAccountNumber) {
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

  public Transaction receiverAccountNumber(String receiverAccountNumber) {
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

  public Transaction transactionTime(OffsetDateTime transactionTime) {
    this.transactionTime = transactionTime;
    return this;
  }

  /**
   * Get transactionTime
   * @return transactionTime
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(OffsetDateTime transactionTime) {
    this.transactionTime = transactionTime;
  }

  public Transaction amount(Double amount) {
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

  public Transaction transactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Get transactionType
   * @return transactionType
  */
  @ApiModelProperty(value = "")


  public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.customerId, transaction.customerId) &&
        Objects.equals(this.senderAccountNumber, transaction.senderAccountNumber) &&
        Objects.equals(this.receiverAccountNumber, transaction.receiverAccountNumber) &&
        Objects.equals(this.transactionTime, transaction.transactionTime) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.transactionType, transaction.transactionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, senderAccountNumber, receiverAccountNumber, transactionTime, amount, transactionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    senderAccountNumber: ").append(toIndentedString(senderAccountNumber)).append("\n");
    sb.append("    receiverAccountNumber: ").append(toIndentedString(receiverAccountNumber)).append("\n");
    sb.append("    transactionTime: ").append(toIndentedString(transactionTime)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
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

