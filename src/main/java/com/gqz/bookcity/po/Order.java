package com.gqz.bookcity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "oid")
    private String oid;

    @Column(name = "bid")
    private Integer bid;

    @Column(name = "count")
    private Integer count;

    @Column(name = "totalprice", precision = 10, scale = 2)
    private BigDecimal totalprice;

    @Column(name = "createdate")
    private LocalDate createdate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "uid")
    private Integer uid;
}