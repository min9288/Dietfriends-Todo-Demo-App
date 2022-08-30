package com.DietfriendsTodoDemoApp.security.jwt.customUserDetails;

import com.DietfriendsTodoDemoApp.domain.user.entity.User;
import com.DietfriendsTodoDemoApp.domain.user.repository.UserRepository;
import com.DietfriendsTodoDemoApp.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
        private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);

            return CustomUserDetails.builder()
                    .userName(user.getUserName())
                    .password(user.getPassword())
                    .authorities(user.getRoles().stream()
                            .map(auth -> new SimpleGrantedAuthority(auth.toString())).collect(Collectors.toList()))
                    .build();
        }

}

