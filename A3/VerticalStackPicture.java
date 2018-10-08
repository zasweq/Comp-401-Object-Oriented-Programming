package a3;

public class VerticalStackPicture implements Picture{ //could try inheritance? literally have same methods
	private int height;
	private int width;
	private Pixel[][] picture;
	
	public VerticalStackPicture(Picture top, Picture bottom) { //needs to encapsulate both of them
		if(top==null||bottom==null) { //pictures cannot be null
			throw new IllegalArgumentException("The picture values cannot be null");
		}
		//throw exception of width doesn't match, then you can't add them
		if(top.getWidth()!=bottom.getWidth()) {
			throw new IllegalArgumentException("Cannot vertically stack. Widths are not the same");
		}
		height = top.getHeight()+bottom.getHeight(); //both are the same if got past if statement for exception
		width = top.getWidth();
		//create pixel array of picture, then fill it with the two pictures
		picture = new Pixel[width][height];
		for(int i=0; i<width;i++) { //filling the first picture on the top
			 for(int j=0; j<top.getHeight(); j++) {
				 picture[i][j] = top.getPixel(i, j);
			 }
		 }
		
		for(int i=0; i<width; i++) { //filling the second picture on the bottom
			int k = 0;
			for(int j=top.getHeight();j<top.getHeight()+bottom.getHeight(); j++) {
				picture[i][j] = bottom.getPixel(i, k); //again, need a -1 for indexing reasons
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
