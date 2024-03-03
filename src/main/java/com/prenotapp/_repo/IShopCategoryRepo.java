package com.prenotapp._repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.prenotapp._dto.ShopCategoryDTO;
import com.prenotapp._model.ShopCategory;

public interface IShopCategoryRepo extends IGenericRepo<ShopCategory, Integer> {
  @Query(
    value = "DELETE FROM shop_category WHERE shop_id = ?1 AND category_id = ?2",
    nativeQuery = true
  )
  void deleteCategoryFromShop(Integer shopId, Integer categoryId)
    throws Exception;

  @Query(
    value = "SELECT * FROM shop_category WHERE shop_id = ?1 AND category_id = ?2",
    nativeQuery = true
  )
  List<ShopCategoryDTO> listCategoriesByShop(
    Integer shopId,
    Integer categoryId
  ) throws Exception;

  @Query(
    value = "SELECT * FROM shop_category WHERE shop_id = ?1 AND category_id = ?2",
    nativeQuery = true
  )
  ShopCategory findByShopIdAndCategoryId(Integer shopId, Integer categoryId);

  
  @Query(
    value = "SELECT * FROM shop_category WHERE shop_id = ?1",
    nativeQuery = true
  )
  List<ShopCategory> findByShop(Integer shopid) throws Exception;
}
