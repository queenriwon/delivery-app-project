package com.example.deliveryappproject.config.aop;


import com.example.deliveryappproject.common.annotation.Auth;
import com.example.deliveryappproject.common.annotation.AuthPermission;
import com.example.deliveryappproject.common.dto.AuthUser;
import com.example.deliveryappproject.common.response.Response;
import com.example.deliveryappproject.domain.store.dto.request.StoreCreateRequest;
import com.example.deliveryappproject.domain.user.enums.UserRole;
import jakarta.validation.Valid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
@AuthPermission(role = UserRole.OWNER)
@PostMapping
public Response<Void> createStore(
        @Auth AuthUser authUser,
        @Valid @RequestBody StoreCreateRequest storeCreateRequest
) {
    storeService.createStore(authUser, storeCreateRequest);
    return Response.empty();
}
 */

@Aspect
@Component
public class AuthPermissionAspect {

    // 어노테이션에서 경로를 지정하는 방법
    @Around("@annotation(com.example.deliveryappproject.config.aop.annotation.LogTrace)")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
    }

    // authPermission 과 매개변수 AuthPermission authPermission 매핑하는 방법
    @Around("@annotation(authPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, AuthPermission authPermission) throws Throwable {

        AuthUser authUser = (AuthUser) joinPoint.getArgs()[0];
        // joinPoint의 첫번째 인자를 가져옴
        // ProceedingJoinPoint: 메서드 실행 정보를 담고 있는 객체
        // joinPoint.getArgs(): joinPoint의 첫번째 매개변수를 가져옴

        //

    }
}




/*
@Aspect
@Component
public class AuthPermissionAspect {

    // @AuthPermission 어노테이션이 붙은 메소드가 실행될 때마다 권한 체크
    @Around("@annotation(authPermission)")  // @AuthPermission 어노테이션을 찾음
    public Object checkPermission(ProceedingJoinPoint joinPoint, AuthPermission authPermission) throws Throwable {
        // 인증된 사용자 정보 가져오기
        AuthUser authUser = (AuthUser) joinPoint.getArgs()[0];  // 첫 번째 인자가 AuthUser로 들어오는 경우

        // 권한이 일치하지 않으면 예외를 발생시킴
        if (authUser == null || !authUser.getUserRole().equals(authPermission.role())) {
            throw new ForbiddenException("권한이 없습니다.");
        }

        // 권한 체크가 통과되면 원래의 메소드 실행
        return joinPoint.proceed();
    }
}


*/
