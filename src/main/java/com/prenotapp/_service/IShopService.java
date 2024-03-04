package com.prenotapp._service;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import java.util.List;

public interface IShopService extends ICRUD<Shop, Integer> {
  List<ShopDTO> listAllShops() throws Exception;

  ShopDTO getShopById(Integer id) throws Exception;

  ShopDTO addCategoryToShop(Integer idShop, Integer idCategory)
    throws Exception;

  void removeCategoryFromShop(Integer idShop, Integer idCategory)
    throws Exception;
}
