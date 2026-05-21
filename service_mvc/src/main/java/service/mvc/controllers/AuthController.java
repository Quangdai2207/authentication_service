package service.mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.reaction.request.ServiceRequestLogin;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    // Gia su rang service "service_mvc" da dang ky su dung dich vu Authentication Service va co auth-service-id
    private final static String authServiceId = "authentication service id";
    private final ObjectMapper objectMapper;

    @GetMapping("/login")
    public String login() {
        return "views/auth/login";
    }

    @PostMapping(value = "/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {
        // Dung RestTemplate truyen du lieu nguoi dung dang nhap gui len cho serive authentication service
        RestTemplate restTemplate = new RestTemplate();
        ServiceRequestLogin loginRequest = ServiceRequestLogin
                .builder()
                .email(email)
                .password(password)
                .authServiceId(authServiceId).build();

        try {
            // Convert object -> json string
            String json = objectMapper.writeValueAsString(loginRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> response = restTemplate
                    .postForEntity(
                            "http://localhost:8080/auth-service/auth/local",
                            entity,
                            String.class
                    );

            // Kiem tra phan hoi
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "views/auth/success";
    }
}
