package com.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account fromAccount;
    @ManyToOne
    private Account toAccount;
    private LocalDateTime timeStamp;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Type {
        IN_COMING, OUT_COMING, TOP_UP
    }
}
