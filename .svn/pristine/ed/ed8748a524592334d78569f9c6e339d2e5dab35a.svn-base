package com.zbwang.calendar.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import com.zbwang.calendar.constant.Constants;

public class RequestUtil {

	public static Map<String, Object> getElements(HttpServletRequest request) {
		Map<String, Object> elements = new HashMap<String, Object>();
		try {
			List<FileItem> list = getRequestItems(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String value = item.getString("UTF-8");
					String key = item.getFieldName();
					if (StringUtils.isNotBlank(value)) {
						if (elements.containsKey(key)) {
							elements.put(key, StringUtils.join(new String[] { value, (String) elements.get(key) }, ","));
						} else {
							elements.put(item.getFieldName(), value);
						}
					}
				} else if (Constants.picContentTypes.contains(item.getContentType())) {
					String key = item.getFieldName();
					if (elements.containsKey(key)) {
						((List<FileItem>) elements.get(key)).add(item);
					} else {
						List<FileItem> items = new ArrayList<FileItem>();
						items.add(item);
						elements.put(item.getFieldName(), items);
					}
				}
			}
		} catch (Exception e) {
			LogUtil.serviceLog.error("Fail to convert request information from request.", e);
		}
		return elements;
	}

	private static List<FileItem> getRequestItems(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		return upload.parseRequest(request);
	}
}
