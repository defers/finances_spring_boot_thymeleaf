package com.defers.springboot.finances.dao;

import com.defers.springboot.finances.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {

    List<Currency> findByName(String name);
}
