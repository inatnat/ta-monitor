package com.manulife.top5website.tests.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Ignore;
import org.junit.Test;

import com.manulife.BeanFactory;
import com.manulife.top5website.CsvReader;
import com.manulife.top5website.FileMonitor;

import com.manulife.top5website.util.FileUtils;

public class MonitorServiceTest {

	@Ignore
	@Test
	public void removeFilesFromFolderTest() {
		FileMonitor fileMonitor = BeanFactory.createInstance(FileMonitor.class, "fileMonitor");
    	
		FileUtils.removeFilesFromFolderTest(fileMonitor.getLocation());
		File file = new File(fileMonitor.getLocation());
		assert(file.list().length == 0);
	}
	
	@Test
	public void readCsvTest() {
		CsvReader csvReader = new CsvReader("//app//test.csv", "|");
		try {
			csvReader.process();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
}
