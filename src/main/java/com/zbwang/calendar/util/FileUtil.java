package com.zbwang.calendar.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtil {

	public static String readFile(String fileName) {
		StringBuilder content = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			try {
				String line;
				while ((line = br.readLine()) != null) {
					content.append(line);
				}
			} finally {
				br.close();
			}
		} catch (Exception e) {
			LogUtil.serviceLog.error("Fail to read file into memory.", e);
		}
		return content.toString();
	}
}
