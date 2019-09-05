package com.accp.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ConvertImageUtil {
	private Base64ConvertImageUtil() {}
	 //getImageToBase64
	 //ͼƬת����base64�ַ���
	public static String getImageToBase64(String imgPath) {
		InputStream in = null;
		byte[] data = null;
		// ��ȡͼƬ�ֽ�����
		try {
			in = new FileInputStream(imgPath);
			data = new byte[in.available()];//��ͼƬ�ļ�ת��Ϊ�ֽ�����
			in.read(data);
			in.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		// ���ֽ�����Base64����
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}

		//generateImageFromBase64
		// base64�ַ���ת����ͼƬ
	public static void generateImageFromBase64(String imgStr, String imgFilePath) {
		if (imgStr == null)return;
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			// Base64����
			byte[] buffer = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < buffer.length; ++i) {
				if (buffer[i] < 0) {
					buffer[i] += 256;
				}
			}
			// ����jpegͼƬ
			out = new FileOutputStream(imgFilePath);
			out.write(buffer);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	
}
