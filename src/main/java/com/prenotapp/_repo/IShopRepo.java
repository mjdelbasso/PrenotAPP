package com.prenotapp._repo;

import org.springframework.stereotype.Repository;

import com.prenotapp._model.Shop;

@Repository
public interface IShopRepo extends IGenericRepo<Shop, Long> {}
