package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.Tdep;

public interface DepService {
	//根据上级取部门
		public List<Tdep> queryByPid(int pid);
}
