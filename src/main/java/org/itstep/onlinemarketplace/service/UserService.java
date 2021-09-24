package org.itstep.onlinemarketplace.service;

import org.itstep.onlinemarketplace.entities.DbUser;
import org.itstep.onlinemarketplace.entities.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    DbUser getUser(String email);

    DbUser registerUser(DbUser user);

    DbUser updateUser(DbUser user);

    boolean deleteUser(Long id);

    List<DbUser> getUsers();

    List<DbUser> getUsersPaged(int currentPage, int length, Role role);
}
