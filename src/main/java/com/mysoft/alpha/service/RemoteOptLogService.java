package com.mysoft.alpha.service;

import java.util.List;

import com.mysoft.alpha.entity.RemoteOptLog;


/**
 * 接口操作日志
 *
 */
public interface RemoteOptLogService {
	RemoteOptLog save(RemoteOptLog remoteOptLog);
   
	List<RemoteOptLog> findAll();
}
