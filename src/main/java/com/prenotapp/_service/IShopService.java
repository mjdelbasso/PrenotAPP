package com.prenotapp._service;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import java.util.List;

public interface IShopService extends ICRUD<Shop, Long> {
  List<ShopDTO> listAllShops() throws Exception;

  ShopDTO getShopById(Long id) throws Exception;

  ShopDTO addCategoryToShop(Long idShop, Long idCategory) throws Exception;

  void removeCategoryFromShop(Long idShop, Long idCategory) throws Exception;
}
