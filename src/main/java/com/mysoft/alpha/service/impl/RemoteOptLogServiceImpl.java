package com.mysoft.alpha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.alpha.dao.RemoteOptLogDao;
import com.mysoft.alpha.entity.RemoteOptLog;
import com.mysoft.alpha.service.RemoteOptLogService;

@Service
public class RemoteOptLogServiceImpl implements RemoteOptLogService{

	@Autowired
	private RemoteOptLogDao remoteOptLogDao;
	
	@Override
	public RemoteOptLog save(RemoteOptLog remoteOptLog) {
		return remoteOptLogDao.save(remoteOptLog);
	}

	@Override
	public List<RemoteOptLog> findAll() {
		return remoteOptLogDao.findAll();
	}

}
