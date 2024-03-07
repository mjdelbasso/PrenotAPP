package com.prenotapp._service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prenotapp._dto.CategoryDTO;
import com.prenotapp._model.Category;
import com.prenotapp._repo.ICategoryRepo;
import com.prenotapp._service.ICategoryService;

@Service
@SuppressWarnings("null")
public class CategoryServiceImpl implements ICategoryService {

  @Autowired
  private ICategoryRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Override
  @Transactional
  public CategoryDTO register(CategoryDTO category) throws Exception {
    return toDTO(repo.save(toEntity(category)));
  }

  @Override
  @Transactional
  public CategoryDTO update(CategoryDTO category) throws Exception {
    return toDTO(repo.save(toEntity(category)));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CategoryDTO> list() throws Exception {
    return repo
      .findAll()
      .stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(CategoryDTO::getId))
      .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryDTO findById(Long id) throws Exception {
    return toDTO(repo.findById(id).orElse(null));
  }

  @Override
  @Transactional
  public void delete(Long id) throws Exception {
    repo.deleteById(id);
  }

  private Category toEntity(CategoryDTO categoryDTO) {
    return mapper.map(categoryDTO, Category.class);
  }

  private CategoryDTO toDTO(Category category) {
    return mapper.map(category, CategoryDTO.class);
  }
}
