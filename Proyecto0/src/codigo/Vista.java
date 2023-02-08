package codigo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.awt.event.ActionEvent;

public class Vista {

	private JFrame frame;
	private JTextField txtEntrada;
	private JTextField txtResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 508, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEntrada = new JTextField();
		txtEntrada.setBounds(26, 16, 291, 26);
		frame.getContentPane().add(txtEntrada);
		txtEntrada.setColumns(10);
		
		txtResultado = new JTextField();
		txtResultado.setColumns(10);
		txtResultado.setBounds(26, 58, 451, 247);
		frame.getContentPane().add(txtResultado);
		
		JButton btnNewButton = new JButton("ANALIZAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File archivo = new File("archivo.txt");
				PrintWriter escribir;
				
				try {
					escribir = new PrintWriter(archivo);
					escribir.print(txtEntrada.getText());
					escribir.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					Reader lector = new BufferedReader(new FileReader("archivo.txt"));
					Lexer lexer = new Lexer(lector);
					String resultado = "";
					while(true) {
						Tokens tokens = lexer.yylex();
						
						if(tokens==null) {
							resultado += "FIN";
							txtResultado.setText(resultado);
							return;
						}
						switch(tokens) {
						case ERROR:
							resultado +="El simbolo no definido\n";
							break;
						case Identificador: case Numero: case Reservadas:
							resultado +=lexer.lexeme + ": Es un "+ tokens +"\n";
							break;
						
							default:
								resultado+="Token: " + tokens +"\n";
						
							
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(347, 15, 115, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
