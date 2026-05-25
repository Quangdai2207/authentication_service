package auth.service.domains.auth.controller.webMvc.v1;

import auth.service.domains.auth.service.ValidateSubscription;
import auth.service.domains.sub.service.SubService;
import auth.service.response.v1.ApiResponseV1;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.reaction.request.ServiceRequestLogin;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth-service/auth/mvc/local")
public class WebMvcController {
    private final ValidateSubscription validateSubscription;
    private final SubService subService;

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponseV1<ServiceRequestLogin>> mvcLocalLogin(
            @RequestBody ServiceRequestLogin body, HttpServletRequest request
    ) {

        System.out.println("Full Body ServiceRequestLogin: " + body.toString());
        System.out.println("WebMVC Controller");
        validateSubscription.validateSubs(request, body.getAuthServiceId(), subService);

        return ApiResponseV1.ok(body, "Nhan duoc roi nha nhoc");
    }
}
