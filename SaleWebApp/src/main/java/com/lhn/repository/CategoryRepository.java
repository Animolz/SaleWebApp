/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository;

import com.lhn.pojo.Category;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategoryRepository {
    List<Category> getCategories();
}
