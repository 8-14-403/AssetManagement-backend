package com.htjn.assetManagement.service;

import com.htjn.assetManagement.entity.Authority;
import com.htjn.assetManagement.entity.Users;
import com.htjn.assetManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by caojy on 2017/12/11.
 */
@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;// 密码编码器

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Value("${autoUnlock}")
    private int AUTO_UNLOCK;

    @Value("${loginErrorTimes}")
    private int MAX_ATTEMPT;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        Assert.notNull(userRepository, "UserDao 不能为空");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }


    public Page<Users> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Users getOne(String id){
        return userRepository.findOne(id);
    }


    public void save(Users users) {
        userRepository.save(users);
    }


    @Transactional
    public void save(Users users, boolean admin) {

        if (ObjectUtils.isEmpty(users.getUserId())) {
            users.setUserId(UUID.randomUUID().toString());
        }
        if (users.getUsername() != null && userRepository.findByUsername(users.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if (admin) {
            List<Authority> list = new ArrayList<>();
            Authority authority = new Authority();
            authority.setId("00010001-0001-0001-0002-000100020002");
            authority.setName("ROLE_ADMIN");
            Authority authority2 = new Authority();
            authority2.setId("00010001-0001-0001-0002-000100020001");
            authority2.setName("ROLE_USER");
            list.add(authority);
            list.add(authority2);
            users.setAuthorities(list);
        } else {
            List<Authority> list = new ArrayList<>();
            Authority authority = new Authority();
            authority.setId("00010001-0001-0001-0002-000100020001");
            authority.setName("ROLE_USER");
            list.add(authority);
            users.setAuthorities(list);
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        save(users);
    }


    @Transactional
    public void update(Users users) {
        if(userRepository.exists(users.getUserId())){
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            userRepository.save(users);
        }
    }

    public void remove(String id){
        userRepository.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (loginAttemptService.isLocked(username)) {
            throw new RuntimeException("用户" + username + "登录错误次数超过"  +MAX_ATTEMPT + "次，该用户已被锁定！" + AUTO_UNLOCK + "分钟后自动解锁！");
        }
        return userRepository.findByUsername(username);
    }

    public Users getByUsername (String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void changePassword(String userId, String oldPassword, String newPassword) {
        if (!userRepository.exists(userId)) {
            throw new RuntimeException("用户不存在");
        }
        if (!passwordEncoder.matches(oldPassword, userRepository.getPassword(userId))) {
            throw new RuntimeException("输入的旧密码不正确");
        }
        String regex = "[^\\u4e00-\\u9fa5&&[\\S]]{6,20}";
        if (!newPassword.matches(regex)) {
            throw new RuntimeException("新密码必须由非空和非汉字的6到20位字符组合");
        }
        userRepository.updatePassword(userId, passwordEncoder.encode(newPassword));
    }
}
