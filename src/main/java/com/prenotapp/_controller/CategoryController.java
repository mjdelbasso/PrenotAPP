package com.prenotapp._controller;

import com.prenotapp._dto.CategoryDTO;
import com.prenotapp._model.Category;
import com.prenotapp._service.ICategoryService;
import com.prenotapp.exception.ModelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> list() throws Exception {
        List<CategoryDTO> list = categoryService
                .list()
                .stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> listById(@PathVariable("id") Integer id)
            throws Exception {
        CategoryDTO categoryDTO;
        Category category = categoryService.listById(id);
        if (category == null) {
            throw new ModelNotFoundException("Category not found with ID: " + id);
        } else {
            categoryDTO = mapper.map(category, CategoryDTO.class);
        }
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody CategoryDTO categoryDTO)
            throws Exception {
        Category category = mapper.map(categoryDTO, Category.class);
        Category registeredCategory = categoryService.register(category);
        CategoryDTO registeredDTO = mapper.map(registeredCategory, CategoryDTO.class);

        URI location = new URI("/categories/" + registeredDTO.getId());
        return ResponseEntity.created(location).build();
    }

    // Otros métodos como actualizar, eliminar, etc. pueden ser implementados de manera similar
}
