package com.example.deliveryappproject.domain.menucategory.service;

import com.example.deliveryappproject.common.dto.AuthUser;
import com.example.deliveryappproject.common.exception.ForbiddenException;
import com.example.deliveryappproject.common.exception.NotFoundException;
import com.example.deliveryappproject.domain.menu.dto.response.MenuResponse;
import com.example.deliveryappproject.domain.menu.entity.Menu;
import com.example.deliveryappproject.domain.menu.service.MenuService;
import com.example.deliveryappproject.domain.menucategory.dto.response.MenuCategoryMenuResponse;
import com.example.deliveryappproject.domain.menucategory.entity.MenuCategory;
import com.example.deliveryappproject.domain.menucategory.entity.MenuCategoryMenu;
import com.example.deliveryappproject.domain.menucategory.repository.MenuCategoryMenuRepository;
import com.example.deliveryappproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MenuCategoryMenuService {

    private final MenuCategoryMenuRepository menuCategoryMenuRepository;
    private final MenuService menuService;
    private final MenuCategoryService menuCategoryService;

    public void createCategoryStore(AuthUser authUser, Long menuId, Long categoryId) {
        Menu findMenu = menuService.findByMenuIdOrElseThrow(menuId);

        if (!Objects.equals(findMenu.getUser().getId(), authUser.getId())) {
            throw new ForbiddenException("설정 가능한 유저가 아닙니다.");
        }

        MenuCategory findMenuCategory = menuCategoryService.findByIdOrElseThrow(categoryId);
        User user = new User(authUser.getId());

        MenuCategoryMenu menuCategoryMenu = new MenuCategoryMenu(user, findMenuCategory, findMenu);

        menuCategoryMenuRepository.save(menuCategoryMenu);
    }

    @Transactional
    public void deleteMenuCategoryMenu(AuthUser authUser, Long menuId, Long categoryId) {
        Menu findMenu = menuService.findByMenuIdOrElseThrow(menuId);

        if (!Objects.equals(findMenu.getUser().getId(), authUser.getId())) {
            throw new ForbiddenException("설정 가능한 유저가 아닙니다.");
        }

        MenuCategoryMenu menuCategoryMenu = findByIdOrThrowElse(categoryId);

        menuCategoryMenuRepository.delete(menuCategoryMenu);
    }

    private MenuCategoryMenu findByIdOrThrowElse(Long categoryId) {
        return menuCategoryMenuRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("Not Found Category")
        );
    }

    public MenuCategoryMenuResponse getMenuCategory(Long menuId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MenuCategoryMenu> menuCategoryMenuPage = menuCategoryMenuRepository.findByMenuId(menuId, pageable);

        String menuCategoryName = menuCategoryMenuPage.isEmpty() ? "" : menuCategoryMenuPage.getContent().get(0).getMenuCategory().getName();

        List<MenuResponse> menuList = menuCategoryMenuPage.getContent().stream()
                .map(menuCategoryMenu -> new MenuResponse(
                        menuCategoryMenu.getMenu().getId(),
                        menuCategoryMenu.getMenu().getMenuName(),
                        menuCategoryMenu.getMenu().getPrice(),
                        menuCategoryMenu.getMenu().getInformation(),
                        menuCategoryMenu.getMenu().getMenuState().name(),
                        menuCategoryMenu.getMenu().getStore().getStoreName()
                ))
                .toList();
        return MenuCategoryMenuResponse.fromDto(menuCategoryName, menuList);
    }
}

