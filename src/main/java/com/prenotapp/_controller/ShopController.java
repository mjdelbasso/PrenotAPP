package com.prenotapp._controller;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._service.IShopService;
import java.util.List;
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

  @GetMapping
  public ResponseEntity<List<ShopDTO>> list() throws Exception {
    return new ResponseEntity<>(service.list(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShopDTO> findById(@PathVariable("id") Long id)
    throws Exception {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<ShopDTO> register(@RequestBody ShopDTO shopDTO)
    throws Exception {
    return new ResponseEntity<>(service.register(shopDTO), HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ShopDTO> update(
    @PathVariable("id") Long id,
    @RequestBody ShopDTO shopDTO
  ) throws Exception {
    shopDTO.setId(id);
    return new ResponseEntity<>(service.update(shopDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/new-category/{idShop}/{idCategory}")
  public ResponseEntity<ShopDTO> addCategoryToShop(
    @PathVariable("idShop") Long idShop,
    @PathVariable("idCategory") Long idCategory
  ) throws Exception {
    return new ResponseEntity<>(
      service.addCategoryToShop(idShop, idCategory),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/remove-category/{idShop}/{idCategory}")
  public ResponseEntity<Void> removeCategoryFromShop(
    @PathVariable("idShop") Long idShop,
    @PathVariable("idCategory") Long idCategory
  ) throws Exception {
    service.removeCategoryFromShop(idShop, idCategory);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
