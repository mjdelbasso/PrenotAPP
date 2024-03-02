package com.prenotapp._service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._model.Category;
import com.prenotapp._model.Shop;
import com.prenotapp._model.ShopCategory;
import com.prenotapp._repo.IShopCategoriesRepo;
import com.prenotapp._service.IShopCategoryService;

@Service
public class ShopCategoryServiceImpl
  extends CRUDImpl<ShopCategory, Integer>
  implements IShopCategoryService {

  @Autowired
  private IShopCategoriesRepo repo;

  @Override
  protected IShopCategoriesRepo getRepo() {
    return repo;
  }

  @Override
  public void addCategorytoShop(Integer shopId, Integer categoryId)
    throws Exception {
    ShopCategory shopCategory = new ShopCategory();
    Shop shop = new Shop();
    shop.setId(shopId);
    Category category = new Category();
    category.setId(categoryId);
    shopCategory.setShop(shop);
    shopCategory.setCategory(category);
    repo.save(shopCategory);
  }
}
