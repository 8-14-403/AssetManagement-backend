package com.htjn.assetManagement.repository;

import com.htjn.assetManagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by caojy on 2017/12/11.
 */
public interface UserRepository extends JpaRepository<Users, String> {


    Users findByUsername(String username);

    @Query(value = "select password from Users WHERE user_id = ?1", nativeQuery = true)
    String getPassword(String userId);

    @Query(value = "update Users set password = ?2 where user_id = ?1", nativeQuery = true)
    @Modifying
    void updatePassword(String userId, String newPassword);
}
