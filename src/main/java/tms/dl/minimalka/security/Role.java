package tms.dl.minimalka.security;

import com.google.common.collect.Sets;
import static tms.dl.minimalka.security.Permission.*;
import java.util.Set;

public enum Role {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(READ, WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
