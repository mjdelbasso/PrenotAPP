package com.prenotapp._service;

import com.prenotapp._dto.ShopCategoryDTO;
import java.util.List;

public interface IShopCategoryService {
  public ShopCategoryDTO register(ShopCategoryDTO shopCategory)
    throws Exception;

  public ShopCategoryDTO update(ShopCategoryDTO shopCategory) throws Exception;

  public List<ShopCategoryDTO> list() throws Exception;

  public ShopCategoryDTO findById(Long id) throws Exception;

  public void delete(Long id) throws Exception;
}
