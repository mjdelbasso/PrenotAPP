package com.prenotapp._service.impl;

import com.prenotapp._model.ShopCategory;
import com.prenotapp._repo.IShopCategoryRepo;
import com.prenotapp._service.IShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCategoryServiceImpl
  extends CRUDImpl<ShopCategory, Integer>
  implements IShopCategoryService {

  @Autowired
  private IShopCategoryRepo repo;

  @Override
  protected IShopCategoryRepo getRepo() {
    return repo;
  }
}
