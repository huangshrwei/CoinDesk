package com.coinmill.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "currency_set")
@Data
@Entity
public class CurrencySet {

    @Id
    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private Date creationDate;   
    
    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;  
    
}
