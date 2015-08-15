package com.xie.springcase.jpa.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class NativeSql {

	/*@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("manager1");
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNamedQuery("getNativeEntity");

		List<Object[]> list = (List<Object[]>) query.getResultList();

		List<NativeEntity> neList = castEntity(list, NativeEntity.class);
		for (NativeEntity ne : neList) {
			System.out.println(ne.getTid() + ":" + ne.getUsername());
		}

		// persistEntity(factory, entityManager);
	}*/

	/**
	 * 通用实体转换方法,将JPA返回的数组转化成对应的实体集合,这里通过泛型和反射实现
	 * 
	 * @param <T>
	 * @param list
	 * @param clazz 需要转化后的类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
		List<T> returnList = new ArrayList<T>();
		Object[] co = list.get(0);
		Class[] c2 = new Class[co.length];

		// 确定构造方法
		for (int i = 0; i < co.length; i++) {
			if(co[i].getClass().equals(Boolean.class)) {
				c2[i] = Byte.class;
			} else {
				c2[i] = co[i].getClass();
			}
		}

		for (Object[] o : list) {
			Constructor<T> constructor = clazz.getConstructor(c2);
			returnList.add(constructor.newInstance(o));
		}

		return returnList;
	}

	/**
	 * 保存实体
	 * 
	 * @param factory
	 * @param entityManager
	 */
	/*@SuppressWarnings("unused")
	private static void persistEntity(EntityManagerFactory factory,
			EntityManager entityManager) {
		entityManager.getTransaction().begin();
		NativeEntity ne = new NativeEntity();
		ne.setUsername("lisi");
		ne.setPassword("3344");
		ne.setEmail("liuyong_0204@hotmail.com");

		entityManager.persist(ne);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}*/
}
