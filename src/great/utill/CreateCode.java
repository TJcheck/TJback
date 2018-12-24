package great.utill;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class CreateCode {
	/**
	 * 条形码编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	//输出条形码到指定路径
	public void encode(String contents, int width, int height, String imgPath) {
		// 保证最小为70*25的大小
		int codeWidth = Math.max(70, width);
		int codeHeight = Math.max(25, height);
		try {
			// 使用EAN_13编码格式进行编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, codeHeight,
					null);
			// 生成png格式的图片保存到imgPath路径
			MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//输出条形码到web页面
	public void encode(String contents, int width, int height, OutputStream stream) {
		// 保证最小为70*25的大小
		int codeWidth = Math.max(70, width);
		int codeHeight = Math.max(25, height);
		try {
			// 使用EAN_13编码格式进行编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, codeHeight,
					null);
			// 生成png格式的图片保存到imgPath路径
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//输出二维码到web页面
	public void qrcode(String contents, int width, int height, OutputStream stream) {
		// 保证最小为70*25的大小
		int codeWidth = Math.max(100, width);
		int codeHeight = Math.max(100, height);
		try {
			// 使用EAN_13编码格式进行编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, codeWidth, codeHeight,
					null);
			// 生成png格式的图片保存到imgPath路径
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//输出二维码到指定路径下
	public void qrcode(String contents, int width, int height, String imgPath) {
		// 保证最小为70*25的大小
		int codeWidth = Math.max(100, width);
		int codeHeight = Math.max(100, height);
		try {
			// 使用EAN_13编码格式进行编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, codeWidth, codeHeight,
					null);
			// 生成png格式的图片保存到imgPath路径
			MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析条形码
	 * 
	 * @param imgPath
	 * @return
	 */
	public String decode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			result = new MultiFormatReader().decode(bitmap, null);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}