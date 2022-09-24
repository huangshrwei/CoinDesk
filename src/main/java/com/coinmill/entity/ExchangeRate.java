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
@Table(name = "exchange_rate")
@Data
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	    
    @Column(name = "rate_id", nullable = false)
    private Long rateId;

    @Column(name = "chart_name", nullable = false)
    private String chartName;

    @Column(name = "disclaimer")
    private String disclaimer;

    @Column(name = "rate_time")
    private Date rateTime;      
    
    @Column(name = "updated")
    private String updated;
    
    @Column(name = "updated_iso")
    private String updatedIso;
    
    @Column(name = "updateduk")
    private String updateduk;    
    
    @Column(name = "currency_code", nullable = false)
    private String currencyCode;
    
    @Column(name = "currency_name", nullable = false)
    private String currencyName;       
        
    @Column(name = "currency_rate", nullable = false)
    private Double currencyRate;    

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private Date creationDate;   
    
    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;  
	
}
