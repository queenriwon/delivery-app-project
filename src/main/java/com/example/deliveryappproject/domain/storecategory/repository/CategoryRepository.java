package com.example.deliveryappproject.domain.storecategory.repository;

import com.example.deliveryappproject.domain.storecategory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
