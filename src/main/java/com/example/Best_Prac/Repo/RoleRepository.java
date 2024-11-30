package com.example.Best_Prac.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Best_Prac.Entity.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    RoleModel findByRolename(String rolename);
}
