import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI {

	private JFrame frameMain;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelSouth;
	private JTextField txtImagesFolder;
	private JTextField txtSubImage;
	private JList listLeft;
	private JList listRight;
	private JButton btnImagesFolder;
	private JButton btnSubImage;
	private JButton btnSearch;
	private JPanel panelTextFields;
	private JPanel panelButtons;
	private JScrollPane viewer;

	public GUI() {
		instanceComponents();
		buildComponents();
		actionButtons();
	}

	private void instanceComponents() {
		frameMain = new JFrame();
		panelLeft = new JPanel();
		panelRight = new JPanel();
		panelSouth = new JPanel();
		panelTextFields = new JPanel();
		panelButtons = new JPanel();
		listLeft = new JList();
		listRight = new JList();
		btnImagesFolder = new JButton("Folder");
		btnSubImage = new JButton("Image");
		btnSearch = new JButton("Search");
		txtImagesFolder = new JTextField();
		txtSubImage = new JTextField();
		txtImagesFolder.setEnabled(false);
		txtSubImage.setEnabled(true);
		viewer = new JScrollPane();
	}

	private void buildLeft() {
		String[] listAux = new String[1];
		listAux[0] = "                          ";
		listLeft.setListData(listAux);
		panelLeft.setLayout(new GridLayout());
		panelLeft.add(listLeft);
		frameMain.add(panelLeft, BorderLayout.WEST);
	}

	private void buildRight() {
		String[] listAux = new String[1];
		listAux[0] = "                          ";
		listRight.setListData(listAux);
		panelRight.setLayout(new GridLayout());
		panelRight.add(listRight);
		frameMain.add(panelRight, BorderLayout.EAST);
	}

	private void buildComponents() {
		frameMain.setLayout(new BorderLayout());
		frameMain.add(viewer);
		frameMain.setTitle("Find Images");
		buildLeft();
		buildRight();

		panelSouth.setLayout(new BorderLayout());
		panelSouth.add(btnSearch, BorderLayout.SOUTH);

		panelTextFields.setLayout(new GridLayout(2,1));
		panelTextFields.add(txtImagesFolder);
		panelTextFields.add(txtSubImage);

		panelButtons.setLayout(new GridLayout(2,1));
		panelButtons.add(btnImagesFolder);
		panelButtons.add(btnSubImage);

		panelSouth.add(panelTextFields);
		panelSouth.add(panelButtons, BorderLayout.EAST);


		frameMain.add(panelSouth, BorderLayout.SOUTH);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setResizable(false);
		frameMain.setSize(700, 500);
		frameMain.setLocationRelativeTo(null);
		frameMain.setVisible(true);
	}

	private void actionButtons() {

		btnImagesFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFolder();
			}
		});

		btnSubImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseSubImage();
			}
		});

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
			}
		});
	}

	private void chooseFolder() {

		JFileChooser jfc = new JFileChooser(".");

		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			txtImagesFolder.setText(selectedFile.getAbsolutePath());
		}
	}
	
	private void chooseSubImage() {
		
		JFileChooser jfc = new JFileChooser(".");

		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			txtSubImage.setText(selectedFile.getAbsolutePath());
		}
	}

	public static void main(String[] args) {


		GUI g = new GUI();
	}


}