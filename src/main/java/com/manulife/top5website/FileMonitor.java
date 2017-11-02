package com.manulife.top5website;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.manulife.top5website.exception.InvalidFileFormatException;
import com.manulife.top5website.util.DateUtils;


@Component
public class FileMonitor {
	
	final Logger logger = Logger.getLogger(FileMonitor.class);
	
	
	String location;
	
	String errorLocation;
	
	String archiveLocation;
	
	
	String fileType;
	
	String seperator;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSeperator() {
		return seperator;
	}

	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}
	
	

	public String getErrorLocation() {
		return errorLocation;
	}

	public void setErrorLocation(String errorLocation) {
		this.errorLocation = errorLocation;
	}

	
	public String getArchiveLocation() {
		return archiveLocation;
	}

	public void setArchiveLocation(String archiveLocation) {
		this.archiveLocation = archiveLocation;
	}

	@Override
	public String toString() {
		return "FileMonitor [location=" + location + ", fileType=" + fileType + ", seperator=" + seperator + "]";
	}
	
	
}
