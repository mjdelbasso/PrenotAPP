package com.prenotapp._service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prenotapp._model.Social;
import com.prenotapp._repo.IGenericRepo;
import com.prenotapp._repo.ISocialRepo;
import com.prenotapp._service.ISocialService;

@Service
public class SocialServiceImpl
  extends CRUDImpl<Social, Integer>
  implements ISocialService {

  @Autowired
  private ISocialRepo shopRepo;

  
  @Override
  protected IGenericRepo<Social, Integer> getRepo() {
    return shopRepo;
  }
}
