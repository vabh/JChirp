package twitterObjects;

public class Size {
	public int h;
	public int w;
	
	/*
	 * Resizing method used to obtain this size. A value of fit 
	 * means that the media was resized to fit one dimension, 
	 * keeping its native aspect ratio. A value of crop means 
	 * that the media was cropped in order to fit a specific resolution.
	 */
	public String resize;
}
