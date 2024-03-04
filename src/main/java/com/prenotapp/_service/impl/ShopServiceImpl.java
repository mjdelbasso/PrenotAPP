package com.prenotapp._service.impl;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import com.prenotapp._model.ShopCategory;
import com.prenotapp._repo.ICategoryRepo;
import com.prenotapp._repo.IGenericRepo;
import com.prenotapp._repo.IShopCategoryRepo;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl
  extends CRUDImpl<Shop, Integer>
  implements IShopService {

  @Autowired
  private IShopRepo shopRepo;

  @Autowired
  private ICategoryRepo categoryRepo;

  @Autowired
  private IShopCategoryRepo shopCategoryRepo;

  @Autowired
  private ModelMapper mapper;

  @Override
  protected IGenericRepo<Shop, Integer> getRepo() {
    return shopRepo;
  }

  @Override
  public List<ShopDTO> listAllShops() {
    List<ShopDTO> lstShopDTO = new ArrayList<>();
    for (Shop shop : shopRepo.findAll()) {
      lstShopDTO.add(mapper.map(shop, ShopDTO.class));
    }
    return lstShopDTO;
  }

  @Override
  public ShopDTO getShopById(@NonNull Integer id) {
    Shop shop = shopRepo.findById(id).get();
    return mapper.map(shop, ShopDTO.class);
  }

  @Override
  public ShopDTO addCategoryToShop(
    @NonNull Integer idShop,
    @NonNull Integer idCategory
  ) {
    Shop shop = shopRepo.findById(idShop).get();
    shop.getCategories().add(categoryRepo.findById(idCategory).get());
    return mapper.map(shopRepo.save(shop), ShopDTO.class);
  }

  @Override
  public void removeCategoryFromShop(
    @NonNull Integer idShop,
    @NonNull Integer idCategory
  ) {
    Shop shop = shopRepo.findById(idShop).get();
    shop.getCategories().remove(categoryRepo.findById(idCategory).get());
    ShopCategory shopCategory = shopCategoryRepo.findByShopAndCategory(
      shop,
      categoryRepo.findById(idCategory).get()
    );
    if (shopCategory != null) shopCategoryRepo.delete(shopCategory);
    shopRepo.save(shop);
  }
}
