package com.example.deliveryappproject.domain.menucategory.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuCategoryUpdateRequest {

    @Size(max = 10, message = "카테고리 명은 10자 이하로 입력해주세요.")
    private String name;

}
