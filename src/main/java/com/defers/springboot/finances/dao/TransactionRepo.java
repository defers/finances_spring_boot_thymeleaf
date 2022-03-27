package com.defers.springboot.finances.dao;

import com.defers.springboot.finances.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Category, Long> {
}
