package com.kritica.springboot.category;

import com.kritica.springboot.category.payload.CategoryDTO;
import com.kritica.springboot.category.payload.CategoryResponse;
import com.kritica.springboot.config.AppConstants;
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
   public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO category) {
       return new ResponseEntity<String>(categoryService.createCategory(category),HttpStatus.CREATED);
   }

    //Delete Category
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
           return new ResponseEntity<String>(categoryService.deleteCategory(id), HttpStatus.ACCEPTED);
   }
    //Update Category
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO category) {
      return new ResponseEntity<String>(categoryService.updateCategory(id,category),HttpStatus.OK);
    };

    //Get All category
    //@RequestMapping(value="/api/admin/category",method = RequestMethod.GET)
    @GetMapping("/category")
    public ResponseEntity<CategoryResponse> getCategory(@RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                        @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize) {
        return new ResponseEntity<CategoryResponse>(categoryService.getCategories(pageNumber,pageSize),HttpStatus.OK);

    }

    @GetMapping("/echo")
    //public ResponseEntity<String> echo(@RequestParam(name="message",defaultValue = "Hello world") String name) {
    public ResponseEntity<String> echo(@RequestParam(name="message",required = false) String name) {
        return new ResponseEntity<String>("Hi "+name,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> blankPage() {
        return new ResponseEntity<String>("Up and Running",HttpStatus.OK);
    }

}
