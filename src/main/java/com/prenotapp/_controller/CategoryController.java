package com.prenotapp._controller;

import com.prenotapp._dto.CategoryDTO;
import com.prenotapp._model.Category;
import com.prenotapp._service.ICategoryService;
import com.prenotapp.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
      .map(category -> toDTO(category))
      .sorted(Comparator.comparing(CategoryDTO::getId))
      .collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Long id)
    throws Exception {
    Category category = categoryService.findById(id);
    if (category == null) {
      throw new ModelNotFoundException("Category not found with ID: " + id);
    }
    return new ResponseEntity<>(toDTO(category), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<CategoryDTO> register(
    @Valid @RequestBody CategoryDTO categoryDTO
  ) throws Exception {
    Category category = categoryService.register(toEntity(categoryDTO));
    return new ResponseEntity<>(toDTO(category), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> update(
    @PathVariable("id") Long id,
    @RequestBody CategoryDTO categoryDTO
  ) throws Exception {
    Category category = toEntity(categoryDTO);
    category.setId(id);
    category = categoryService.update(category);
    return new ResponseEntity<>(toDTO(category), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    throws Exception {
    categoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public CategoryDTO toDTO(Category category) {
    return mapper.map(category, CategoryDTO.class);
  }

  public Category toEntity(CategoryDTO categoryDTO) {
    return mapper.map(categoryDTO, Category.class);
  }
}
