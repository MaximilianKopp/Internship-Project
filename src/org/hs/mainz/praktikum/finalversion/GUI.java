package org.hs.mainz.praktikum.finalversion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

public class GUI implements ActionListener {

	private final JTextField tfForeName;
	private final JTextField tfPatronym;
	private final JTextField tfLastName;
	private final JTextField tfGregFrom;
	private final JTextField tfGregTo;
	private final JTextField tfJewFrom;
	private final JTextField tfJewTo;
	private final JTextArea taCounter;
	private final JTextArea taNotepad;
	private final JTextArea taText;
	private final JButton jbFilter;
	private final JButton jbTotal;
	private final JButton jbChart;
	private final JRadioButton jrbForeName;
	private final JRadioButton jrbLastName;
	private static JRadioButton jrbGender;
	private final JRadioButton jrbYearGreg;
	private final JRadioButton jrbYearJew;
	private final JFileChooser jfcFileChooser;
	private final JComponent imagePanel;
	private String output;
	private JLabel jlImage;
	private final JMenuItem jmiLoadCSV;
	private final JMenuItem jmiLoadWKT;
	private final JMenuItem jmiExportTotal;
	private final JMenuItem jmiExportFilter;
	private final JMenuItem jmiGearyIndex;
	private final JMenuItem jmiMoranIndex;
	private final JMenuItem jmiClose;

	public GUI() {
		JFrame frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int) tk.getScreenSize().getWidth();
		int ySize = (int) tk.getScreenSize().getHeight();
		frame.setSize(xSize, ySize);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		jbFilter = new JButton("Test");
		jbFilter.setBounds(28, 516, 75, 25);
		jbFilter.setText("Filter");
		jbFilter.addActionListener(this);

		jbTotal = new JButton("Total");
		jbTotal.setBounds(132, 516, 75, 25);
		jbTotal.setText("Total");
		jbTotal.addActionListener(this);

		jbChart = new JButton("Chart");
		jbChart.setBounds(80, 560, 75, 25);
		jbChart.addActionListener(this);

		tfForeName = new JTextField();
		tfForeName.setBounds(27, 185, 76, 21);

		JLabel lblVorname = new JLabel("Forename");
		lblVorname.setBounds(28, 165, 60, 15);

		tfPatronym = new JTextField(10);
		tfPatronym.setBounds(80, 251, 76, 21);

		JLabel lblPatronym = new JLabel("Patronym");
		lblPatronym.setBounds(88, 230, 76, 15);

		tfLastName = new JTextField(10);
		tfLastName.setBounds(135, 185, 76, 21);

		JLabel jlLastName = new JLabel("Lastname");
		jlLastName.setBounds(135, 165, 76, 15);

		tfGregFrom = new JTextField(10);
		tfGregFrom.setBounds(28, 321, 76, 21);

		JLabel jlDateGreg = new JLabel("Date: Gregorian");
		jlDateGreg.setBounds(70, 300, 202, 15);

		tfGregTo = new JTextField(10);
		tfGregTo.setBounds(135, 321, 76, 21);

		JLabel jlDateJew = new JLabel("Date: Jewish");
		jlDateJew.setBounds(70, 363, 202, 15);

		tfJewFrom = new JTextField(10);
		tfJewFrom.setBounds(28, 384, 76, 21);
		tfJewTo = new JTextField(10);
		tfJewTo.setBounds(135, 384, 76, 21);

		JMenuBar jmbMenuBar = new JMenuBar();
		jmiLoadCSV = new JMenuItem("Load CSV-File");
		jmiLoadCSV.addActionListener(this);
		jmiLoadWKT = new JMenuItem("Load WKT-File");
		jmiLoadWKT.addActionListener(this);
		jmiExportTotal = new JMenuItem("Export to GeoJSON (total)");
		jmiExportTotal.addActionListener(this);
		jmiExportFilter = new JMenuItem("Export to GeoJSON (filtered)");
		jmiExportFilter.addActionListener(this);
		jmiClose = new JMenuItem("Close");
		jmiClose.addActionListener(this);

