package com.prenotapp._service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._model.Category;
import com.prenotapp._repo.ICategoryRepo;
import com.prenotapp._service.ICategoryService;

@Service
public class CategoryServiceImpl
  extends CRUDImpl<Category, Long>
  implements ICategoryService {

  @Autowired
  private ICategoryRepo repo;

  @Override
  protected ICategoryRepo getRepo() {
    return repo;
  }
}
