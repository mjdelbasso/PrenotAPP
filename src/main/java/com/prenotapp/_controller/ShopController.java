package com.prenotapp._controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import com.prenotapp._service.IShopService;
import com.prenotapp.exception.ModelNotFoundException;
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
    return new ResponseEntity<>((lstShopDTO), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopDTO> findById(@PathVariable("id") Integer id)
    throws Exception {
    return new ResponseEntity<>(toDTO(service.findById(id)), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<ShopDTO> register(@RequestBody ShopDTO shopDTO)
    throws Exception {
    Shop shop = service.register(toEntity(shopDTO));
    return new ResponseEntity<>(toDTO(shop), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ShopDTO> update(
    @PathVariable("id") Integer id,
    @RequestBody ShopDTO shopDTO
  ) throws Exception {
    Shop shop = toEntity(shopDTO);
    shop.setId(id);
    shop = service.update(shop);
    return new ResponseEntity<>(toDTO(shop), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

  @PostMapping("/new-category/{idShop}/{idCategory}")
  public ResponseEntity<ShopDTO> addCategoryToShop(
    @PathVariable("idShop") Integer idShop,
    @PathVariable("idCategory") Integer idCategory
  ) throws Exception {
    return new ResponseEntity<>(
      service.addCategoryToShop(idShop, idCategory),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/remove-category/{idShop}/{idCategory}")
  public ResponseEntity<Void> removeCategoryFromShop(
    @PathVariable("idShop") Integer idShop,
    @PathVariable("idCategory") Integer idCategory
  ) throws Exception {
    service.removeCategoryFromShop(idShop, idCategory);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public ShopDTO toDTO(Shop shop) {
    return mapper.map(shop, ShopDTO.class);
  }

  public Shop toEntity(ShopDTO shopDTO) {
    return mapper.map(shopDTO, Shop.class);
  }
}
