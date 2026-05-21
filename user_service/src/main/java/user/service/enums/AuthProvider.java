package user.service.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthProvider {
    LOCAL("LOCAL"),
    GOOGLE("GOOGLE"),
    X("X"),
    FACEBOOK("FACEBOOK"),
    GITHUB("GITHUB"),
    APPLE("APPLE");

    @Getter
    private final String name;
}
