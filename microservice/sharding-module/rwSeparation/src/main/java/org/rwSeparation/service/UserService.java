package org.rwSeparation.service;

import java.util.List;

import org.rwSeparation.dao.mybatis.models.UserDO;
import org.rwSeparation.service.vo.UserVO;

public interface UserService {

	public UserDO selectByPrimaryKey(Integer id);

	public UserDO info(UserVO userVO);

	public List<UserDO> findList(UserVO userVO);

	public int delete(UserVO userVO);

	public int add(UserVO userVO);

	public int update(UserVO userVO);

}
