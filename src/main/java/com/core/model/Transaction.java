package com.core.model;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "from_account")
    private Long fromAccount;
    @Column(name = "to_account")
    private Long toAccount;
    private LocalDateTime dateTime;
    private Long amount;
    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Type {
        INCOMING, OUTCOMING
    }
}
