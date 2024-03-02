package com.prenotapp._controller;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import com.prenotapp._service.IShopCategoryService;
import com.prenotapp._service.IShopService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shops")
public class ShopController {

  @Autowired
  private IShopService service;

  @Autowired
  private IShopCategoryService scService;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<ShopDTO>> list() throws Exception {
    List<ShopDTO> lstShopDTO = service
      .list()
      .stream()
      .map(s -> mapper.map(s, ShopDTO.class))
      .sorted(Comparator.comparing(ShopDTO::getId))
      .collect(Collectors.toList());
    return ResponseEntity.ok(lstShopDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopDTO> findById(@PathVariable("id") Integer id)
    throws Exception {
    return ResponseEntity.ok(mapper.map(service.findById(id), ShopDTO.class));
  }

  @PostMapping("/register")
  public ResponseEntity<ShopDTO> register(@RequestBody ShopDTO shopDTO)
    throws Exception {
    Shop shop = service.register(mapper.map(shopDTO, Shop.class));
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(mapper.map(shop, ShopDTO.class));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ShopDTO> update(
    @PathVariable("id") Integer id,
    @RequestBody ShopDTO shopDTO
  ) throws Exception {
    Shop shop = mapper.map(shopDTO, Shop.class);
    shop.setId(id);
    shop = service.update(shop);
    return ResponseEntity.ok(mapper.map(shop, ShopDTO.class));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{shopId}/categories/{categoryId}")
  public ResponseEntity<Void> addCategorytoShop(
    @PathVariable("shopId") Integer shopId,
    @PathVariable("categoryId") Integer categoryId
  ) {
    try {
      scService.addCategorytoShop(shopId, categoryId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{shopId}/categories/{categoryId}")
  public ResponseEntity<Void> removeCategoryFromShop(
    @PathVariable("shopId") Integer shopId,
    @PathVariable("categoryId") Integer categoryId
  ) {
    try {
      scService.removeCategoryFromShop(shopId, categoryId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
