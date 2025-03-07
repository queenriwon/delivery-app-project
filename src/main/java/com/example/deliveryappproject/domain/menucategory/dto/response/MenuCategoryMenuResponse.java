package com.example.deliveryappproject.domain.menucategory.dto.response;

import com.example.deliveryappproject.domain.menu.dto.response.MenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MenuCategoryMenuResponse {

    private final String categoryName;
    private final List<MenuResponse> categoryMenus;

    public static MenuCategoryMenuResponse fromDto(String categoryName, List<MenuResponse> categoryMenus) {
        return MenuCategoryMenuResponse.builder()
                .categoryName(categoryName)
                .categoryMenus(categoryMenus)
                .build();
    }
}
