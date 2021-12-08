package com.gqz.bookcity.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_role")
@Data
public class Role implements Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "keyword", length = 64)
    private String keyword;

    @Column(name = "description", length = 128)
    private String description;

    @Override
    public String getAuthority() {
        return this.keyword;
    }
}