		JMenu jmFile = new JMenu("File");
		jmFile.add(jmiLoadCSV);
		jmFile.add(jmiLoadWKT);
		jmFile.add(jmiExportTotal);
		jmFile.add(jmiExportFilter);
		jmFile.add(jmiClose);

		jmiGearyIndex = new JMenuItem("Autocorrelation (Geary-Index)");
		jmiGearyIndex.addActionListener(this);
		jmiMoranIndex = new JMenuItem("Autocorrelation (Moran-Index)");
		jmiMoranIndex.addActionListener(this);
		JMenuItem jmiStatistics = new JMenuItem("Statistics");
		jmiStatistics.addActionListener(this);
		JMenu jmAnalysis = new JMenu("Analysis");
		jmAnalysis.add(jmiGearyIndex);
		jmAnalysis.add(jmiMoranIndex);
		jmAnalysis.add(jmiStatistics);

		jmbMenuBar.add(jmFile);
		jmbMenuBar.add(jmAnalysis);
		frame.setJMenuBar(jmbMenuBar);
		jfcFileChooser = new JFileChooser();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1038, 25, 500, 500);
		scrollPane_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollPane_1.setBorder(BorderFactory.createBevelBorder(15));

		taText = new JTextArea();
		scrollPane_1.setViewportView(taText);
		taText.setEditable(false);

		JScrollPane jsNotepad = new JScrollPane();
		jsNotepad.setBounds(1038, 630, 500, 200);
		jsNotepad.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		taNotepad = new JTextArea();
		jsNotepad.setViewportView(taNotepad);
		taNotepad.setEditable(true);

		JLabel jlCounter = new JLabel("Counter");
		jlCounter.setBounds(1038, 560, 76, 21);
		taCounter = new JTextArea();
		taCounter.setBounds(1038, 580, 76, 21);
		taCounter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		taCounter.setEditable(false);

		jrbForeName = new JRadioButton("Forename");
		jrbForeName.setBounds(1030, 527, 90, 16);
		jrbLastName = new JRadioButton("Lastname");
		jrbLastName.setBounds(1130, 527, 90, 16);
		jrbGender = new JRadioButton("Gender");
		jrbGender.setBounds(1230, 527, 90, 16);
		jrbGender.addActionListener(this);
		jrbYearGreg = new JRadioButton("Date Greg.");
		jrbYearGreg.setBounds(1330, 527, 90, 16);
		jrbYearJew = new JRadioButton("Date Jew.");
		jrbYearJew.setBounds(1430, 527, 90, 16);
		JRadioButton jrbID = new JRadioButton("ID");
		jrbID.setBounds(1530, 527, 90, 16);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(jrbForeName);
		jrbForeName.setActionCommand("Forename");
		buttonGroup.add(jrbLastName);
		jrbLastName.setActionCommand("Lastname");
		buttonGroup.add(jrbGender);
		jrbGender.setActionCommand("Gender");
		buttonGroup.add(jrbYearGreg);
		jrbYearGreg.setActionCommand("YearGreg");
		buttonGroup.add(jrbYearJew);
		jrbYearJew.setActionCommand("YearJew");
		buttonGroup.add(jrbID);
		jrbID.setActionCommand("ID");

		imagePanel = new JPanel(new BorderLayout(0, 2));
		imagePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imagePanel.setBounds(261, 25, 740, 805);

		frame.getContentPane().add(tfForeName);
		frame.getContentPane().add(lblVorname);
		frame.getContentPane().add(tfPatronym);
		frame.getContentPane().add(lblPatronym);
		frame.getContentPane().add(tfLastName);
		frame.getContentPane().add(jlLastName);
		frame.getContentPane().add(tfGregFrom);
		frame.getContentPane().add(jlDateGreg);
		frame.getContentPane().add(tfGregTo);
		frame.getContentPane().add(jlDateJew);
		frame.getContentPane().add(tfJewFrom);
		frame.getContentPane().add(tfJewTo);
		frame.getContentPane().add(jbFilter);
		frame.getContentPane().add(jbTotal);
		frame.getContentPane().add(jbChart);
		frame.getContentPane().add(scrollPane_1);
		frame.getContentPane().add(jlCounter);
		frame.getContentPane().add(taCounter);
		frame.getContentPane().add(jrbForeName);
		frame.getContentPane().add(jrbLastName);
		frame.getContentPane().add(jrbGender);
		frame.getContentPane().add(jrbYearGreg);
		frame.getContentPane().add(jrbYearJew);
		frame.getContentPane().add(jrbID);
		frame.getContentPane().add(jsNotepad);
		frame.getContentPane().add(imagePanel);
		frame.getContentPane().add(jmbMenuBar);
	}

	public static void initAndShowGUI() {
		new GUI();
	}

	public static void initJavaFX() {
		final JFXPanel fxPanel = new JFXPanel();
		JFrame fxFrame = new JFrame();
		fxFrame.setSize(500, 500);
		fxFrame.setLocation(200, 200);
		fxFrame.getContentPane().add(fxPanel);
		fxFrame.setVisible(true);
		Platform.runLater(() -> initFXThread(fxPanel));
	}

	private static void initFXThread(JFXPanel fxPanel) {
		fxPanel.setScene(chartSelection());
	}

	private static Scene chartSelection() {
		Scene scene = null;
		if (jrbGender.isSelected()) {
			scene = Charts.createPieChart();
		}
		return scene;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			initAndShowGUI();
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Analysis analysis = new Analysis();

		if (e.getSource() == jbFilter) {
			filter();
			try {
				imagePanel.remove(jlImage);
				jlImage = new JLabel(new ImageIcon(PointPlotter.drawImage(addData())));
				imagePanel.add(jlImage);
				imagePanel.repaint();
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "No Coordinates are available");
			}
		}

		else if (e.getSource() == jbChart) {
			initJavaFX();
		}

		else if (e.getSource() == jbTotal) {
			showTotal();
			try {
				imagePanel.remove(jlImage);
				jlImage = new JLabel(new ImageIcon(PointPlotter.drawImage(showTotal())));
				imagePanel.add(jlImage);
				imagePanel.repaint();
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "No Coordinates are available");
			}
		}

		else if (e.getSource() == jmiLoadCSV) {
			CSVReader.loadCSV();
		}

		else if (e.getSource() == jmiLoadWKT) {
			PointPlotter.loadWKT();
			try {
				jlImage = new JLabel(new ImageIcon(PointPlotter.drawImage(addData())));
				imagePanel.add(jlImage);
				imagePanel.repaint();
			} catch (NullPointerException ex) {
				ex.getStackTrace();
			}
		}

		else if (e.getSource() == jmiExportTotal) {
			jfcFileChooser.showSaveDialog(null);
			GeoJsonExport.jsonExport(showTotal(), jfcFileChooser);
		}

		else if (e.getSource() == jmiExportFilter) {
			jfcFileChooser.showSaveDialog(null);
			GeoJsonExport.jsonExport(addData(), jfcFileChooser);
		}

		else if (e.getSource() == jmiGearyIndex) {
			String outputGeary = analysis.autoCorrelGeary() + "";
			taNotepad.setText("The autocorrelation according to Geary's index is: " + "\n" + outputGeary);

		} else if (e.getSource() == jmiMoranIndex) {
			String outputMoran = analysis.autoCorrelMoran() + "";
			taNotepad.setText("The autocorrelation according to the Moran's index is: " + "\n" + outputMoran);
		} else if (e.getSource() == jmiClose) {
			System.exit(JFrame.EXIT_ON_CLOSE);
		}
	}

	private void filter() {
		printOutput();
		taText.setText(printOutput());
		taText.getText();
	}

	private String printOutput() {
		return addData().toString();
	}

	private List<DataStorage> showTotal() {
		List<DataStorage> total = new ArrayList<>();
		int counter = 0;

		for (int i = 0; i < CSVReader.storage().size(); i++) {
			total.add(CSVReader.storage().get(i));
			counter++;
			taCounter.setText(Integer.toString(counter));
		}
		taCounter.getText();
		taText.setText(total.toString());
		taText.getText();

		return total;
	}

	private List<DataStorage> addData() {
		List<DataStorage> filter = new ArrayList<>();

		int counter = 0;
		for (int i = 0; i < CSVReader.storage().size(); i++) {

			if (!(tfForeName.getText().isEmpty() && tfPatronym.getText().isEmpty() && tfLastName.getText().isEmpty())) {
				if (tfForeName.getText().equals(CSVReader.storage().get(i).getForeName())
						&& tfPatronym.getText().equals(CSVReader.storage().get(i).getPatronym())
						&& tfLastName.getText().equals(CSVReader.storage().get(i).getLastName())) {
					filter.add(CSVReader.storage().get(i));
					counter++;
				}
			}
			if ((!(tfForeName.getText().isEmpty())) && tfPatronym.getText().isEmpty() && tfLastName.getText().isEmpty()
					&& tfForeName.getText().equals(CSVReader.storage().get(i).getForeName())) {
				filter.add(CSVReader.storage().get(i));
				counter++;
			} else if ((!(tfPatronym.getText().isEmpty())) && tfForeName.getText().isEmpty()
					&& tfLastName.getText().isEmpty()
					&& tfPatronym.getText().equals(CSVReader.storage().get(i).getPatronym())) {
				filter.add(CSVReader.storage().get(i));
				counter++;
			} else if ((!(tfLastName.getText().isEmpty())) && tfPatronym.getText().isEmpty()
					&& tfForeName.getText().isEmpty()
					&& tfLastName.getText().equals(CSVReader.storage().get(i).getLastName())) {
				filter.add(CSVReader.storage().get(i));
				counter++;
				
			}else if ((!(tfLastName.getText().isEmpty())) && tfPatronym.getText().isEmpty()
						&& tfForeName.getText().isEmpty() && CSVReader.storage().get(i).getLastName().contains("Halevi")
						&& tfLastName.getText().equals("Halevi") || CSVReader.storage().get(i).getLastName().contains("Hakohen") && tfLastName.getText().equals("Hakohen")) {
					filter.add(CSVReader.storage().get(i));
					counter++;
					
			} else if (!(tfGregFrom.getText().isEmpty() && tfGregTo.getText().isEmpty())) {
				if (CSVReader.storage().get(i).getDateGregYear() >= Integer.parseInt(tfGregFrom.getText())
						&& CSVReader.storage().get(i).getDateGregYear() <= Integer.parseInt(tfGregTo.getText())) {
					filter.add(CSVReader.storage().get(i));
					counter++;
				}
			} else if (!(tfJewFrom.getText().isEmpty() && tfJewTo.getText().isEmpty())) {
				if (CSVReader.storage().get(i).getDateJewish() >= Integer.parseInt(tfJewFrom.getText())
						&& CSVReader.storage().get(i).getDateJewish() <= Integer.parseInt(tfJewTo.getText())) {
					filter.add(CSVReader.storage().get(i));
					counter++;
				}
			}

			if (jrbForeName.isSelected()) {
				filter.sort(Comparator.comparing(DataStorage::getForeName));
			} else if (jrbLastName.isSelected()) {
				filter.sort(Comparator.comparing(DataStorage::getLastName));
			} else if (jrbYearGreg.isSelected()) {
				filter.sort(Comparator.comparing(DataStorage::getDateGregYear));
			} else if (jrbYearJew.isSelected()) {
				filter.sort(Comparator.comparing(DataStorage::getDateJewish));
			}
			taCounter.setText(Integer.toString(counter));
		}

		taCounter.getText();
		taText.getText();

		return filter;
	}
}
