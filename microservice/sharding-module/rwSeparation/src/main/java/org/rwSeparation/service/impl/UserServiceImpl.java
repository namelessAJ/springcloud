package org.rwSeparation.service.impl;

import java.util.List;

import org.rwSeparation.dao.mybatis.mapper.UserDOMapper;
import org.rwSeparation.dao.mybatis.models.UserDO;
import org.rwSeparation.service.UserService;
import org.rwSeparation.service.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDOMapper userDOMapper;

	@Override
	public UserDO selectByPrimaryKey(Integer id) {
		return userDOMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserDO info(UserVO userVO) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userVO, userDO);
		return userDOMapper.selectByEntity(userDO);
	}

	@Override
	public int delete(UserVO userVO) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userVO, userDO);
		return userDOMapper.delete(userDO);
	}

	@Override
	@Transactional
	public int add(UserVO userVO) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userVO, userDO);
		return userDOMapper.insert(userDO);
	}

	@Override
	public int update(UserVO userVO) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userVO, userDO);
		return userDOMapper.update(userDO);
	}

	@Override
	public List<UserDO> findList(UserVO userVO) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userVO, userDO);
		return userDOMapper.findList(userDO);
	}
}
