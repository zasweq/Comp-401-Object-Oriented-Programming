package a3;

public class MonochromePicture implements Picture{
	private int width;
	private int height;
	private Pixel value;
	public MonochromePicture(int width, int height, Pixel value) {
		if(width<=0) {
			throw new IllegalArgumentException("Width must be positive"); //illegal argument means argument passed to method is wrong, extends runtime exception
		}
		if(height<=0) {
			throw new IllegalArgumentException("Height must be positive");
		}
		if(value==null) {
			throw new IllegalArgumentException("Pixel cannot be null");
		}
		this.width = width;
		this.height = height;
		this.value = value;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Pixel getPixel(int x, int y) {
		if(x<0||y<0) {
			throw new IllegalArgumentException("Value must be positive");
		}
		if(x>(width-1)||y>(height-1)) { //did not do -1 originally, -1 as the array has up to width-1 and height-1 instances
			throw new IllegalArgumentException("Value is out of range");
		}
		return value; //immutable, so even if it gets painted on it will create another object and return that, the main pictures stays the same
	}
	
	public Picture paint(int x, int y, Pixel p){
		Picture created = new MutablePixelArrayPicture(width, height, value); //creates another object to return
		created.paint(x, y, p); //paints created picture
		return created; //returns created picture, lower ones will follow same structure
	}
	
	public Picture paint(int x, int y, Pixel p, double factor){
		Picture created = new MutablePixelArrayPicture(width, height, value);
		created.paint(x, y, p, factor);
		return created;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture created = new MutablePixelArrayPicture(width, height, value);
		created.paint(ax, ay, bx, by, p); //this caused a stack overflow because i did say created.paint smh
		return created;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture created = new MutablePixelArrayPicture(width, height, value);
		created.paint(ax, ay, bx, by, p, factor);
		return created;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture created = new MutablePixelArrayPicture(width, height, value);
		created.paint(cx, cy, radius, p);
		return created;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture created = new MutablePixelArrayPicture(width, height, value);
		created.paint(cx, cx, radius, p, factor);
		return created;
	}
}