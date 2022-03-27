package com.defers.springboot.finances.dao;

import com.defers.springboot.finances.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountRepo extends CrudRepository<Account, Long> {

    List<Account> findByName(String name);

}
