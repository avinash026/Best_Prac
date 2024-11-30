package com.example.Best_Prac.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Best_Prac.Entity.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	//User findByUserName(String username);
    UserModel findByEmail(String email);
    //void deleteByUserName(String username);
}
