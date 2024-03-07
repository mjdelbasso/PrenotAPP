package com.prenotapp._service;

import com.prenotapp._dto.CategoryDTO;
import java.util.List;

public interface ICategoryService {
  public CategoryDTO register(CategoryDTO category) throws Exception;

  public CategoryDTO update(CategoryDTO category) throws Exception;

  public void delete(Long id) throws Exception;

  public CategoryDTO findById(Long id) throws Exception;

  public List<CategoryDTO> list() throws Exception;
}
