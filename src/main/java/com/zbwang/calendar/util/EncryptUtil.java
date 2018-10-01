package com.zbwang.calendar.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	private final static String DES = "DES";
	private static String key = "bobomeilin@1234";

	public static String encrypt(String data) {
		byte[] bt;
		try {
			bt = encrypt(data.getBytes(), key.getBytes());
			String strs = new BASE64Encoder().encode(bt);
			return strs;
		} catch (Exception e) {
			LogUtil.serviceLog.error("Fail to encrypt " + data, e);
		}
		return data;
	}

	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	public static String decrypt(String data) {
		if (data == null)
			return null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] buf = decoder.decodeBuffer(data);
			byte[] bt;
			bt = decrypt(buf, key.getBytes());
			return new String(bt);
		} catch (Exception e) {
			LogUtil.serviceLog.error("Fail to decrypt " + data, e);
		}
		return data;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("") + 1);
		System.out.println(decrypt("2"));
	}

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
