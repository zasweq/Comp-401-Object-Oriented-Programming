package a3;

public class TransformedPicture implements Picture{
	private Picture source;
	private PixelTransformation xform;
	public TransformedPicture(Picture source, PixelTransformation xform) {
		this.source = source;
		this.xform = xform;
	}
	public int getWidth() {
		return source.getWidth();
	}
	
	public int getHeight() {
		return source.getHeight();
	}

	public Pixel getPixel(int x, int y) { //do you need to also transform paint methods?
		Pixel a = xform.transform(source.getPixel(x, y));
		return a;
	}

	public Picture paint(int x, int y, Pixel p) {
		return source.paint(x, y, p);
	}

	public Picture paint(int x, int y, Pixel p, double factor) { //depends on what implementation of the picture it is, but the interface specifies every implementation has to have these methods so you can call it
		return source.paint(x, y, p, factor);
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return source.paint(ax, ay, bx, by, p);
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		return source.paint(ax, ay, bx, by, p, factor);
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return source.paint(cx, cy, radius, p);
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		return source.paint(cx, cy, radius, p, factor);
	}

}
