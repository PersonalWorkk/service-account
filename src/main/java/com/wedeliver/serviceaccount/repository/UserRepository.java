package com.wedeliver.serviceaccount.repository;

import com.wedeliver.serviceaccount.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
