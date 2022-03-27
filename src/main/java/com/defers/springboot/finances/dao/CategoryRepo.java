package com.defers.springboot.finances.dao;

import com.defers.springboot.finances.entity.Category;
import com.defers.springboot.finances.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends CrudRepository<Category, UUID> {

    List<Category> findByName(String name);

    Optional<Category> findByUid(UUID uid);

}
