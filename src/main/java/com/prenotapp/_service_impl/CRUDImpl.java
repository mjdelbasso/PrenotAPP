package com.prenotapp._service_impl;

import java.util.List;

import com.prenotapp._repo.IGenericRepo;
import com.prenotapp._service.ICRUD;

@SuppressWarnings("null")
public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

  protected abstract IGenericRepo<T, ID> getRepo();

  @Override
  public T register(T t) throws Exception {
    return getRepo().save(t);
  }

  @Override
  public T update(T t) throws Exception {
    return getRepo().save(t);
  }

  @Override
  public List<T> list() throws Exception {
    return getRepo().findAll();
  }

  @Override
  public T listById(ID id) throws Exception {
    return getRepo().findById(id).orElse(null);
  }

  @Override
  public void delete(ID id) throws Exception {
    getRepo().deleteById(id);
  }
}
