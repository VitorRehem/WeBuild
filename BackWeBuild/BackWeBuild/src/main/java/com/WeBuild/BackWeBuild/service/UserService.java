package com.WeBuild.BackWeBuild.service;

import com.WeBuild.BackWeBuild.model.User;
import com.WeBuild.BackWeBuild.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    // O PasswordEncoder é necessário para carregar o usuário, mas a injeção não está aqui
    // Ele será usado no AuthController e SecurityConfig.

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail: " + email));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}