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
	 //图片转化成base64字符串
	public static String getImageToBase64(String imgPath) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgPath);
			data = new byte[in.available()];//将图片文件转化为字节数组
			in.read(data);
			in.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

		//generateImageFromBase64
		// base64字符串转化成图片
	public static void generateImageFromBase64(String imgStr, String imgFilePath) {
		if (imgStr == null)return;
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			// Base64解码
			byte[] buffer = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < buffer.length; ++i) {
				if (buffer[i] < 0) {
					buffer[i] += 256;
				}
			}
			// 生成jpeg图片
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
