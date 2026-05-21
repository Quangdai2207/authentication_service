package auth.service.handler;

import auth.service.response.v1.ApiResponseV1;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.reaction.request.ServiceRequestLogin;


@RestController
@RequestMapping("/auth-service/auth") // http://localhost:8080/auth-service/auth/local
public class HandlerController {

    @PostMapping(
            value = "/local",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponseV1<Object>> local(
            @RequestBody ServiceRequestLogin body
    ) {
        System.out.println("Request from service_mvc: " + body.toString());
        return ApiResponseV1.ok(body, "Da nhan duoc du lieu tu Monolithic");
    }
}
