package com.manulife.top5website;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.manulife.BeanFactory;
import com.manulife.top5website.dao.SiteDao;
import com.manulife.top5website.model.Site;
@Component
public class CsvReader {
	final Logger logger = Logger.getLogger(FileMonitor.class);
	
	SiteDao siteDao;
	
	private static final char DEFAULT_QUOTE = '"';
	
	private static final char DEFAULT_SEPARATOR = ',';
	
	String fileName;
	
	String separator;
	
	
	public CsvReader() {
		
	}
	
	public CsvReader(String fileName, String separator) {
		this.siteDao = BeanFactory.createInstance(SiteDao.class, "siteDao");
    	
		this.fileName = fileName;
		this.separator = separator;
	}
	
	public void process() throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new File(fileName));
		List<Site> sites = new ArrayList<Site>();
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            if (!isHeader(line)) {
	            Site site = new Site(line.get(0), line.get(1), line.get(2));
	            sites.add(site);
            }
        }
        this.siteDao.save(sites);
        scanner.close();
	}
	
	private boolean isHeader(List<String> line) {
		if (line.get(0).equalsIgnoreCase("date")) {
			return true;
		} else {
			return false;
		}
		
	}

	public List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, separator.charAt(0), ' ');
    }
	public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
    

}
