package com.prenotapp._service.impl;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Category;
import com.prenotapp._model.Shop;
import com.prenotapp._model.ShopCategory;
import com.prenotapp._repo.ICategoryRepo;
import com.prenotapp._repo.IShopCategoryRepo;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;
import com.prenotapp.exception.ModelNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  @Transactional(readOnly = true)
  public List<ShopDTO> list() throws Exception {
    return shopRepo
      .findAll()
      .stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(ShopDTO::getId))
      .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public ShopDTO findById(@NonNull Long id) {
    return shopRepo
      .findById(id)
      .map(this::toDTO)
      .orElseThrow(() ->
        new ModelNotFoundException("Shop not found with ID: " + id)
      );
  }

  @Override
  @Transactional
  public ShopDTO register(@NonNull ShopDTO shopDTO) throws Exception {
    return toDTO(shopRepo.save(toEntity(shopDTO)));
  }

  @Override
  @Transactional
  public ShopDTO update(@NonNull ShopDTO shopDTO) throws Exception {
    return toDTO(shopRepo.save(toEntity(shopDTO)));
  }

  @Override
  @Transactional
  public void delete(@NonNull Long id) throws Exception {
    shopRepo.deleteById(id);
  }

  @Override
  @Transactional
  public ShopDTO addCategoryToShop(
    @NonNull Long idShop,
    @NonNull Long idCategory
  ) throws Exception {
    Shop shop = shopRepo
      .findById(idShop)
      .orElseThrow(() ->
        new ModelNotFoundException("Shop not found with ID: " + idShop)
      );
    Category category = categoryRepo
      .findById(idCategory)
      .orElseThrow(() ->
        new ModelNotFoundException("Category not found with ID: " + idCategory)
      );
    shop.getCategories().add(category);
    Shop savedShop = shopRepo.save(shop);
    return toDTO(savedShop);
  }

  @Override
  @Transactional
  public void removeCategoryFromShop(
    @NonNull Long idShop,
    @NonNull Long idCategory
  ) throws Exception {
    Shop shop = shopRepo
      .findById(idShop)
      .orElseThrow(() ->
        new IllegalArgumentException("Shop not found with ID: " + idShop)
      );
    Category category = categoryRepo
      .findById(idCategory)
      .orElseThrow(() ->
        new IllegalArgumentException(
          "Category not found with ID: " + idCategory
        )
      );

    if (shop.getCategories().remove(category)) {
      shopRepo.save(shop);

      ShopCategory shopCategory = shopCategoryRepo.findByShopAndCategory(
        shop,
        category
      );
      if (shopCategory != null) {
        shopCategoryRepo.delete(shopCategory);
      }
    }
  }

  private ShopDTO toDTO(Shop shop) {
    return mapper.map(shop, ShopDTO.class);
  }

  private Shop toEntity(ShopDTO shopDTO) {
    return mapper.map(shopDTO, Shop.class);
  }
}
