package org.hs.mainz.praktikum.finalversion;

import java.io.IOException;
import java.util.stream.IntStream;

import org.hs.mainz.praktikum.finalversion.CSVReader;

public class Analysis {
	final int size = CSVReader.storage().size();

	final double[] dateGregYear = new double[size];
	final double[] xCoordinate = new double[size];
	final double[] yCoordinate = new double[size];

	public int noEntry() {
		int noEntry = 0;
		for (double v : dateGregYear) {
			if (v == 0) {
				noEntry++;

			}
		}
		return noEntry;
	}

	public double autoCorrelGeary() {

		IntStream.range(0, dateGregYear.length).forEach(i -> {
			dateGregYear[i] = CSVReader.storage().get(i).getDateGregYear();
			xCoordinate[i] = CSVReader.storage().get(i).getxCoord();
			yCoordinate[i] = CSVReader.storage().get(i).getyCoord();
		});

		double degreeSum = 0;
		double degree = 0;
		double cDiff = 0;
		double result = 0;
		for (int i = 0; i < dateGregYear.length; i++) {
			for (int j = 0; j < dateGregYear.length; j++) {
				if (dateGregYear[i] != 0 && dateGregYear[j] != 0) {
					cDiff = Math.pow(dateGregYear[i] - dateGregYear[j], 2);

					degree = 1 / (1 + Math.sqrt(Math.pow(xCoordinate[i] - xCoordinate[j], 2)
							+ Math.pow(yCoordinate[i] - yCoordinate[j], 2)));

					result += degree * cDiff;
					degreeSum += degree;
				}
			}
		}
		try {
			result /= (2 * variance() * degreeSum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public double autoCorrelMoran() {

		IntStream.range(0, dateGregYear.length).forEach(i -> {
			dateGregYear[i] = CSVReader.storage().get(i).getDateGregYear();
			xCoordinate[i] = CSVReader.storage().get(i).getxCoord();
			yCoordinate[i] = CSVReader.storage().get(i).getyCoord();
		});

		double degreeSum = 0;
		double degree = 0;
		double cDiff = 0;
		double result = 0;
		for (int i = 0; i < dateGregYear.length; i++) {
			for (int j = 0; j < dateGregYear.length; j++) {
				if (dateGregYear[i] != 0 && dateGregYear[j] != 0) {
					try {
						cDiff = ((dateGregYear[i] - average()) * (dateGregYear[j] - average()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					degree = 1 / (1 + Math.sqrt(Math.pow(xCoordinate[i] - xCoordinate[j], 2)
							+ Math.pow(yCoordinate[i] - yCoordinate[j], 2)));

					result += degree * cDiff;
					degreeSum += degree;
				}
			}
		}
		try {
			result /= (variance() * degreeSum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public double variance() throws IOException {
		double variance = 0;
		for (double v : dateGregYear) {
			if (v != 0) {

				variance += Math.pow(v - average(), 2);
			}
		}
		return variance /= (dateGregYear.length - noEntry());
	}

	public double average() throws IOException {
		double avgZ = 0;
		for (double v : dateGregYear) {
			if (v != 0) {
				avgZ += v;
			}
		}
		return avgZ /= (dateGregYear.length - noEntry());
	}
}
