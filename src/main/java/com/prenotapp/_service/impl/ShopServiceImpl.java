package com.prenotapp._service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._model.Shop;
import com.prenotapp._repo.IGenericRepo;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ShopServiceImpl
  extends CRUDImpl<Shop, Integer>
  implements IShopService {

  @Autowired
  private IShopRepo shopRepo;

  @Override
  protected IGenericRepo<Shop, Integer> getRepo() {
    return shopRepo;
  }

  @PersistenceContext
  private EntityManager entityManager;
}

