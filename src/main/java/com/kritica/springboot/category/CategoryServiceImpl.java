package com.kritica.springboot.category;

import com.kritica.springboot.exception.APIException;
import com.kritica.springboot.exception.ResourcesNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements  CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        long count = categoryRepository.count();
        if(count==0)
            throw new APIException("No categories found");
        return categoryRepository.findAll();
    }

    public String createCategory(Category category){

        Category categoryFound = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFound != null){
            throw new APIException("Category already exists");
        }
        categoryRepository.save(category);
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
List<Category> categories = categoryRepository.findAll();
//        Category category = categories.stream().filter(c->c.getId()==id).findFirst()
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        Category category = categories.stream().filter(c->c.getId()==id).findFirst()
                .orElseThrow(()->new ResourcesNotFound("Category",Long.valueOf(id),"Category ID"));
        categoryRepository.deleteById(category.getId());
            return "Category deleted successfully";




    }

    @Override
    public String updateCategory(int id, Category updatedCategory) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream().filter(c->c.getId()==id).findFirst().orElse(null);
        if(category==null){
            createCategory(updatedCategory);
            return "Category created successfully";
        }
        else{
            category.setCategoryName(updatedCategory.getCategoryName());
            categoryRepository.save(category);
            return "Category updated successfully";
        }
    }

}
