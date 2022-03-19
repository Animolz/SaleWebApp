package com.lhn.repository;

import com.lhn.pojo.Product;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public interface ProductRepository {
    List<Product> getProducts(Map<String, String> params, int page);
}
