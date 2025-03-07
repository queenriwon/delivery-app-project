package com.example.deliveryappproject.domain.menucategory.service;

import com.example.deliveryappproject.common.dto.AuthUser;
import com.example.deliveryappproject.common.exception.ForbiddenException;
import com.example.deliveryappproject.common.exception.NotFoundException;
import com.example.deliveryappproject.domain.category.dto.request.CategoryUpdateRequest;
import com.example.deliveryappproject.domain.menucategory.dto.request.MenuCategoryCreateRequest;
import com.example.deliveryappproject.domain.menucategory.entity.MenuCategory;
import com.example.deliveryappproject.domain.menucategory.repository.MenuCategoryRepository;
import com.example.deliveryappproject.domain.store.entity.Store;
import com.example.deliveryappproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;

    @Transactional
    public void createMenuCategory(
            AuthUser authUser,
            Long storeId,
            MenuCategoryCreateRequest menuCategoryCreateRequest
    ) {
        User user = new User(authUser.getId());
        Store store = new Store(storeId);
        MenuCategory menuCategory = MenuCategoryCreateRequest.toEntity(user, store, menuCategoryCreateRequest);

        menuCategoryRepository.save(menuCategory);
    }

    @Transactional
    public void updateMenuCategory(
            AuthUser authUser,
            Long menuCategoryId,
            CategoryUpdateRequest categoryUpdateRequest
    ) {
        MenuCategory findMenuCategory = findByIdOrElseThrow(menuCategoryId);

        if (!Objects.equals(findMenuCategory.getUser().getId(), authUser.getId())) {
            throw new ForbiddenException("삭제 가능한 유저가 아닙니다.");
        }
        findMenuCategory.updateMenuCategoryName(categoryUpdateRequest.getName());
    }

    public void deleteMenuCategory(AuthUser authUser, Long menuCategoryId) {
        MenuCategory findMenuCategory = findByIdOrElseThrow(menuCategoryId);

        if (!Objects.equals(findMenuCategory.getUser().getId(), authUser.getId())) {
            throw new ForbiddenException("삭제 가능한 유저가 아닙니다.");
        }
        menuCategoryRepository.delete(findMenuCategory);
    }

    public MenuCategory findByIdOrElseThrow(Long menuCategoryId) {
        return menuCategoryRepository.findById(menuCategoryId).orElseThrow(
                () -> new NotFoundException("Not Found Menu Category")
        );
    }
}
