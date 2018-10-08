package a3;

public class MutablePixelArrayPicture implements Picture{
	private int width;
	private int height;
	final Pixel[][] picture;

	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
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
		picture = pixel_array.clone(); //need to clone for an array, also I need a picture?
	}

	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		//check if what you are initializing is right
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

	public MutablePixelArrayPicture(int width, int height) {
		if(width<=0||height<=0) {
			throw new IllegalArgumentException("Width and Height both have to be positive");
		} 
		this.width = width;
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

	// getPixel(x, y) retrieves the pixel at position (x,y) in the
	// picture. The coordinate (0,0) is the upper left
	// corner of the picture. The coordinate (getWidth()-1, getHeight()-1)
	// is the lower right of the picture. An IllegalArgumentException
	// is thrown if x or y are not in range.

	public Pixel getPixel(int x, int y) {
		if(x>(width-1)||y>(height-1)) {
			throw new IllegalArgumentException("Value is out of range");
		}
		return picture[x][y]; //arrays start at the top left in Java, so we're good
	}


	// MAYBE WE NEED RUNTIME EXCEPTIONS FOR THESE, CAN ADD THESE LATER
	public Picture paint(int x, int y, Pixel p) {
		if(x<0||y<0) {
			throw new IllegalArgumentException("One of x or y is too small");
		}
		if(x>(width-1)||y>(height-1)) {
			throw new IllegalArgumentException("One of x or y is too large-goes past array");
		}

		this.picture[x][y] = p;
		//set picture as new picture, return new picture but permanently set because mutable
		return this; //returns itself
	}

	public Picture paint(int x, int y, Pixel p, double factor) {
		if(x<0||y<0) {
			throw new IllegalArgumentException("One of x or y is too small");
		}
		if(x>(width-1)||y>(height-1)) {
			throw new IllegalArgumentException("One of x or y is too large-goes past array");
		}
		if(factor<0||factor>1) {
			throw new IllegalArgumentException("Factor must be between 0 and 1");
		}
		picture[x][y] = picture[x][y].blend(p, factor); //blends with itself
		return this;
	}

	// paint(int ax, int ay, int bx, int by, Pixel p) paints the
	// rectangular region defined by the positions (ax, ay) and
	// (bx, by) with the specified pixel value. The second form
	// should blend with the specified factor as previously described.

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		//for loop with bx-ax
		/*if(ax<0||ay<0) { //less than zero because zero is the start of the array
			throw new IllegalArgumentException("Out of the array");
		}
		if(ax>(width-1)||ay>(height-1)) {
			throw new IllegalArgumentException("One of x or y is too large-goes past array");
		}*/
		
		if(ax<0) { //so we don't get out of bounds exception
			ax = 0;
		}
		if(ay<0) {
			ay = 0;
		}
		for(int i=ax; i<=bx; i++) {//iterates through x values
			for(int j=ay; j<=by; j++) { //need less than or equal to because it paints up to that point
				picture[i][j] = p;
			}
		}
		return this;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) { //maybe do the polymophism where you call more general method
		if(ax<0||ay<0) {
			throw new IllegalArgumentException("Out of the array");
		}
		if(bx>(width-1)||by>(height-1)) {
			throw new IllegalArgumentException("One of x or y is too large-goes past array");
		}
		if(factor<0||factor>1) {
			throw new IllegalArgumentException("Factor must be between 0 and 1");
		}
		
		for(int i=ax; i<=bx; i++) {//iterates through x values
			for(int j=by; j<=by; j++) {
				picture[i][j] = picture[i][j].blend(p, factor);
			}
		}
		return this;
	}

	// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
	// picture that are within radius distance of the coordinate (cx, cy) to the
	// Pixel value p.  Only positive values of radius should be allowed. Any
	// value of cx and cy should be allowed (even if negative or otherwise
	// outside of the boundaries of the frame). 

	// Calculate the distance of a particular (x,y) position to (cx,cy) with
	// the expression: Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy))	

	// The second form with factor, blends as previously described.

	public Picture paint(int cx, int cy, double radius, Pixel p) {
		/*if(cx<0||cy<0) {
			throw new IllegalArgumentException("Coordinates are outside of array");
		}
		if(cx>(width-1)||(cy>height-1)) {
			throw new IllegalArgumentException("Coordinates are outside of array");
		}
		
		if(radius<=0) { //less than zero or less than and equal to zero?
			throw new IllegalArgumentException("Radius has to be positive"); //is this the correct syntax
		}*/
		for(int i=0; i<width; i++) {//iterates through x values
			for(int j=0; j<height; j++) {
				if(Math.sqrt(((i-cx)*(i-cx))+((j-cy)*(j-cy)))<=radius) {
					picture[i][j] = p;
				}
			}
		}
		return this;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		/*if(cx<0||cy<0) {
			throw new IllegalArgumentException("Coordinates are outside of array");
		}
		if(cx>(width-1)||cy>(height-1)) {
			throw new IllegalArgumentException("Coordinates are outside of array");
		}
		if(factor<0||factor>1) {
			throw new IllegalArgumentException("Factor must be between 0 and 1");
		}
		if(radius<=0) {
			throw new IllegalArgumentException("Radius has to be positive"); //is this the correct syntax
		}*/
		
		
		
		for(int i=0; i<width; i++) {//iterates through x values
			for(int j=0; j<height; j++) {
				if(((i-cx)*(i-cx)+(j-cy)*(j-cy))<=radius) {
					picture[i][j] = picture[i][j].blend(p, factor);
				}
			}
		}
		return this;
	}






}