package com.fx.dealsimporter.dto;

public class DealRequestDto {

    private String dealUniqueId;
    private String fromCurrency;
    private String toCurrency;
    private String dealTimestamp;
    private Double amount;

    public DealRequestDto() {}

    public String getDealUniqueId() { return dealUniqueId; }
    public void setDealUniqueId(String dealUniqueId) { this.dealUniqueId = dealUniqueId; }

    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }

    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }

    public String getDealTimestamp() { return dealTimestamp; }
    public void setDealTimestamp(String dealTimestamp) { this.dealTimestamp = dealTimestamp; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
