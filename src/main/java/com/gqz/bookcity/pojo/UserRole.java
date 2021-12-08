package com.gqz.bookcity.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "t_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -8648596386856449613L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole entity = (UserRole) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.userId, entity.userId);
    }
}