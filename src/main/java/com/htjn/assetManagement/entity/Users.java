package com.htjn.assetManagement.entity;

import com.htjn.assetManagement.validate.Create;
import com.htjn.assetManagement.validate.Update;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by caojy on 2017/12/7.
 */
@Entity
@Table(name = "users")
public class Users implements UserDetails, Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id", length = 36)
    private String userId;
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class})
    @Pattern(regexp = "[\\u4E00-\\u9FA5A-Za-z0-9_-]{1,40}", message = "用户名只允许输入1到40位中文、大小写字母、数字、-_",
            groups = {Create.class, Update.class})
    @Column(name = "username", nullable = false, length = 40, unique = true)
    private String username;
    @Pattern(regexp = "[^\\u4e00-\\u9fa5&&[\\S]]{6,20}", message ="用户密码必须为非空和非汉字的6-20位字符组成", groups = {Create.class, Update.class})
    @NotBlank(message = "密码不能为空")
    @Column(name = "password", nullable = false)
    private String password;
    @Email(regexp = "^[a-zA-Z0-9_-]+[.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "请输入正确的邮箱地址", groups = {Create.class, Update.class})
    @Size(max = 32, message = "邮箱长度应小于32位")
    @Column(name = "email", length = 32, nullable = false)
    private String email;
    @Size(max = 20, message = "联系方式长度应小于20")
    @Column(name = "phone", length = 20)
    private String phone;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
    }

/*    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }*/

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Users) {
            return username.equals(((Users) o).username);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
