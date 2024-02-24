package com.prenotapp._service_impl;

import com.prenotapp._model.Shop;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl
  extends CRUDImpl<Shop, Integer>
  implements IShopService {

  @Autowired
  private IShopRepo repo;

  @Override
  protected IShopRepo getRepo() {
    return repo;
  }
}
