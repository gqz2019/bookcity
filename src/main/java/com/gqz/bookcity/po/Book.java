package com.gqz.bookcity.po;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_book")
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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return sales
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * @param sales
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * @return stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return img_path
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}