package com.ruicai.manage.login.servlet;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

/**
 * 验证码生成页面
 * @author liukw
 * date 2010-4-16 
 */	
public class GetRandNum extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String CONTENT_TYPE = "text/html; charset=GBK";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		int width = 90, height = 40;
		
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.createGraphics();

		Random random = new Random();
		
		g.setColor(getRandColor(180, 250));
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < 5; i++) {
			g.setColor(getRandColor(50, 100));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 4, 4);
		}
		
		g.setFont(new Font("", Font.PLAIN, 40));
		
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(80), 20 + random.nextInt(100), 20 + random.nextInt(90)));
			g.drawString(rand, (17 + random.nextInt(3)) * i + 8, 34);
 
			for (int k = 0; k < 12; k++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(9);
				int yl = random.nextInt(9);
				g.drawLine(x, y, x + xl, y + yl);
			}
		}
		
		request.getSession().setAttribute("randCode", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	public Color getRandColor(int fc, int bc) {
		Random rand = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + rand.nextInt(bc - fc);
		int g = fc + rand.nextInt(bc - fc);
		int b = fc + rand.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
