package com.manulife.top5website.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.manulife.top5website.App;
import com.manulife.top5website.CsvReader;
import com.manulife.top5website.FileMonitor;
import com.manulife.top5website.exception.InvalidFileFormatException;
import com.manulife.top5website.service.MonitorService;
import com.manulife.top5website.util.DateUtils;
import com.manulife.top5website.util.FileUtils;

public class MonitorServiceImpl implements MonitorService {

	final Logger logger = Logger.getLogger(MonitorServiceImpl.class);
	
	FileMonitor fileMonitor;
	
	CsvReader csvReader = new CsvReader();

	@Override
	public void startMonitor(FileMonitor fileMonitor) throws IOException, InterruptedException {
		preprocess(fileMonitor.getLocation());
		this.fileMonitor = fileMonitor;
		final Path path = FileSystems.getDefault().getPath(fileMonitor.getLocation());
		
		try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
			final WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			while (true) {
				final WatchKey wk = watchService.take();
				for (WatchEvent<?> event : wk.pollEvents()) {
					final Path newFile = (Path) event.context();
					try {
						validate(newFile);
						process(newFile);
						doArchive(newFile);
					} catch (InvalidFileFormatException e) {
						logger.error(e);
						doError(newFile);
					}

				}
				boolean valid = wk.reset();
				if (!valid) {
					logger.debug("Key has been unregisterede");
				}
			}
		}
	}

	public void preprocess(String folder) {
		FileUtils.removeFilesFromFolderTest(folder);
		
	}

	private void doArchive(Path newFile) {
		File file = new File(fileMonitor.getLocation() + "//" + newFile);
		file.renameTo(
				new File(fileMonitor.getArchiveLocation() + "//" + newFile + DateUtils.formatDateTime(new Date())));

	}

	private void process(Path newFile) throws FileNotFoundException {
		
		CsvReader csvReader = new CsvReader(fileMonitor.getLocation() + "//" + newFile.getFileName().toString(), fileMonitor.getSeperator());
		csvReader.process();
		
	}

	private void doError(Path newFile) {
		File errorFile = new File(fileMonitor.getLocation() + "//" + newFile);
		errorFile.renameTo(
				new File(fileMonitor.getErrorLocation() + "//" + newFile  + DateUtils.formatDateTime(new Date())));

	}

	private void validate(Path newFile) throws InvalidFileFormatException {
		FileUtils.validateMonitorCsv(newFile, fileMonitor.getFileType());

	}

}
