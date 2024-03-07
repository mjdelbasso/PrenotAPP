package com.prenotapp._service.impl;

import com.prenotapp._dto.ShopCategoryDTO;
import com.prenotapp._model.ShopCategory;
import com.prenotapp._repo.IShopCategoryRepo;
import com.prenotapp._service.IShopCategoryService;
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
public class ShopCategoryServiceImpl implements IShopCategoryService {

  @Autowired
  private IShopCategoryRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Override
  @Transactional
  public ShopCategoryDTO register(ShopCategoryDTO shopCategory)
    throws Exception {
    return toDTO(repo.save(toEntity(shopCategory)));
  }

  @Override
  public ShopCategoryDTO update(ShopCategoryDTO shopCategory) throws Exception {
    return toDTO(repo.save(toEntity(shopCategory)));
  }

  @Override
  public List<ShopCategoryDTO> list() throws Exception {
    return repo
      .findAll()
      .stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(ShopCategoryDTO::getId))
      .collect(Collectors.toList());
  }

  @Override
  public ShopCategoryDTO findById(@NonNull Long id) throws Exception {
    return toDTO(repo.findById(id).get());
  }

  @Override
  public void delete(Long id) throws Exception {
    repo.deleteById(id);
  }

  private ShopCategoryDTO toDTO(ShopCategory shopCategory) {
    return mapper.map(shopCategory, ShopCategoryDTO.class);
  }

  private ShopCategory toEntity(ShopCategoryDTO shopCategoryDTO) {
    return mapper.map(shopCategoryDTO, ShopCategory.class);
  }
}
