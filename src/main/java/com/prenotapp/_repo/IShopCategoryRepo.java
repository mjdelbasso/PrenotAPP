package com.prenotapp._repo;

import com.prenotapp._model.Category;
import com.prenotapp._model.Shop;
import com.prenotapp._model.ShopCategory;

public interface IShopCategoryRepo
  extends IGenericRepo<ShopCategory, Integer> {

    ShopCategory findByShopAndCategory(Shop shop, Category category);
  }
