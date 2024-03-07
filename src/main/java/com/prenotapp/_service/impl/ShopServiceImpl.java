package com.prenotapp._service.impl;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Category;
import com.prenotapp._model.Shop;
import com.prenotapp._model.ShopCategory;
import com.prenotapp._repo.ICategoryRepo;
import com.prenotapp._repo.IShopCategoryRepo;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;

import lombok.NonNull;

@Service
@SuppressWarnings("null")
public class ShopServiceImpl implements IShopService {

  @Autowired
  private IShopRepo shopRepo;

  @Autowired
  private ICategoryRepo categoryRepo;

  @Autowired
  private IShopCategoryRepo shopCategoryRepo;

  @Autowired
  private ModelMapper mapper;

  protected IShopRepo getRepo() {
    return shopRepo;
  }

  @Override
  public List<ShopDTO> list() {
    return shopRepo
      .findAll()
      .stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(ShopDTO::getId))
      .toList();
  }

  @Override
  public ShopDTO findById(@NonNull Long id) {
    return toDTO(shopRepo.findById(id).get());
  }

  @Override
  public ShopDTO addCategoryToShop(
    @NonNull Long idShop,
    @NonNull Long idCategory
  ) {
    Shop shop = shopRepo.findById(idShop).get();
    Category category = categoryRepo.findById(idCategory).get();
    shop.getCategories().add(category);
    return toDTO(shopRepo.save(shop));
  }

  @Override
  public void removeCategoryFromShop(
    @NonNull Long idShop,
    @NonNull Long idCategory
  ) {
    Shop shop = shopRepo.findById(idShop).get();
    Category category = categoryRepo.findById(idCategory).get();
    shop.getCategories().remove(category);
    ShopCategory shopCategory = shopCategoryRepo.findByShopAndCategory(
      shop,
      category
    );
    if (shopCategory != null) shopCategoryRepo.delete(shopCategory);
    shopRepo.save(shop);
  }

  
  @Override
  public ShopDTO register(@NonNull ShopDTO shopDTO) throws Exception {
    return toDTO(shopRepo.save(toEntity(shopDTO)));
  }

  @Override
  public ShopDTO update(@NonNull ShopDTO shopDTO) throws Exception {
    return toDTO(shopRepo.save(toEntity(shopDTO)));
  }

  @Override
  public void delete(@NonNull Long id) throws Exception {
    shopRepo.deleteById(id);
  }

  public ShopDTO toDTO(Shop shop) {
    return mapper.map(shop, ShopDTO.class);
  }

  public Shop toEntity(ShopDTO shopDTO) {
    return mapper.map(shopDTO, Shop.class);
  }
}
