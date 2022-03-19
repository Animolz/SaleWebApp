/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.repository.impl;

import com.lhn.pojo.Product;
import com.lhn.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Product> getProducts(Map<String, String> params, int page) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = builder.createQuery(Product.class);
        Root root = q.from(Product.class);
        q.select(root);
        
        if(params != null){
            List<Predicate> predicates = new ArrayList<>();
            if(params.containsKey("kw")){
                predicates.add(builder.like(root.get("name").as(String.class), String.format("%%%s%%", params.get("kw"))));
            }
            
            if(params.containsKey("fromPrice")){
                predicates.add(builder.greaterThanOrEqualTo(root.get("price").as(Long.class), Long.parseLong(params.get("fromPrice"))));
            }
            
            if(params.containsKey("toPrice")){
                predicates.add(builder.lessThanOrEqualTo(root.get("price").as(Long.class), Long.parseLong(params.get("fromPrice"))));
            }
            
            if(params.containsKey("cateId")){
                predicates.add(builder.equal(root.get("categoryId").as(Integer.class), Integer.parseInt(params.get("cateId"))));
            }
            
            q = q.where(predicates.toArray(new Predicate[]{}));
        }
        
        q = q.orderBy(builder.desc(root.get("id")));
        
        Query query = s.createQuery(q);
        
        //Phân trang
        int max = 9;
        int index = (page - 1) * max;
        query.setFirstResult(index);
        query.setMaxResults(max);
        
        return query.getResultList();
    }
    
}
