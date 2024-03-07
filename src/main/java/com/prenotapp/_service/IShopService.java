package com.prenotapp._service;

import java.util.List;

import com.prenotapp._dto.ShopDTO;

public interface IShopService {
  public List<ShopDTO> list() throws Exception;

  public ShopDTO register(ShopDTO shop) throws Exception;

  public ShopDTO update(ShopDTO shop) throws Exception;

  public ShopDTO findById(Long id) throws Exception;

  public void delete(Long id) throws Exception;

  public ShopDTO addCategoryToShop(Long idShop, Long idCategory)
    throws Exception;

  public void removeCategoryFromShop(Long idShop, Long idCategory) throws Exception;
}
