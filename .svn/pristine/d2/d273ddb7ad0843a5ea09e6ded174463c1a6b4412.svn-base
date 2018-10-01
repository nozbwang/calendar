package com.zbwang.calendar.util;

import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;

import com.zbwang.calendar.constant.Constants;

public class CommonUtil {

	public static String get163MusicId(String musicLink) {
		Matcher mMatcher = Constants.MUSIC_163_PATTERN_M.matcher(musicLink);
		if (mMatcher.matches()) {
			return mMatcher.group(1);
		}
		Matcher pcMatcher = Constants.MUSIC_163_PATTERN_PC.matcher(musicLink);
		if (pcMatcher.matches()) {
			return pcMatcher.group(1);
		}
		return StringUtils.EMPTY;
	}

	public static String getXiamiMusicId(String musicLink) {
		Matcher mMatcher = Constants.MUSIC_XIAMI_PATTERN_PC.matcher(musicLink);
		if (mMatcher.matches()) {
			return mMatcher.group(1);
		}
		return StringUtils.EMPTY;
	}

	public static boolean isPictureLink(String link) {
		String linkTrimed = StringUtils.trimToEmpty(link);
		return Constants.PICTURE_PATTERN.matcher(linkTrimed).matches();
	}

	public static boolean is163Music(String link) {
		return StringUtils.contains(link, "music.163.com");
	}

	public static boolean isXiamiMusic(String link) {
		return StringUtils.contains(link, "www.xiami.com");
	}

	public static String getPhotoNameLimited(String pictureName) {
		String result = StringUtils.trimToEmpty(pictureName);
		if (result.length() > Constants.PICTURE_NAME_MAX_LENGTH) {
			return result.substring(result.length() - Constants.PICTURE_NAME_MAX_LENGTH, result.length());
		}
		return result;
	}

	public static String getBookCatalog(String catalogName) {
		return Constants.catalogMap.get(catalogName);
	}
}
