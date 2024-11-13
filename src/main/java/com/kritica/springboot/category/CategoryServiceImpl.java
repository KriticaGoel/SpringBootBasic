package com.kritica.springboot.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements  CategoryService {

    public List<Category> categories = new ArrayList<Category>();
    private int nextId=1;
    public List<Category> getCategories(){
        return categories;
    }

    public String createCategory(Category category){
        category.setId(nextId++);
        categories.add(category);
        return "Category created successfully";
    }

    public String deleteCategory(int id){
//        Category category = categories.stream().filter(c->c.getId()==id).findFirst().orElse(null);
//        if(category==null){
//            return "Category not found";
//        }else {
//            categories.remove(category);
//            return "Category deleted successfully";
//        }

        Category category = categories.stream().filter(c->c.getId()==id).findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
            categories.remove(category);
            return "Category deleted successfully";
        }

}
