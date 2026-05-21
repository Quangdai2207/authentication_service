package auth.service.domains.role.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleAuthorization {
    ROLE_SUPER_ADMIN("System Super Admin", "SUPER_ADMIN"),
    ROLE_ADMIN("Administrator", "ADMIN"),

    ROLE_MODERATOR("Moderator", "MODERATOR"),
    ROLE_SUPPORT("Support", "SUPPORT"),

    ROLE_EDITOR("Editor", "EDITOR"),
    ROLE_AUTHOR("Author", "AUTHOR"),
    ROLE_REVIEWER("Reviewer", "REVIEWER"),

    ROLE_CUSTOMER("Customer", "CUSTOMER"),
    ROLE_SELLER("Seller", "SELLER"),
    ROLE_VENDOR("Vendor", "VENDOR"),
    ROLE_WAREHOUSE("Warehouse", "WAREHOUSE"),
    ROLE_SHIPPER("Shipper", "SHIPPER"),

    ROLE_DEVOPS("DevOps", "DEVOPS"),
    ROLE_DEVELOPER("Developer", "DEVELOPER"),
    ROLE_ANALYSIS("Analysis", "ANALYSIS"),

    ROLE_MANAGER("Manager", "MANAGER"),
    ROLE_EMPLOYEE("Employee", "EMPLOYEE"),
    ROLE_AUDITOR("Auditor", "AUDITOR"),

    ROLE_USER("End User", "USER");

    @Getter
    private final String description;

    @Getter
    private final String name;
}
