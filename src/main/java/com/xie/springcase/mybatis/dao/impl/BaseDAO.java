package com.xie.springcase.mybatis.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.xie.springcase.mybatis.dao.IBaseDAO;
import com.xie.springcase.mybatis.utils.BeanUtils;

@Repository
public abstract class BaseDAO<T> implements IBaseDAO<T> {
	@Autowired(required = true)
	protected SqlSession sqlSessionTemplate;
	
	protected T claz;
	protected String entityName;
	
	protected BaseDAO(T claz) {
		this.entityName = claz.getClass().getSimpleName();
		this.claz = claz;
	}
	
	public Long selectCount() {
		try {
			return sqlSessionTemplate.selectOne("selectCount");
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}
	
	public Long selectCount(T query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne("selectCount", params);
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}
	
	public T selectOne(T query) {
		Assert.notNull(query);
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne("select", params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public T selectById(Object id) {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.selectOne("selectById", id);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void insert(T entity) {
		Assert.notNull(entity);
		try {
			sqlSessionTemplate.insert("insert", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int deleteById(String id) {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.delete("deleteById", id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int delete(T query) {
		Assert.notNull(query);
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.delete("delete", params);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteAll() {
		try {
			return sqlSessionTemplate.delete("delete");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<T> selectList(T query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectList("select", params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<T> selectPageList(T query, Pageable pageable) {
		try {
			List<T> contentList = sqlSessionTemplate.selectList("select", getParams(query, pageable));
			return new PageImpl<T>(contentList, pageable, this.selectCount(query));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 设置分页
	 * @param pageInfo 分页信息
	 * @return SQL分页参数对象
	 */
	protected RowBounds getRowBounds(Pageable pageable) {
		RowBounds bounds = RowBounds.DEFAULT;
		if (null != pageable) {
			bounds = new RowBounds(pageable.getOffset(), pageable.getPageSize());
		}
		return bounds;
	}
	
	/**
	 * 获取分页查询参数
	 * @param query 查询对象
	 * @param pageable 分页对象
	 * @return Map 查询参数
	 */
	protected Map<String, Object> getParams(T query, Pageable pageable) {
		Map<String, Object> params = BeanUtils.toMap(query, getRowBounds(pageable));
		if (pageable != null && pageable.getSort() != null) {
			String sorting = pageable.getSort().toString();
			params.put("sorting", sorting.replace(":", ""));
		}
		return params;
	}
}
