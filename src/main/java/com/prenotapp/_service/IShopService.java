package com.prenotapp._service;

import com.prenotapp._dto.ShopDTO;

public interface IShopService extends ICRUD<ShopDTO, Long> {
  ShopDTO addCategoryToShop(Long idShop, Long idCategory) throws Exception;

  void removeCategoryFromShop(Long idShop, Long idCategory) throws Exception;
}
