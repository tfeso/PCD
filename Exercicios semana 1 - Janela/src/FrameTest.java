import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameTest {
	private JFrame frame;

	public FrameTest() {
		frame = new JFrame("FrameTest");
		
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());
		
		addFrameContent();
		
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();
	}

	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}

	private void addFrameContent() {
		
		
		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
		frame.setLayout(new GridLayout(1,0));

		JLabel label = new JLabel("Nome: ");
		frame.add(label);

		JTextField text = new JTextField("Tiago");
		frame.add(text);
		
		JCheckBox check = new JCheckBox("check");
		frame.add(check);
		
		JButton button = new JButton("button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, check.isSelected() ? "checked" : "not checked");
			}
		});
		frame.add(button);	
	}

	public static void main(String[] args) {
		FrameTest window = new FrameTest();
		window.open();
	}
}