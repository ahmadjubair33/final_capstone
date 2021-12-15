package com.knoldus.UserManagement.Dao;

import com.knoldus.UserManagement.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
}
