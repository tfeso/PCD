import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class Visualizador {
	
	private JFrame frame;
	private File[] files;
	private int atualPosition; 
	
	public Visualizador() {
		atualPosition = 0;
		frame = new JFrame("Photos");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loadImages();
		addFrameContent();
	}

	private void addFrameContent() {
		
		JButton btnLeft = new JButton("<<<");
		JButton btnRight = new JButton(">>>");
		JButton btnReload = new JButton("Reload");		
		JLabel lblTitle = new JLabel("Title");
		JLabel lblImage = new JLabel();

		JPanel painel = new JPanel();	
		painel.add(lblImage);	
		JScrollPane scroll = new JScrollPane(painel);
		
		if(files.length > 0) {
			lblImage.setIcon(new ImageIcon(files[0].getName()));
			lblTitle.setText(files[0].getName());
		}else {
			lblImage.setText("Não existe imagens na pasta!");
		}
		
		btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(atualPosition == 0) {
					//lblImage.setText("Início das fotos!");
					JOptionPane.showMessageDialog(frame, "Início das Fotos!");
				}else {
					atualPosition--;
					lblImage.setIcon(new ImageIcon(files[atualPosition].getName()));
					lblTitle.setText(files[atualPosition].getName());
				}
			}
		});
		
		btnReload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadImages();
				lblImage.setIcon(new ImageIcon(files[0].getName()));
				lblTitle.setText(files[0].getName());
				atualPosition = 0;
			}
		});
		
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(atualPosition == files.length - 1) {
					//lblImage.setText("Fim das fotos!");
				JOptionPane.showMessageDialog(frame, "Fim das fotos!");
				}else {
					atualPosition++;
					lblImage.setIcon(new ImageIcon(files[atualPosition].getName()));
					lblTitle.setText(files[atualPosition].getName());
				}
				
			}
		});
		
		frame.add(scroll);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);		
		frame.add(btnLeft, BorderLayout.WEST);
		frame.add(btnRight, BorderLayout.EAST);
		//frame.add(lblImage);
		frame.add(btnReload, BorderLayout.SOUTH);
		frame.add(lblTitle, BorderLayout.NORTH);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
	}
	
	public void open() {
		frame.setVisible(true);
	}
	
	private void loadImages() {

		String path = System.getProperty("user.dir"); // obtem o diretorio de execucao (raiz do projeto Eclipse)
		
		files = new File(path).listFiles(new FileFilter() {
		     public boolean accept(File f) {
				  
		          // se retornar verdadeiro, f será incluido
		    	 if(f.getName().endsWith(".jpg")) {
		    		 return true;
		    	 }
				return false;
		     }
		});
	}
	
	public static void main(String[] args) {
		
		Visualizador window = new Visualizador();
		window.open();
		
	}

}
