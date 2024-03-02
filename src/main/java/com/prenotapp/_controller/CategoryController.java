package com.prenotapp._controller;

import com.prenotapp._dto.CategoryDTO;
import com.prenotapp._model.Category;
import com.prenotapp._service.ICategoryService;
import com.prenotapp.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      .sorted(Comparator.comparing(CategoryDTO::getId))
      .collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id)
    throws Exception {
    Category category = categoryService.findById(id);
    if (category == null) {
      throw new ModelNotFoundException("Category not found with ID: " + id);
    }
    return ResponseEntity.ok(mapper.map(category, CategoryDTO.class));
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(
    @Valid @RequestBody CategoryDTO categoryDTO
  ) throws Exception {
    Category category = categoryService.register(
      mapper.map(categoryDTO, Category.class)
    );
    URI location = new URI("/categories/" + category.getId());
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> update(
    @PathVariable("id") Integer id,
    @RequestBody CategoryDTO categoryDTO
  ) throws Exception {
    Category category = mapper.map(categoryDTO, Category.class);
    category.setId(id);
    category = categoryService.update(category);
    return ResponseEntity.ok(mapper.map(category, CategoryDTO.class));
  }
}
