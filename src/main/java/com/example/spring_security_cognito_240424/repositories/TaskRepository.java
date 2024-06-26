package com.example.spring_security_cognito_240424.repositories;

import com.example.spring_security_cognito_240424.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCognitoUsername(String cognitoUsername);

    @Transactional
    void deleteByCognitoUsername(String cognitoUsername);
}
