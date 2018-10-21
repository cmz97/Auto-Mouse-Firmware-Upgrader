package MainPackage;

import java.awt.image.BufferedImage;

public class ImageComparer {
	boolean same = false;
	int counter = 0;
	int error = 0;
	int numberOfPixel = 0;
	
	public ImageComparer(BufferedImage image1,BufferedImage image2, int tolerrance){
		error = tolerrance;
		numberOfPixel = image1.getWidth() * image1.getHeight();
		for(int i = 0; i < image1.getWidth() ; i++){
			for(int j = 0 ; j < image1.getHeight() ; j++){
				if(image1.getRGB(i, j) != image2.getRGB(i, j))counter ++;
			}
		}
		
	}
	
	
	public boolean isSame(){
		return (counter - numberOfPixel)/(numberOfPixel) < error*0.01;
	}
}
