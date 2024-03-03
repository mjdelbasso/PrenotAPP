package com.prenotapp._repo;

import com.prenotapp._model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {
  @Query(
    value = "SELECT c.* FROM shop_category c INNER JOIN category sc ON c.id = sc.id_category WHERE sc.id_shop = ?1",
    nativeQuery = true
  )
  List<Category> listCategoriesByShop(Integer shopId) throws Exception;
}
