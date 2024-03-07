package com.prenotapp._controller;

import java.util.List;

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

import com.prenotapp._dto.CategoryDTO;
import com.prenotapp._service.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private ICategoryService service;

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> list() throws Exception {
    return new ResponseEntity<>(service.list(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Long id)
    throws Exception {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<CategoryDTO> register(
    @Valid @RequestBody CategoryDTO categoryDTO
  ) throws Exception {
    return new ResponseEntity<>(
      service.register(categoryDTO),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<CategoryDTO> update(
    @PathVariable("id") Long id,
    @RequestBody CategoryDTO categoryDTO
  ) throws Exception {
    categoryDTO.setId(id);
    return new ResponseEntity<>(service.update(categoryDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
