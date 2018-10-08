package a3;

public class GradientPicture implements Picture{
	private int width;
	private int height;
	private Pixel topleft;
	private Pixel topright;
	private Pixel bottomleft;
	private Pixel bottomright;
	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if(width<=0) {
			throw new IllegalArgumentException("Width must be positive");
		}
		if(height<=0) {
			throw new IllegalArgumentException("Height must be positive");
		}
		if((upper_left==null||upper_right==null)||(lower_left==null||lower_right==null)) {
			throw new IllegalArgumentException("One of the pixel values is null");
		}
		this.width = width;
		this.height = height;
		topleft = upper_left;
		topright = upper_right;
		bottomleft = lower_left;
		bottomright = lower_right;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Pixel getPixel(int x, int y) { //NOT IMPLEMETED
		if(x>(width-1)||y>(height-1)) {
			throw new IllegalArgumentException("Value is out of range");
		}
		//multiply by double since two ints divided will return an imprecise point
		double factory = ((y)/(height-1.0)); //factor y is the factor mixing the data points
		//System.out.println(factory);
		double factorx = ((x)/(width-1.0));
		//System.out.println(factorx);
		Pixel leftside = topleft.blend(bottomleft, factory); //calculating two pixels on end, then calculating one in middle from blend of these two
		Pixel rightside = topright.blend(bottomright, factory);
		Pixel xy = leftside.blend(rightside, factorx);
		
		return xy;
	}
	
	//construct 2d array of pixels, then paint, then return
	public Picture paint(int x, int y, Pixel p) {
		Pixel[][] picture = create();
		Picture created = new MutablePixelArrayPicture(picture);
		created.paint(x, y, p);
		return created;
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		Pixel[][]picture = create();
		Picture created = new MutablePixelArrayPicture(picture);
		created.paint(x, y, p, factor);
		return created;
	}
	
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Pixel[][]picture = create();
		Picture created = new MutablePixelArrayPicture(picture);
		created.paint(ax, ay, by, bx, p);
		return created;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Pixel[][]picture = create();
		Picture created = new MutablePixelArrayPicture(picture);
		created.paint(ax, ay, by, bx, p, factor);
		return created;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Pixel[][]picture = create();
		Picture created = new MutablePixelArrayPicture(picture);
		created.paint(cx, cy, radius, p);
		return created;
	}
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Pixel[][]picture = create();
		Picture created = new MutablePixelArrayPicture(picture);
		created.paint(cx, cy, radius, p, factor);
		return created;
	}
	
	
	private Pixel[][] create(){ //helper function for creating array
		Pixel[][] picture = new Pixel[width][height]; //will fill this picture similar to getpixel method, except x and y will be iterated through a two for loops
		for(int i = 0; i<width; i++) { //corresponds to x position
			for(int j = 0; j<height; j++) { //corresponds to y position
				double factory = (j*1.0)/(height-1.0); //factor y is the factor mixing the data points
				double factorx = (i*1.0)/(width-1.0);
				Pixel leftside = topleft.blend(bottomleft, factory); //calculating two pixels on end, then calculating one in middle from blend of these two
				Pixel rightside = topright.blend(bottomright, factory);
				Pixel xy = leftside.blend(rightside, factorx);
				picture[i][j] = xy;
			}
		}
		return picture;
	}
}