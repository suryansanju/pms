package com.nic.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.pms.model.Role;



@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
}