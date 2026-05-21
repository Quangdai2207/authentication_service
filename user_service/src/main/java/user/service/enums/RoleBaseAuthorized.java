package user.service.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleBaseAuthorized {

    ROLE_SUPER_ADMIN("System Super Admin"),
    ROLE_ADMIN("Administrator"),

    ROLE_MODERATOR("Moderator"),
    ROLE_SUPPORT("Support"),

    ROLE_EDITOR("Editor"),
    ROLE_AUTHOR("Author"),
    ROLE_REVIEWER("Reviewer"),

    ROLE_CUSTOMER("Customer"),
    ROLE_SELLER("Seller"),
    ROLE_VENDOR("Vendor"),
    ROLE_WAREHOUSE("Warehouse"),
    ROLE_SHIPPER("Shipper"),

    ROLE_DEVOPS("DevOps"),
    ROLE_DEVELOPER("Developer"),
    ROLE_ANALYSIS("Analysis"),

    ROLE_MANAGER("Manager"),
    ROLE_EMPLOYEE("Employee"),
    ROLE_AUDITOR("Auditor"),

    ROLE_USER("End User");

    @Getter
    private final String description;
}
