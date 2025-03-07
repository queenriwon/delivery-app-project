package com.example.deliveryappproject.domain.menucategory.repository;

import com.example.deliveryappproject.domain.menucategory.entity.MenuCategoryMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuCategoryMenuRepository extends JpaRepository<MenuCategoryMenu, Long> {

    @Query("SELECT m FROM MenuCategoryMenu m " +
            "LEFT JOIN FETCH m.menu s " +
            "WHERE m.menu.id = :menuId " +
            "ORDER BY m.modifiedAt DESC")
    Page<MenuCategoryMenu> findByMenuId(@Param("menuId") Long menuId, Pageable pageable);
}

