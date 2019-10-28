import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JPanel centerPanel;
	private JLabel labelImage;
	private DefaultListModel<String> modelLeft;
	private DefaultListModel<String> modelRight;
	private File[] files;

	public GUI() {
		instanceComponents();
		buildComponents();
		actionButtons();
		loadImages();
	}

	private void instanceComponents() {
		frameMain = new JFrame();
		panelLeft = new JPanel();
		panelRight = new JPanel();
		panelSouth = new JPanel();
		panelTextFields = new JPanel();
		panelButtons = new JPanel();
		centerPanel = new JPanel();
		modelRight = new DefaultListModel<String>();
		modelLeft = new DefaultListModel<String>();
		listLeft = new JList(modelLeft);
		listRight = new JList(modelRight);
		labelImage = new JLabel();
		btnImagesFolder = new JButton("Folder");
		btnSubImage = new JButton("Image");
		btnSearch = new JButton("Search");
		txtImagesFolder = new JTextField();
		txtSubImage = new JTextField();
		txtImagesFolder.setEnabled(false);
		txtSubImage.setEnabled(true);
		viewer = new JScrollPane(centerPanel);
	}

	private void buildLeft() {
		listLeft.setPreferredSize(new Dimension(100, 0));
		panelLeft.setLayout(new GridLayout());
		panelLeft.add(listLeft);
		frameMain.add(panelLeft, BorderLayout.WEST);
	}

	private void buildRight() {
		listRight.setPreferredSize(new Dimension(100,0));
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
		
		centerPanel.add(labelImage);

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
		

		    listRight.addKeyListener(new KeyAdapter() {
		        public void keyPressed(KeyEvent e) {
		            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) { // O down não funciona porque??
		            	labelImage.setIcon(new ImageIcon(listRight.getSelectedValue().toString()));
		            }
		        }
		    });

		    listRight.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 1) {
		            	labelImage.setIcon(new ImageIcon(listRight.getSelectedValue().toString()));
		            }
		        }
		    });

		
	}
	
	public void addWorkerToList(int workerType) {
		switch(workerType) {
			case 0:
				if(!existsWorkerInList("simples"))
					modelLeft.addElement("Procura simples");
				break;
			case 90:
				if(!existsWorkerInList("90"))
					modelLeft.addElement("Procura 90º");
				break;
			case 180:
				if(!existsWorkerInList("180"))
					modelLeft.addElement("Procura 180º");
				break;
			case 270:
				if(!existsWorkerInList("270"))
					modelLeft.addElement("Procura 270º");
				break;
			}
	}
	
	private boolean existsWorkerInList(String rotation) {
		for(int i = 0; i < modelLeft.getSize(); i++) {
			if(modelLeft.get(i).contains(rotation))
				return true;
		}
		return false;
	}
	
	private void loadImages() {

		String path = System.getProperty("user.dir"); // obtem o diretorio de execucao (raiz do projeto Eclipse)
		files = new File(path).listFiles(new FileFilter() {
		     public boolean accept(File f) {
				  
		          // se retornar verdadeiro, f ser� incluido
		    	 if(f.getName().endsWith(".png")) {
		    		 return true;
		    	 }
				return false;
		     }
		});
			for(File f : files) {
				modelRight.addElement(f.getName());
			}
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
	
	public void uploadImages(BufferedImage bf) {
		
		
		
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