package com.ccs3402.adv_spring_security.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ccs3402.adv_spring_security.dto.UserDto;
import com.ccs3402.adv_spring_security.model.Role;
import com.ccs3402.adv_spring_security.model.User;
import com.ccs3402.adv_spring_security.repository.RoleRepository;
import com.ccs3402.adv_spring_security.repository.UserRepository;
import com.ccs3402.adv_spring_security.util.TbConstants;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Role.USER);

        if (role == null) {
            role = roleRepository.save(new Role(TbConstants.Role.USER));
        }

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));

        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
