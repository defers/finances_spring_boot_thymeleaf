package com.defers.springboot.finances.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private UUID uid;

    private Long id;

    private String name;

    @OneToOne(targetEntity = Category.class)
    private Category parent;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "uid=" + uid +
                ", id=" + id +
                ", name='" + name + '}';
    }
}
