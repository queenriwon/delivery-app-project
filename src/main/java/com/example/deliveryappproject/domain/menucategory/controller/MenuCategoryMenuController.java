package com.example.deliveryappproject.domain.menucategory.controller;

import com.example.deliveryappproject.common.annotation.Auth;
import com.example.deliveryappproject.common.annotation.AuthPermission;
import com.example.deliveryappproject.common.dto.AuthUser;
import com.example.deliveryappproject.common.response.Response;
import com.example.deliveryappproject.domain.menucategory.dto.response.MenuCategoryMenuResponse;
import com.example.deliveryappproject.domain.menucategory.service.MenuCategoryMenuService;
import com.example.deliveryappproject.domain.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MenuCategoryMenuController {

    private final MenuCategoryMenuService menuCategoryMenuService;

    @AuthPermission(role = UserRole.OWNER)
    @PostMapping("menus/{menuId}/catetorys/{categoryId}/")
    public Response<Void> createMenuCategoryMenu(
            @Auth AuthUser authUser,
            @PathVariable Long menuId,
            @PathVariable Long categoryId
    ) {
        menuCategoryMenuService.createCategoryStore(authUser, menuId, categoryId);
        return Response.empty();
    }

    @AuthPermission(role = UserRole.OWNER)
    @DeleteMapping("menus/{menuId}/catetorys/{categoryId}/")
    public Response<Void> deleteMenuCategoryMenu(
            @Auth AuthUser authUser,
            @PathVariable Long menuId,
            @PathVariable Long categoryId
    ) {
        menuCategoryMenuService.deleteMenuCategoryMenu(authUser, menuId, categoryId);
        return Response.empty();
    }

    @GetMapping("menus/{menuId}/catetorys/")
    public Response<MenuCategoryMenuResponse> getMenuCategory(
            @PathVariable Long menuId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Response.of(menuCategoryMenuService.getMenuCategory(menuId, page, size));
    }

}

