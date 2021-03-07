package se.swcg.consultauction.security;

public enum SecurityPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    CONSULTANT_READ("consultant:read"),
    CONSULTANT_WRITE("consultant:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    SecurityPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
