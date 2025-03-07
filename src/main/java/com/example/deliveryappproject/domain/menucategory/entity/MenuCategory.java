package com.example.deliveryappproject.domain.menucategory.entity;

import com.example.deliveryappproject.common.entity.Timestamped;
import com.example.deliveryappproject.domain.store.entity.Store;
import com.example.deliveryappproject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_categorys")
public class MenuCategory extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(unique = true)
    private String name;

    @Builder
    public MenuCategory(User user, Store store, String name) {
        this.user = user;
        this.store = store;
        this.name = name;
    }
    //
//    public Category(Long id) {
//        this.id = id;
//    }
//
    public void updateMenuCategoryName(String name) {
        this.name = name;
    }
}
