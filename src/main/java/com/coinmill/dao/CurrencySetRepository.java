package com.coinmill.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinmill.entity.CurrencySet;

@Repository
public interface CurrencySetRepository extends JpaRepository<CurrencySet,String>  {

}
