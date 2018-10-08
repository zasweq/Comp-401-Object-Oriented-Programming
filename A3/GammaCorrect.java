package a3;

public class GammaCorrect implements PixelTransformation{
	private double gamma;
	public GammaCorrect(double gamma) {
		this.gamma = gamma;
	}
	
	public Pixel transform(Pixel p) {
		double newred = Math.pow(p.getRed(), (1.0/gamma));
		double newgreen = Math.pow(p.getGreen(), (1.0/gamma));
		double newblue = Math.pow(p.getBlue(), (1.0/gamma));
		Pixel newpixel = new ColorPixel(newred, newgreen, newblue);
		return newpixel;
	}

}
