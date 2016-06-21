package se.alphadev.emotion.core;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Component;

import se.alphadev.emotion.api.Challenge;
import se.alphadev.emotion.api.FaceRectangle;

@Component
public class ImageDecoratorClient {

	public BufferedImage decorate(FaceRectangle winner, BufferedImage image, Challenge challenge) {

		Graphics2D g = image.createGraphics();
		
		g.setStroke(new BasicStroke( 5 ));
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Font font = new Font("Serif", Font.BOLD, 50);
		g.setFont(font);
		g.drawString( challenge.toString() , winner.left - 50, winner.top -50);
		
		Shape theCircle = new Ellipse2D.Double(winner.left - 30, winner.top - 30, winner.width +60, winner.height+60);
		g.draw(theCircle);
		
		g.dispose();
		
		return image;
	}

}
