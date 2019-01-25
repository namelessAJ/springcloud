package org.rwSeparation.controller;

import java.util.List;

import org.rwSeparation.dao.mybatis.models.UserDO;
import org.rwSeparation.service.UserService;
import org.rwSeparation.service.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sharding")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String getUser(@RequestBody UserVO userVO) {
		UserDO userDO = userService.info(userVO);
		return userDO != null ? userDO.toString() : "";
	}

	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	public String findList(@RequestBody UserVO userVO) {
		StringBuilder str = new StringBuilder();
		List<UserDO> list = userService.findList(userVO);
		list.stream().forEach(u -> {
			str.append(u.toString()).append(";");
		});
		return str.toString();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestBody UserVO userVO) {
		userService.add(userVO);
		return "success";
	}
}
