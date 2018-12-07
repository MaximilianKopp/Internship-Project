package org.hs.mainz.praktikum.finalversion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
public class Charts {
    
   
	
    public static Scene createPieChart() {
        Group root = new Group();
        Scene scene = new Scene(root);
        double sumMale = 0;
		double sumFemale = 0;
		double rest = 0;
		for (int i = 0; i < CSVReader.storage().size(); i++) {
			if (CSVReader.storage().get(i).getPatronym().equals("ben")) {
				sumMale++;
			} else if (CSVReader.storage().get(i).getPatronym().equals("bat")) {
				sumFemale++;
			} else {
				rest++;
			}
		}
		sumMale = sumMale / CSVReader.storage().size() * 100;
		sumFemale = sumFemale / CSVReader.storage().size() * 100;
		rest = rest/ CSVReader.storage().size() * 100;
		

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Male " +  (float)  sumMale +"%", sumMale), new PieChart.Data("Female " + (float) sumFemale+"%", sumFemale),
				new PieChart.Data("No information " + (float) rest+"%", rest));

		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Distribution by gender");
		final Label caption = new Label("");
		caption.setTextFill(Color.BLACK);
		caption.setStyle("-fx-font: 24 arial;");
		root.getChildren().add(chart);
        return scene;
    }
}
 