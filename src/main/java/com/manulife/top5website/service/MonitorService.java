package com.manulife.top5website.service;

import java.io.IOException;

import com.manulife.top5website.FileMonitor;

public interface MonitorService {
	public void startMonitor(FileMonitor fileMonitor) throws IOException, InterruptedException;

	
}
