package com.manulife.top5website.util;

import java.io.File;
import java.nio.file.Path;

import com.manulife.top5website.exception.InvalidFileFormatException;

public class FileUtils {
	public static void removeFilesFromFolderTest(String folder) {
		File dir = new File(folder);
		for(File file: dir.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
	}

	public static void validateMonitorCsv(Path newFile, String fileType) throws InvalidFileFormatException {
		if (!newFile.getFileName().toString().substring(newFile.getFileName().toString().length() - 3, newFile.getFileName().toString().length()).equals(fileType)) {
			throw new InvalidFileFormatException(
					"Incorrect file extension. Expected csv, found " + newFile.getFileName());
		}
		
	}
	
}
