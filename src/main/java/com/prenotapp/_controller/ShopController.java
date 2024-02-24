package com.prenotapp._controller;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import com.prenotapp._service.IShopService;
import com.prenotapp.exception.ModelNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ShopDTO>> list() throws Exception {
        List<ShopDTO> list = shopService
                .list()
                .stream()
                .map(shop -> mapper.map(shop, ShopDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> listById(@PathVariable("id") Integer id)
            throws Exception {
        ShopDTO shopDTO;
        Shop shop = shopService.listById(id);
        if (shop == null) {
            throw new ModelNotFoundException("Shop not found with ID: " + id);
        } else {
            shopDTO = mapper.map(shop, ShopDTO.class);
        }
        return new ResponseEntity<>(shopDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody ShopDTO shopDTO)
            throws Exception {
        Shop shop = mapper.map(shopDTO, Shop.class);
        Shop registeredShop = shopService.register(shop);
        ShopDTO registeredDTO = mapper.map(registeredShop, ShopDTO.class);

        URI location = new URI("/shops/" + registeredDTO.getId());
        return ResponseEntity.created(location).build();
    }

    // Other endpoints like update, delete, etc. can be implemented similarly

}
