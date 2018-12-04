package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.Tdep;

public interface DepDao {
	//根据上级取部门
	public List<Tdep> queryByPid(int pid);
}
