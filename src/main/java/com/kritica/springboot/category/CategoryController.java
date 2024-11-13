package com.kritica.springboot.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/api/admin")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    //Create Category
   @PostMapping("/createCategory")
   public ResponseEntity<String> createCategory(@RequestBody Category category) {
       return new ResponseEntity<String>(categoryService.createCategory(category),HttpStatus.CREATED);
   }

    //Delete Category
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
       try {
           return new ResponseEntity<String>(categoryService.deleteCategory(id), HttpStatus.ACCEPTED);
       }catch (ResponseStatusException e) {
           return new ResponseEntity<String>(e.getMessage(), e.getStatusCode());
       }
   }
    //Update Category
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category category) {
      return new ResponseEntity<String>(categoryService.updateCategory(id,category),HttpStatus.OK);
    };

    //Get All category
    @GetMapping("/category")
    //@RequestMapping(value="/api/admin/category",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> createCategory() {
        return new ResponseEntity<>(categoryService.getCategories(),HttpStatus.OK);

    }
}
