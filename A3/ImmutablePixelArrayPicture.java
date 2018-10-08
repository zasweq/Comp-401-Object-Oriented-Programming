package a3;

public class ImmutablePixelArrayPicture implements Picture{
	private int width;
	private int height;
	final Pixel[][] picture; //convention to make it call
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		if(pixel_array==null) {//check if array is null
			throw new IllegalArgumentException("Array is Null");
		}
		width = pixel_array.length;
		height = pixel_array[0].length;
		if(width<=0||height<=0) {
			throw new IllegalArgumentException("Width and Height both have to be positive");
		}
		for(int i=0; i<width;i++) { //checking if the values of the array are not null
			if(pixel_array[i]==null) { //put it up here and it worked
				throw new IllegalArgumentException("Row is null");
			}
			if(pixel_array[i].length!=height) { //checking if even array across the picture
				throw new IllegalArgumentException("Not an even array");
			}
			for(int j=0; j<height; j++) {
				//count++;
				if(pixel_array[i][j] == null){
					throw new IllegalArgumentException("Value in array is null");
				}
			}
		}
		 picture = pixel_array.clone();
	}
	
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if(width<=0||height<=0) {
			throw new IllegalArgumentException("Width and Height both have to be positive");
		} 
		 this.width = width;
		 this.height = height;
		 picture = new Pixel[width][height];
		 for(int i=0; i<width;i++) { //setting all values of picture to be pixel
			 for(int j=0; j<height; j++) {
				 picture[i][j] = initial_value;
			 }
		 }
	}
	
	public ImmutablePixelArrayPicture(int width, int height) {
		if(width<=0||height<=0) {
			throw new IllegalArgumentException("Width and Height both have to be positive");
		}  
		this.width = width; //chain it to second constructor but simply calling a new graypixel smh
		 this.height = height;
		 picture = new Pixel[width][height];
		 for(int i=0; i<width;i++) { //setting all values of picture to be pixel
			 for(int j=0; j<height; j++) {
				 picture[i][j] = new GrayPixel(.5);
			 }
		 }
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Pixel getPixel(int x, int y) {
		if(x>(width-1)||y>(height-1)) {
			throw new IllegalArgumentException("Value is out of range");
		}
		return picture[x][y]; //arrays start at the top left in Java, so we're good
	}
	
	public Picture paint(int x, int y, Pixel p) { //for some reason, this paints the original array
		Pixel[][] create = this.picture.clone();
		Pixel[][] created = create.clone();
		//set picture as new picture, return new picture but permanently set because mutable
		Picture createdd = new MutablePixelArrayPicture(created);
		createdd=createdd.paint(x, y, p);
		return createdd; //return a created mutable picture
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		Pixel[][] created = picture.clone();
		//set picture as new picture, return new picture but permanently set because mutable
		Picture createdd = new MutablePixelArrayPicture(created);
		createdd=createdd.paint(x, y, p, factor);
		return createdd; //how do I return a picture?
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		//for loop with bx-ax
		Pixel[][] created = picture.clone();
		Picture createdd = new MutablePixelArrayPicture(created);
		createdd.paint(ax, ay, bx, by, p);
		return createdd;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		//for loop with bx-ax
		Pixel[][] created = picture.clone();
		Picture createdd = new MutablePixelArrayPicture(created);
		createdd.paint(ax, ay, bx, by, p, factor);
		return createdd;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		//for loop with bx-ax
		Pixel[][] created = picture.clone();
		Picture createdd = new MutablePixelArrayPicture(created);
		createdd.paint(cx, cy, radius, p);
		return createdd;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		//for loop with bx-ax
		Pixel[][] created = picture.clone();
		Picture createdd = new MutablePixelArrayPicture(created);
		createdd.paint(cx, cy, radius, p, factor);
		return createdd;
	}

}
