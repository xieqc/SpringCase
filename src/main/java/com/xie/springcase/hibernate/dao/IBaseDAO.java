package com.xie.springcase.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Xie
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public abstract interface IBaseDAO<T> {
	public void save(T transientInstance) ;
	public void update(T transientInstance) throws RuntimeException;
	public void delete(T persistentInstance) ;
	public void deleteAll(Collection<T> entities);
	public T findById(Serializable id);
	public List<T> findByIds(final Object[] ids);
	public List<T> findByExample(T instance);
	public List<T> findByProperty(String propertyName, Object value);
	public List<T> findByName(Object name);
	public List<T> findAll();
	public void saveOrUpdate(T transientInstance);
	public void batchDelete(final Object[] ids);
}
