package auth.service.handler;

import auth.service.response.v1.ApiResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.reaction.request.ServiceRequestLogin;
import tools.jackson.databind.ObjectMapper;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth-service/auth") // http://localhost:8080/auth-service/auth/local
public class HandlerController {
    private final ObjectMapper objectMapper;

    @PostMapping(
            value = "/local",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponseV1<ServiceRequestLogin>> local(
            @RequestBody ServiceRequestLogin body
    ) {
        System.out.println("Full Body ServiceRequestLogin: " + body.toString());
        System.out.println("Email: " + body.getEmail());
        System.out.println("Password " + body.getPassword());
        System.out.println("AuthServiceId " + body.getAuthServiceId());
        return ApiResponseV1.ok(body, "Nhan duoc roi nha nhoc");
    }
}
