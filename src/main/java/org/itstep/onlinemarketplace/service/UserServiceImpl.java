package org.itstep.onlinemarketplace.service;

import org.itstep.onlinemarketplace.entities.DbUser;
import org.itstep.onlinemarketplace.entities.Role;
import org.itstep.onlinemarketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@EnableWebSecurity
public class UserServiceImpl implements UserService {


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DbUser getUser(String email) {
        return userRepository.findDbUserByEmail(email);
    }

    @Override
    public DbUser registerUser(DbUser dbUser) {
        DbUser checkDbUser = userRepository.findDbUserByEmail(dbUser.getEmail());
        if (Objects.isNull(checkDbUser)) {
            dbUser.setPassword(passwordEncoder().encode(dbUser.getPassword()));
            return userRepository.save(dbUser);
        }
        return null;
    }

    @Override
    public DbUser updateUser(DbUser user) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public List<DbUser> getUsers() {
        return null;
    }

    @Override
    public List<DbUser> getUsersPaged(int currentPage, int length, Role role) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DbUser dbUser = userRepository.findDbUserByEmail(email);
        if(Objects.nonNull(dbUser)){
            User securityDbUser = new User(dbUser.getEmail(), dbUser.getPassword(), dbUser.getRoles());
            return securityDbUser;
        }
        else{
            return null;
        }
    }
}
