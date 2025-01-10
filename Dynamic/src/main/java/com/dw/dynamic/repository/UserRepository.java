package com.dw.dynamic.repository;

import com.dw.dynamic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
