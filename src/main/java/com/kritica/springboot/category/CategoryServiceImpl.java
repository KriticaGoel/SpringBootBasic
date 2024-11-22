package com.kritica.springboot.category;

import com.kritica.springboot.category.payload.CategoryDTO;
import com.kritica.springboot.category.payload.CategoryResponse;
import com.kritica.springboot.exception.APIException;
import com.kritica.springboot.exception.ResourcesNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements  CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryResponse getCategoriesManual(){
        long counting = categoryRepository.count();
        if(counting==0)
            throw new APIException("No categories found");
        // Database - Entity
        List<Category> categories  = categoryRepository.findAll();
        //Response Model
        CategoryResponse categoryResponse = new CategoryResponse();
        //Request Model
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        //convert entity to response model
        categories.stream().forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(category.getCategoryName());

            categoryDTOList.add(categoryDTO);
        });
        //Setting response model
        categoryResponse.setContent(categoryDTOList);
        return categoryResponse;
    }

    public CategoryResponse getCategories(Integer pageNumber,Integer pageSize){
        long count = categoryRepository.count();
        if(count==0)
            throw new APIException("No categories found");
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        // Database - Entity
        //List<Category> categories  = categoryRepository.findAll();
        List<Category> categories  = categoryPage.getContent();
        //Response Model
        CategoryResponse categoryResponse = new CategoryResponse();

        //convert entity to response model
        List<CategoryDTO> categoryDTOList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        //Setting response model
        categoryResponse.setContent(categoryDTOList);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());
        categoryResponse.setHasNextPage((categoryPage.hasNext()));
        categoryResponse.setHasPreviousPage((categoryPage.hasPrevious()));

        return categoryResponse;
    }

    public String createCategory(CategoryDTO category){

        Category categoryFound = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFound != null){
            throw new APIException("Category already exists");
        }
        Category modelCategory =modelMapper.map(category, Category.class);
        categoryRepository.save(modelCategory);
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
    public String updateCategory(int id, CategoryDTO updatedCategory) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream().filter(c->c.getId()==id).findFirst().orElse(null);
        if(category==null){
            createCategory(updatedCategory);
            return "Category created successfully";
        }
        else{
            Category modelCategory =modelMapper.map(updatedCategory, Category.class);
            category.setCategoryName(modelCategory.getCategoryName());
            categoryRepository.save(category);
            return "Category updated successfully";
        }
    }

}
