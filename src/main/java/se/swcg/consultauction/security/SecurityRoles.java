package se.swcg.consultauction.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static se.swcg.consultauction.security.SecurityPermissions.*;

// Sätter dom olika rollerna och vilka behörighter dom har
public enum SecurityRoles {
    //USER(Sets.newHashSet(USER_READ, USER_WRITE)),
    CLIENT(Sets.newHashSet(USER_READ, USER_WRITE, CLIENT_READ, CLIENT_WRITE)),
    CONSULTANT(Sets.newHashSet(USER_READ, USER_WRITE, CONSULTANT_READ, CONSULTANT_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, CLIENT_READ, CLIENT_WRITE, CONSULTANT_READ, CONSULTANT_WRITE, ADMIN_READ, ADMIN_WRITE));

    private final Set<SecurityPermissions> permissions;

    SecurityRoles(Set<SecurityPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<SecurityPermissions> getPermissions() {
        return permissions;
    }
}
