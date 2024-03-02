package com.prenotapp._service;

import com.prenotapp._model.ShopCategory;

public interface IShopCategoryService extends ICRUD<ShopCategory, Integer> {
  void addCategorytoShop(Integer shopId, Integer categoryId) throws Exception;

  void removeCategoryFromShop(Integer shopId, Integer categoryId) throws Exception;
}
