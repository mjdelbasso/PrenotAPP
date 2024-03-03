package com.prenotapp._repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prenotapp._dto.CategoryDTO;
import com.prenotapp._model.Shop;

@Repository
public interface IShopRepo extends IGenericRepo<Shop, Integer> {
  @Query(
    value = "INSERT INTO shop_category (id_shop, id_category) VALUES (?1, ?2)",
    nativeQuery = true
  )
  void addCategorytoShop(Integer idShop, Integer idCategory);

  @Query(
    value = "DELETE FROM shop_category WHERE id_shop = ?1 AND id_category = ?2",
    nativeQuery = true
  )
  void removeCategoryfromShop(Integer idShop, Integer idCategory);


  
  @Query(
    value = "SELECT * FROM shop_category WHERE shop_id= ?1 AND category_id = ?2",
    nativeQuery = true
  )
  List<CategoryDTO> listCategoriesByShopId(Integer shopId, Integer categoryId) throws Exception;
}
