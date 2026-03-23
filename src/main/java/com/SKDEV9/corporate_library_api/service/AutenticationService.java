package com.SKDEV9.corporate_library_api.service;

import com.SKDEV9.corporate_library_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AutenticationService implements UserDetailsService {

    private final UserRepository repository;


    public AutenticationService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aqui chamamos aquele método que você criou no UserRepository para procurar pelo email
        return repository.findByEmail(username);
    }
}
