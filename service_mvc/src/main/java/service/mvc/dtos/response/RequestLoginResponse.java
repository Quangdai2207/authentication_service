package service.mvc.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestLoginResponse<T> {
    private int status;
    private boolean success;
    private Object message;
    private Object meta;
    private T data;
}
