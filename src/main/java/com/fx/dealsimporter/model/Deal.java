package com.fx.dealsimporter.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deal_unique_id", unique = true, nullable = false)
    private String dealUniqueId;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "deal_timestamp", nullable = false)
    private LocalDateTime dealTimestamp;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Deal() {}

    public Deal(String dealUniqueId, String fromCurrency, String toCurrency,
                LocalDateTime dealTimestamp, Double amount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealTimestamp = dealTimestamp;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public String getDealUniqueId() { return dealUniqueId; }
    public void setDealUniqueId(String dealUniqueId) { this.dealUniqueId = dealUniqueId; }

    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }

    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }

    public LocalDateTime getDealTimestamp() { return dealTimestamp; }
    public void setDealTimestamp(LocalDateTime dealTimestamp) { this.dealTimestamp = dealTimestamp; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
