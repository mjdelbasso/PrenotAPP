package com.prenotapp._controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
   
    List<ShopDTO> lstShopDTO = service.listAllShops();
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

  @SuppressWarnings("null")
  @GetMapping("/hateoas/{id}")
  public EntityModel<ShopDTO> findByIdHateoas(@PathVariable("id") Integer id)
    throws Exception {
    Shop shop = service.findById(id);
    if (shop == null) {
      throw new ModelNotFoundException("Shop not found with ID: " + id);
    }
    ShopDTO shopDTO = mapper.map(shop, ShopDTO.class);
    EntityModel<ShopDTO> model = EntityModel.of(shopDTO);
    model.add(linkTo(methodOn(this.getClass()).list()).withRel("all-shops"));
    return model;
  }

  
}
