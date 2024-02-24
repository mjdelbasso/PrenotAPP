package com.prenotapp._repo;

import com.prenotapp._model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopRepo extends IGenericRepo<Shop, Integer> {
}
