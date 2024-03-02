package com.prenotapp._repo;

import org.springframework.stereotype.Repository;

import com.prenotapp._model.Social;

@Repository
public interface ISocialRepo extends IGenericRepo<Social, Integer> {}
