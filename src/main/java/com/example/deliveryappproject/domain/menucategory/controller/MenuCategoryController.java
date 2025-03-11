package com.example.deliveryappproject.domain.menucategory.controller;

import com.example.deliveryappproject.common.annotation.Auth;
import com.example.deliveryappproject.common.annotation.AuthPermission;
import com.example.deliveryappproject.common.dto.AuthUser;
import com.example.deliveryappproject.common.response.Response;
import com.example.deliveryappproject.domain.storecategory.dto.request.CategoryUpdateRequest;
import com.example.deliveryappproject.domain.menucategory.dto.request.MenuCategoryCreateRequest;
import com.example.deliveryappproject.domain.menucategory.service.MenuCategoryService;
import com.example.deliveryappproject.domain.user.enums.UserRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @AuthPermission(role = UserRole.OWNER)
    @PostMapping("/stores/{storeId}/menu-categorys")
    public Response<Void> createMenuCategory(
            @Auth AuthUser authUser,
            @PathVariable Long storeId,
            @Valid @RequestBody MenuCategoryCreateRequest menuCategoryCreateRequest
    ) {
        menuCategoryService.createMenuCategory(authUser, storeId, menuCategoryCreateRequest);
        return Response.empty();
    }

    @AuthPermission(role = UserRole.OWNER)
    @PatchMapping("menu-categorys/{menuCategoryId}")
    public Response<Void> updateMenuCategory(
            @Auth AuthUser authUser,
            @PathVariable Long menuCategoryId,
            @Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest
    ) {
        menuCategoryService.updateMenuCategory(authUser, menuCategoryId, categoryUpdateRequest);
        return Response.empty();
    }

    @AuthPermission(role = UserRole.OWNER)
    @DeleteMapping("/menu-categorys/{menuCategoryId}")
    public Response<Void> deleteMenuCategory(
            @Auth AuthUser authUser,
            @PathVariable Long menuCategoryId
    ) {
        menuCategoryService.deleteMenuCategory(authUser, menuCategoryId);
        return Response.empty();
    }
}

