package com.xie.springcase.hibernate.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xie.springcase.hibernate.dao.IBaseDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public abstract class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {
	Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	// property constants
	public static final String NAME = "name";
	protected T claz;
	protected String entityName;

	protected BaseDAO(T claz) {
		this.entityName = claz.getClass().getSimpleName();
		this.claz = claz;
	}

	public void save(T transientInstance) {
		logger.debug("saving Object instance");
		try {
			getHibernateTemplate().save(transientInstance);
			logger.debug("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(T transientInstance) {
		logger.debug("saving Object instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			logger.debug("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	public void update(T transientInstance) throws RuntimeException {
		logger.debug("saving Object instance");
		getHibernateTemplate().update(transientInstance);
		logger.debug("save successful");
	}

	public void delete(T persistentInstance) {
		logger.debug("deleting Object instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public void deleteAll(Collection<T> entities) {
		logger.debug("deleting Collection entities");
		try {
			getHibernateTemplate().deleteAll(entities);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public void batchDelete(final Object[] ids) {
		final String queryString = "delete " + entityName + " where id in (:ids) ";
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(queryString);
				query.setParameterList("ids", ids);
				int affect = query.executeUpdate();
				// releaseSession(session);
				return affect;
			}
		});
	}

	public List<T> findByIds(final Object[] ids) {
		final String queryString = "from " + entityName + " where id in (:ids) ";
		List<T> list = (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(queryString);
						query.setParameterList("ids", ids);
						List<T> list = query.list();
						// releaseSession(session);
						return list;
					}
				});
		return list;
	}

	public T findById(Serializable id) {
		logger.debug("getting Object instance with id: " + id);
		try {
			T instance = (T) getHibernateTemplate().get(claz.getClass(), id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	public List<T> findByExample(T instance) {
		logger.debug("finding Object instance by example");
		try {
			List<T> results = (List<T>) getHibernateTemplate().findByExample(instance);
			logger.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.error("find by example failed", re);
			throw re;
		}
	}

	public List<T> findByProperty(String propertyName, Object value) {
		logger.debug("finding Object instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from " + entityName + " as model where model." + propertyName + "= ?";
			logger.debug(queryString);
			return (List<T>) getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamedParam(String hql, String[] paramName,
			Object[] paramValue) {
		Session session = super.currentSession();
		Query query = session.createQuery(hql);

		if (paramName != null) {
			for (int i = 0; i < paramName.length; i++) {
				query.setParameter(paramName[i], paramValue[i]);
			}
		}
		List list = query.list();
		// releaseSession(session);
		return list;
	}

	public List<T> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<T> findAll() {
		logger.debug("finding all Object instances");
		try {
			String queryString = "from " + entityName;
			return (List<T>) getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
}
