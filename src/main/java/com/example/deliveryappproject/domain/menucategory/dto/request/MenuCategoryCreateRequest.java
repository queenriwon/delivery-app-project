package com.example.deliveryappproject.domain.menucategory.dto.request;

import com.example.deliveryappproject.domain.menucategory.entity.MenuCategory;
import com.example.deliveryappproject.domain.store.entity.Store;
import com.example.deliveryappproject.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuCategoryCreateRequest {
    @NotBlank(message = "카테고리 명은 필수 입력 값입니다.")
    @Size(max = 10, message = "카테고리 명은 10자 이하로 입력해주세요.")
    private String name;

    public static MenuCategory toEntity(User user, Store store, MenuCategoryCreateRequest dto) {
        return MenuCategory.builder()
                .user(user)
                .store(store)
                .name(dto.getName())
                .build();
    }
}
