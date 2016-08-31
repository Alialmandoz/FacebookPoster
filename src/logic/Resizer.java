package logic;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Resizer {
	public ImageIcon resizer(String path) {
		ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		int base = 255;
		int altoImg = image.getHeight(null);
		int anchImg = image.getWidth(null);
		Image newimg = image.getScaledInstance(base,regla(base, altoImg, anchImg),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		 return imageIcon = new ImageIcon(newimg);  // transform it back
	}
	private static int regla(int base, int altoB, int anchoB) {
		int resultado = (base*altoB)/anchoB;
		return resultado;
	}
}
