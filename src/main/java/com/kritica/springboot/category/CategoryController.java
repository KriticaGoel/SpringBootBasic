package com.kritica.springboot.category;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/admin")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    //Create Category
   @PostMapping("/createCategory")
   public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
       return new ResponseEntity<String>(categoryService.createCategory(category),HttpStatus.CREATED);
   }

    //Delete Category
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
           return new ResponseEntity<String>(categoryService.deleteCategory(id), HttpStatus.ACCEPTED);
   }
    //Update Category
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @Valid @RequestBody Category category) {
      return new ResponseEntity<String>(categoryService.updateCategory(id,category),HttpStatus.OK);
    };

    //Get All category
    @GetMapping("/category")
    //@RequestMapping(value="/api/admin/category",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategory() {
        return new ResponseEntity<>(categoryService.getCategories(),HttpStatus.OK);

    }
}
