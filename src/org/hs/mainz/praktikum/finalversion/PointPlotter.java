package org.hs.mainz.praktikum.finalversion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTFileReader;
import com.vividsolutions.jts.io.WKTReader;

public class PointPlotter {

	private static String path = null;

	public static int size = 750;
	public JComponent userInterface;
	static AffineTransform transform;

	public static String loadWKT() {
		try {
			JFileChooser jfcLoadWKT = new JFileChooser();
			jfcLoadWKT.showOpenDialog(null);
			path = jfcLoadWKT.getSelectedFile().getAbsolutePath();
			System.out.println(path);
		} catch (NullPointerException e) {

		}
		return path;
	}

	public static BufferedImage drawImage(String output, List<DataStorage> filter) {
		List<Point2D> points = getPoints();

		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.BLUE);
		Area area = new Area();
		double r = 0.2;
		for (Point2D tmpPoint : points) {
			Ellipse2D.Double ellipse = new Ellipse2D.Double(tmpPoint.getX(), tmpPoint.getY(), 2 * r, 2 * r);

			Shape shape = ellipse;

			area.add(new Area(shape));
		}

		Rectangle2D rect = area.getBounds2D();

		double width = rect.getWidth();
		double height = rect.getHeight();
		double max = width > height ? width : height;
		double s = size / max;
		AffineTransform scale = AffineTransform.getScaleInstance(s, s);
		double transformX = -rect.getMinX();
		double transformY = -rect.getMinY();
		AffineTransform translate = AffineTransform.getTranslateInstance(transformX, transformY);
		transform = scale;
		transform.concatenate(translate);
		area = new Area(transform.createTransformedShape(area));

		g.draw(area);

		for (int i = 0; i < filter.size(); i++) {
			g.setTransform(transform);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 1));

			if (!(filter.get(i).getForeName().isEmpty())) {
				g.setColor(Color.RED);

				output = filter.get(i).getForeName() + " " + filter.get(i).getPatronym() + " "
						+ filter.get(i).getLastName();
//					String dateOutput = filter.get(i).getDateGreg() + " | "
//							+ filter.get(i).getDateJewish();
//					String genderOutput = filter.get(i).getPatronym();
//					String monthOutput = filter.get(i).getMonthName();

				g.drawString(output, filter.get(i).getxCoord().intValue(), filter.get(i).getyCoord().intValue());

			}
		}

		return image;
	}

	@SuppressWarnings("unchecked")
	public static List<Point2D> getPoints() {
		File file = new File(path);
		WKTReader r = new WKTReader();
		WKTFileReader fr = new WKTFileReader(file, r);

		List<Geometry> geometries = null;

		try {

			geometries = fr.read();

		} catch (IOException | ParseException e) {

// TODO Auto-generated catch block

			e.printStackTrace();
		}

		List<Point2D> pointList = new ArrayList<>();

		for (Geometry g : geometries) {
			Coordinate[] c = g.getGeometryN(0).getCoordinates();
			CoordinateArraySequence s = new CoordinateArraySequence(c);
			for (int point = 0; point < g.getNumGeometries(); point++) {
				double posX = s.getOrdinate(point, 0);
				double posY = s.getOrdinate(point, 1);
				Point2D tmpPoint = new Point2D.Double(posX, posY);
				pointList.add(tmpPoint);
			}

		}
		return pointList;
	}

	public JComponent getUserInterface() {
		return userInterface;
	}
}
