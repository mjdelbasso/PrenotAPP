package com.prenotapp._repo;

import org.springframework.data.jpa.repository.Query;

import com.prenotapp._model.ShopCategory;

public interface IShopCategoryRepo extends IGenericRepo<ShopCategory, Integer> {

    @Query(
        value = "DELETE FROM shop_categories WHERE shop_id = ?1 AND category_id = ?2",
        nativeQuery = true
    )
    void deleteCategoryFromShop(Integer shopId, Integer categoryId) throws Exception;
}
