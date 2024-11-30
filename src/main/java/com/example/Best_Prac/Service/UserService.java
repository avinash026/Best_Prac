package com.example.Best_Prac.Service;

import org.springframework.stereotype.Service;

import com.example.Best_Prac.Entity.UserModel;
import com.example.Best_Prac.Repo.RoleRepository;
import com.example.Best_Prac.Repo.UserRepository;

@Service
public class UserService {


    private UserRepository userRepository;


    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserModel getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}