package com.example.deliveryappproject.domain.menucategory.repository;

import com.example.deliveryappproject.domain.menucategory.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
