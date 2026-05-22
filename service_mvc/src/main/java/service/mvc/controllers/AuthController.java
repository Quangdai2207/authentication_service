package service.mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.mvc.dtos.response.RequestLoginResponse;
import service.reaction.request.ServiceRequestLogin;
import tools.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    // Gia su rang service "service_mvc" da dang ky su dung dich vu Authentication Service va co auth-service-id
    private final static String authServiceId = "authentication service id";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @GetMapping("/login")
    public String login() {
        return "views/auth/login";
    }

    @PostMapping(value = "/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpMethod httpMethod) {

        // Dung RestTemplate truyen du lieu nguoi dung dang nhap gui len cho serive authentication service
        ServiceRequestLogin body = ServiceRequestLogin
                .builder()
                .email(email)
                .password(password)
                .authServiceId(authServiceId)
                .build();

        /**
         * Khi dung RestTemplate voi methods call Api, phuong thuc gom 3-4 tham so doi tuong
         *  1. Connection String URL/ Endpoints.
         *  2. Request Body
         *  3. Doi tuong nhan phan hoi Response. Thuong thi nen Copy doi tuong phan hoi tu service dang call Api
         * */
        ResponseEntity<RequestLoginResponse> response = restTemplate.postForEntity(
                "http://authentication-service/auth-service/auth/local",
                body, //? Request Body
                RequestLoginResponse.class //? Response Object type corresponding
        );

        System.out.println(response.getBody().getData());
        return "views/auth/success";
    }
}
