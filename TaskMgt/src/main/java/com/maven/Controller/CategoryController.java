package com.maven.Controller;

import com.maven.Model.Category;
import com.maven.Model.dtos.DtoCategory;
import com.maven.Services.ICategoryService;
import com.maven.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody DtoCategory category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Long categoryId){

         System.out.println(categoryId);
         categoryService.deleteCategory(categoryId);
         return ResponseEntity.ok("category deleted successfully...!");
    }

    @PatchMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestBody DtoCategory category) throws ResourceNotFoundException {

        return ResponseEntity.ok(categoryService.updateCategory(category));
    }
}
