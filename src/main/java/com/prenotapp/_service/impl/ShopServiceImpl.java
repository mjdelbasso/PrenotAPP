package com.prenotapp._service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._dto.ShopDTO;
import com.prenotapp._model.Shop;
import com.prenotapp._repo.IGenericRepo;
import com.prenotapp._repo.IShopRepo;
import com.prenotapp._service.IShopService;

import lombok.NonNull;

@Service
public class ShopServiceImpl extends CRUDImpl<Shop, Integer> implements IShopService {

    @Autowired
    private IShopRepo shopRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected IGenericRepo<Shop, Integer> getRepo() {
        return shopRepo;
    }

    @Override
    public List<ShopDTO> listAllShops() {
        List<ShopDTO> lstShopDTO = new ArrayList<>();
        for (Shop shop : shopRepo.findAll()) {
            lstShopDTO.add(convertShopToShopDTO(shop));
        }
        return lstShopDTO;
    }

    @Override
    public ShopDTO listShopById(@NonNull Integer id) {
        Optional<Shop> optionalShop = shopRepo.findById(id);
        return optionalShop.map(shop -> modelMapper.map(shop, ShopDTO.class)).orElse(null);
    }

    private ShopDTO convertShopToShopDTO(Shop shop) {
        return modelMapper.map(shop, ShopDTO.class);
    }
}


