package com.example.deliveryappproject.domain.menucategory.entity;

import com.example.deliveryappproject.common.entity.Timestamped;
import com.example.deliveryappproject.domain.menu.entity.Menu;
import com.example.deliveryappproject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(
        name = "menu_category_menus",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"menu_category_id", "menu_id"})
        }
)
public class MenuCategoryMenu extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id")
    private MenuCategory menuCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MenuCategoryMenu(User user, MenuCategory menuCategory, Menu menu) {
        this.user = user;
        this.menuCategory = menuCategory;
        this.menu = menu;
    }

    //    public CategoryStore(User user, Category category, Store store) {
//        this.user = user;
//        this.category = category;
//        this.store = store;
//    }

}