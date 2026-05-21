package auth.service.response.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseV1<T> {
    private int status;
    private boolean success;
    private Object message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // TODO; RESPONSE OK
    public static <T> ResponseEntity<ApiResponseV1<T>> ok(T data) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(HttpStatus.OK.getReasonPhrase());
        body.setData(data);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> ok(T data, String message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(message);
        body.setData(data);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> ok(T data, Object meta, String message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(message);
        body.setMeta(meta);
        body.setData(data);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> ok(T data, Object meta) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(HttpStatus.OK.getReasonPhrase());
        body.setMeta(meta);
        body.setData(data);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> ok() {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(HttpStatus.OK.getReasonPhrase());
        body.setData(null);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> ok(String message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(message);
        body.setData(null);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    // TODO: CREATED RESPONSE
    public static <T> ResponseEntity<ApiResponseV1<T>> created(T data) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(HttpStatus.CREATED.getReasonPhrase());
        body.setData(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> created(T data, String message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.CREATED.value());
        body.setSuccess(true);
        body.setMessage(message);
        body.setData(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> created() {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.OK.value());
        body.setSuccess(true);
        body.setMessage(HttpStatus.CREATED.getReasonPhrase());
        body.setData(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    // TODO: NOT_FOUND RESPONSE
    public static <T> ResponseEntity<ApiResponseV1<T>> notFound() {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.NOT_FOUND.value());
        body.setSuccess(false);
        body.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        body.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> notFound(String message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.NOT_FOUND.value());
        body.setSuccess(false);
        body.setMessage(message);
        body.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // TODO: INTERNAL_SERVER_ERROR RESPONSE
    public static <T> ResponseEntity<ApiResponseV1<T>> internalServerError() {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.setSuccess(false);
        body.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.setData(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> internalServerError(Object message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.setSuccess(false);
        body.setMessage(message);
        body.setData(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    // TODO: BAD_REQUEST RESPONSE
    public static <T> ResponseEntity<ApiResponseV1<T>> badRequest() {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.BAD_REQUEST.value());
        body.setSuccess(false);
        body.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> badRequest(Object message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.BAD_REQUEST.value());
        body.setSuccess(false);
        body.setMessage(message);
        body.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> unauthorize(T data) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.UNAUTHORIZED.value());
        body.setSuccess(false);
        body.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        body.setData(data);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> unauthorize() {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.UNAUTHORIZED.value());
        body.setSuccess(false);
        body.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    public static <T> ResponseEntity<ApiResponseV1<T>> unauthorize(String message) {
        ApiResponseV1<T> body = new ApiResponseV1<>();
        body.setStatus(HttpStatus.UNAUTHORIZED.value());
        body.setSuccess(false);
        body.setMessage(message);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
    
}
