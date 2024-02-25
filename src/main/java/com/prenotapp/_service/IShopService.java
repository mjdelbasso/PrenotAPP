package com.prenotapp._service;

import com.prenotapp._model.Shop;

public interface IShopService extends ICRUD<Shop, Integer> {
  void addCategorytoShop(Integer shopId, Integer categoryId) throws Exception;
}
