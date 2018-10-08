package a3;

public class Threshold implements PixelTransformation{
	private double threshold;
	public Threshold(double threshold) {
		this.threshold = threshold;
	}
	
	public Pixel transform(Pixel p) { //DO WE NEED CONDITIONS FOR EVERY THING FOR EXCEPTIONS
		Pixel wb;
		if(p.getIntensity()>threshold) {
			wb = new GrayPixel(1.0);//wb stands for white black, white pixel
		}
		else {
			wb = new GrayPixel(0.0); //black pixel
		}
		return wb;
	}
}
