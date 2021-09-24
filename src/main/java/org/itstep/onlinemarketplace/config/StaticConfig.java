package org.itstep.onlinemarketplace.config;

import org.itstep.onlinemarketplace.entities.Role;

public class StaticConfig {
    public static final Role ROLE_USER = new Role(1L, "ROLE_USER", "User Role");
    public static final Role ROLE_ADMIN = new Role(2L, "ROLE_ADMIN", "Admin Role");

    private StaticConfig(){

    }
}
