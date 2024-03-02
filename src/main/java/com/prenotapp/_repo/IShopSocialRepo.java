package com.prenotapp._repo;

import org.springframework.data.jpa.repository.Query;

import com.prenotapp._model.ShopSocial;

public interface IShopSocialRepo extends IGenericRepo<ShopSocial, Integer> {
  @Query(
    value = "DELETE FROM shop_socials WHERE shop_id = ?1 AND social_id = ?2",
    nativeQuery = true
  )
  void deleteSocialFromShop(Integer shopId, Integer socialId) throws Exception;
}
