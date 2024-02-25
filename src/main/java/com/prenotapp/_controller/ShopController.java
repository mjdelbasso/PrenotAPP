package com.prenotapp._controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import com.prenotapp._service.IShopService;
import com.prenotapp.exception.ModelNotFoundException;

@RestController
@RequestMapping("/shops")
public class ShopController {

  @Autowired
  private IShopService service;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<ShopDTO>> list() throws Exception {
    List<Shop> lstShop = service.list();
    List<ShopDTO> lstShopDTO = lstShop
      .stream()
      .map(s -> mapper.map(s, ShopDTO.class))
      .sorted(Comparator.comparing(ShopDTO::getId))
      .collect(Collectors.toList());
    return new ResponseEntity<>(lstShopDTO, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopDTO> listById(@PathVariable("id") Integer id)
    throws Exception {
    Shop shop = service.listById(id);
    ShopDTO shopDTO = mapper.map(shop, ShopDTO.class);
    return new ResponseEntity<>(shopDTO, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<ShopDTO> register(@RequestBody ShopDTO shopDTO)
    throws Exception {
    Shop shop = mapper.map(shopDTO, Shop.class);
    shop = service.register(shop);
    shopDTO = mapper.map(shop, ShopDTO.class);
    return new ResponseEntity<>(shopDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ShopDTO> update(
    @PathVariable("id") Integer id,
    @RequestBody ShopDTO shopDTO
  ) throws Exception {
    Shop shop = mapper.map(shopDTO, Shop.class);
    shop.setId(id);
    shop = service.update(shop);
    shopDTO = mapper.map(shop, ShopDTO.class);
    return new ResponseEntity<>(shopDTO, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @SuppressWarnings("null")
  @GetMapping("/hateoas/{id}")
  public EntityModel<ShopDTO> listByIdHateoas(@PathVariable("id") Integer id)
    throws Exception {
    Shop shop = service.listById(id);
    if (shop == null) {
      throw new ModelNotFoundException("Shop not found with ID: " + id);
    }
    ShopDTO shopDTO = mapper.map(shop, ShopDTO.class);
    EntityModel<ShopDTO> model = EntityModel.of(shopDTO);
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).list());
    model.add(linkTo.withRel("all-shops"));
    return model;
  }

  @PostMapping("/{shopId}/categories/{categoryId}")
  public ResponseEntity<Void> addCategorytoShop(
    @PathVariable("shopId") Integer shopId,
    @PathVariable("categoryId") Integer categoryId
  ) {
    try {
      service.addCategorytoShop(shopId, categoryId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
