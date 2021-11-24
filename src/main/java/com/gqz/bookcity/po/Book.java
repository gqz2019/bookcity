package com.gqz.bookcity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author gqz20
 */
@Table(name = "t_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String name;

    private String author;

    private BigDecimal price;

    private Integer sales;

    private Integer stock;

    @Column(name = "img_path")
    private String imgPath;
}