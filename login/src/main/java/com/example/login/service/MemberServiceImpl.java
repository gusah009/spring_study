package com.example.login.service;

import com.example.login.service.security.UserEntity;
import com.example.login.service.security.UserRoleEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public UserEntity getUser(String loginUserId) {
        return new UserEntity("carami", "$2a$10$G/ADAGLU3vKBd62E6GbrgetQpEKu2ukKgiDR5TWHYwrem0cSv6Z8m");
    }

    @Override
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<UserRoleEntity> list = new ArrayList<>();
        list.add(new UserRoleEntity("carami", "ROLE_USER"));
        return list;
    }
}