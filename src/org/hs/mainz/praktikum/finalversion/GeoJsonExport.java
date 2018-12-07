package org.hs.mainz.praktikum.finalversion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;

import org.json.*;

public class GeoJsonExport {

	public static void jsonExport(List<DataStorage> addData, JFileChooser jfcChooser) {

		JSONObject featureCollection = new JSONObject();
		featureCollection.put("type", "FeatureCollection");
		JSONArray featureList = new JSONArray();
		for (DataStorage p : addData) {

			// {"geometry": {"type": "Point", "coordinates": [-94.149, 36.33]}
			JSONObject feature = new JSONObject();
			feature.put("type", "Feature");
			JSONObject point = new JSONObject();
			point.put("type", "Point");
			// construct a JSONArray from a string; can also use an array or list
			JSONArray coord = new JSONArray("[" + p.getxCoord() + "," + p.getyCoord() + "]");
			point.put("coordinates", coord);

			feature.put("geometry", point);

			featureList.put(feature);
			featureCollection.put("features", featureList);
			JSONObject properties = new JSONObject();
			properties.put("name", p.getForeName() + " " + p.getPatronym() + " " + p.getLastName());
			properties.put("gender", p.getPatronym());
			properties.put("yearGreg", p.getDateGreg());
			properties.put("yearJew", p.getDateJewish());
			properties.put("month", p.getMonthName());
			properties.put("xCoord", p.getxCoord());
			properties.put("yCoord", p.getyCoord());
			feature.put("properties", properties);
		}

		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(jfcChooser.getSelectedFile()+".json"));
			writer.write(featureCollection.toString());
			writer.flush();
			System.out.println(featureCollection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

