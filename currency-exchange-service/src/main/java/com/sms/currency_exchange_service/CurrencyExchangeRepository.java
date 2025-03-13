package com.sms.currency_exchange_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    @Query("select c from CurrencyExchange c where c.from = :from AND c.to = :to")
    CurrencyExchange findByFromAndTo(@Param("from")String from, @Param("to") String to);
}
