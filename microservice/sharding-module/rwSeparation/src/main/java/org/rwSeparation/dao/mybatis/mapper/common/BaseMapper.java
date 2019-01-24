package org.rwSeparation.dao.mybatis.mapper.common;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseMapper<T> {

	/**
	 * 获取单条数据
	 *
	 * @param id
	 * @return
	 */
	public T selectByPrimaryKey(Integer id);

	/**
	 * 获取单条数据
	 *
	 * @param entity
	 * @return
	 */
	public T selectByEntity(T entity);

	/**
	 * 查询数据列表
	 *
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);

	/**
	 * 插入数据
	 *
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 更新数据
	 *
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 *
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
}
