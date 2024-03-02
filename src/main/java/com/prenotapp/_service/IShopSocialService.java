package com.prenotapp._service;

import com.prenotapp._model.ShopSocial;

public interface IShopSocialService extends ICRUD<ShopSocial, Integer> {
  void addSocialtoShop(Integer shopId, Integer socialId) throws Exception;

  void removeSocialFromShop(Integer shopId, Integer socialId) throws Exception;
}
