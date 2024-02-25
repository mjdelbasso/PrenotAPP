package com.prenotapp._service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._model.Category;
import com.prenotapp._model.Shop;
import com.prenotapp._repo.ICategoryRepo;
import com.prenotapp._repo.IGenericRepo;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;

@Service
public class ShopServiceImpl
  extends CRUDImpl<Shop, Integer>
  implements IShopService {

  @Autowired
  private IShopRepo shopRepo;

  @Autowired
  private ICategoryRepo categoryRepo;

  @Override
  protected IGenericRepo<Shop, Integer> getRepo() {
    return shopRepo;
  }

  @SuppressWarnings("null")
  @Override
  public void addCategorytoShop(Integer shopId, Integer categoryId)
    throws Exception {
    Shop shop = shopRepo.findById(shopId).orElse(null);
    if (shop == null) {
      throw new Exception("Shop not found");
    }
    Category category = categoryRepo.findById(categoryId).orElse(null);
    if (category == null) {
      throw new Exception("Category not found");
    }
    category.setShop(shop);
    shop.getCategories().add(category);
    shopRepo.save(shop);
  }
}
