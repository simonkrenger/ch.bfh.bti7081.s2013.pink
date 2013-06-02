package ch.bfh.bti7081.s2013.pink.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;

public class IndicatorImageSource implements StreamSource {
	private static final long serialVersionUID = -6148705779537472081L;

	private ByteArrayOutputStream imagebuffer = null;
	private String baseImageName;
	private BufferedImage baseImage;
	private int indicator;

	public IndicatorImageSource(String icon, int indicator) {
		try {
			int begin = icon.lastIndexOf('/') + 1;
			int end = icon.lastIndexOf('.');
			if (end < begin)
				end = icon.length();
			baseImageName = icon.substring(begin, end);
			baseImage = ImageIO.read(getClass().getResourceAsStream(icon));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.indicator = indicator;
	}

	@Override
	public InputStream getStream() {
		BufferedImage image = new BufferedImage(baseImage.getWidth(),
				baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.drawImage(baseImage, null, 0, 0);
		g2d.setColor(new Color(255, 73, 150));
		g2d.fillOval(22, 0, 18, 18);
		g2d.setColor(Color.white);
		g2d.drawOval(22, 0, 18, 18);
		Font f = g2d.getFont();
		f = f.deriveFont(12f);
		g2d.setFont(f);
		g2d.drawString(String.valueOf(indicator), 27, 14);

		try {
			/* Write the image to a buffer. */
			imagebuffer = new ByteArrayOutputStream();
			ImageIO.write(image, "png", imagebuffer);

			/* Return a stream from the buffer. */
			return new ByteArrayInputStream(imagebuffer.toByteArray());
		} catch (IOException e) {
			return null;
		}
	}

	public Resource getResource() {
		return new StreamResource(this, baseImageName + indicator + ".png");
	}
}
