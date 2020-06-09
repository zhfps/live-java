package com.live.zhf.utils;

import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CodeUtil {

	public static void responseJson(HttpServletResponse response, int status, Object data) {
		try {
			Gson json = new Gson();
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "*");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(status);

			response.getWriter().write(json.toJson(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void responseBufferedImage(HttpServletRequest request, HttpServletResponse response, BufferedImage bufferedImage) throws IOException {
		if (bufferedImage != null) {
			// 将bufferedImage写入HttpServletResponse OutputStream中
			ServletOutputStream out = null;
			try {
				response.setDateHeader("Expires", 0);
				response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
				response.addHeader("Cache-Control", "post-check=0, pre-check=0");
				response.setHeader("Pragma", "no-cache");
				response.setContentType("image/jpeg");
				out = response.getOutputStream();
				ImageIO.write(bufferedImage, "jpg", out);
				out.flush();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}

	}
}
