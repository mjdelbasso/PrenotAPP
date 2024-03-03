package com.prenotapp._service;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import java.util.List;

public interface IShopService extends ICRUD<Shop, Integer> {

  List<ShopDTO> listAllShops() throws Exception;

  ShopDTO listShopById(Integer id) throws Exception;
}
