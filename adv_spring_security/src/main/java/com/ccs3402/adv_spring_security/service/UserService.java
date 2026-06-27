package com.ccs3402.adv_spring_security.service;

import com.ccs3402.adv_spring_security.dto.UserDto;
import com.ccs3402.adv_spring_security.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);
}
