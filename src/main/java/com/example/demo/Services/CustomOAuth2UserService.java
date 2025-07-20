package com.example.demo.Services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository userRepository;

    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");

        // Save new user if not exists
        User existingUser = userRepository.findByUsername(email);
        if (existingUser == null) {
            User newUser = new User();
            newUser.setName(name);
            newUser.setUsername(email);
            newUser.setPassword(null);  // OAuth users don’t have a password
            userRepository.save(newUser);
        }

        // Assign ROLE_USER authority and wrap into a valid OAuth2User implementation
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email"  // use email as the unique identifier
        );
    }
}
