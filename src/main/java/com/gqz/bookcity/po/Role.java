package com.gqz.bookcity.po;

import lombok.Data;

import javax.persistence.*;

@Table(name = "t_role")
@Data
public class Role {
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

}