package com.prenotapp._service.impl;

import com.prenotapp._model.Shop;
import com.prenotapp._model.ShopSocial;
import com.prenotapp._model.Social;
import com.prenotapp._repo.IShopSocialRepo;
import com.prenotapp._service.IShopSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopSocialServiceImpl
  extends CRUDImpl<ShopSocial, Integer>
  implements IShopSocialService {

  @Autowired
  private IShopSocialRepo repo;

  @Override
  protected IShopSocialRepo getRepo() {
    return repo;
  }

  @Override
  public void addSocialtoShop(Integer shopId, Integer socialId)
    throws Exception {
    ShopSocial shopSocial = new ShopSocial();
    Shop shop = new Shop();
    Social social = new Social();
    shop.setId(shopId);
    social.setId(socialId);
    shopSocial.setShop(shop);
    shopSocial.setSocial(social);
    repo.save(shopSocial);
  }

  @Override
  public void removeSocialFromShop(Integer shopId, Integer socialId)
    throws Exception {
    repo.deleteSocialFromShop(shopId, socialId);
  }
}
