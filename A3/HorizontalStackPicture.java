package a3;

public class HorizontalStackPicture implements Picture{ //could try inheritance? literally have same methods, do this if it doesn't work
	private int height;
	private int width;
	private Pixel[][] picture;
	
	public HorizontalStackPicture(Picture left, Picture right) { //needs to encapsulate both of them
		if(left==null||right==null) {
			throw new IllegalArgumentException("The picture values cannot be null");
		}
		//throw exception of height doesn't match, then you can't add them
		if(left.getHeight()!=right.getHeight()) {
			throw new IllegalArgumentException("Cannot horizontally stack. Heights are not the same");
		}
		height = left.getHeight(); //both are the same if got past if statement for exception
		width = left.getWidth()+right.getWidth();
		//create pixel array of picture, then fill it with the two pictures
		picture = new Pixel[width][height];
		for(int i=0; i<left.getWidth();i++) { //filling the first picture on the left side
			 for(int j=0; j<height; j++) {
				 picture[i][j] = left.getPixel(i, j);
			 }
		 }
		
		for(int i=left.getWidth(); i<(left.getWidth()+right.getWidth()); i++) { //filling the second picture on the right side
			int k = 0; //counter for picture on right side
			for(int j=0;j<height; j++) {
				picture[i][j] = right.getPixel(k, j); //need a -1 in this because it is in the array at place width-1, also got rid of i+, because this is an individual array
			}
			k++;
		}
		
	}
	
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	public Pixel getPixel(int x, int y) {
		if(x>width||y>height) {
			throw new RuntimeException("Value is out of range");
		}
		return picture[x][y];
	}
	
	public Picture paint(int x, int y, Pixel p) {
		if(x<0||y<0) {
			throw new IllegalArgumentException("One of x or y is too small");
		}
		if(x>(width-1)||y>(height-1)) {
			throw new IllegalArgumentException("One of x or y is too large-goes past array");
		}
		picture[x][y] = p;
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
		picture[x][y] = picture[x][y].blend(p, factor); //blends with itself
		return this;
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		//for loop with bx-ax
		if(ax<0||ay<0) {
			throw new IllegalArgumentException("One of x or y is too small");
		}
		if(bx>(width-1)||by>(height-1)) {
			throw new IllegalArgumentException("One of x or y is too large-goes past array");
		}
		for(int i=ax; i<bx; i++) {//iterates through x values
			for(int j=by; i<by; j++) {
				picture[i][j] = p;
			}
		}
		return this;
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) { //maybe do the polymophism where you call more general method
		for(int i=ax; i<bx; i++) {//iterates through x values
			for(int j=by; i<by; j++) {
				picture[i][j] = picture[i][j].blend(p, factor);
			}
		}
		return this;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if(radius<=0) { //less than zero or less than and equal to zero?
			throw new RuntimeException("Radius has to be positive"); //is this the correct syntax
		}
		for(int i=0; i<width; i++) {//iterates through x values
			for(int j=0; i<height; j++) {
				if(((i-cx)*(i-cx)+(j-cy)*(j-cy))<=radius) {
					picture[i][j] = p;
				}
			}
		}
		return this;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(radius<=0) {
			throw new RuntimeException("Radius has to be positive"); //is this the correct syntax
		}
		for(int i=0; i<width; i++) {//iterates through x values
			for(int j=0; i<height; j++) {
				if(((i-cx)*(i-cx)+(j-cy)*(j-cy))<=radius) {
					picture[i][j] = picture[i][j].blend(p, factor);
				}
			}
		}
		return this;
	}

}
