package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import facebook4j.FacebookException;
import logic.ImagePreviewPanel;
import logic.Publisher;
import logic.Resizer;

import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.DebugGraphics;

@SuppressWarnings("serial")
public class Ui extends JFrame {

	private JPanel contentPane;
	private String toUpload;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ui frame = new Ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ui.class.getResource("/media/icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel borde = new JPanel();
		borde.setToolTipText("Tu Imagen");
		borde.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		borde.setBounds(6, 11, 276, 278);
		contentPane.add(borde);
		borde.setLayout(null);
		
		JLabel image = new JLabel("");
		image.setBounds(10, 11, 255, 255);
		borde.add(image);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(Ui.class.getResource("/media/images.png")));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setSize(new Dimension(90, 43));
		btnBuscar.setBackground(SystemColor.control);
		btnBuscar.setIcon(new ImageIcon(Ui.class.getResource("/media/BtnBuscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**llamamos el metodo que permite cargar la ventana*/
				   JFileChooser chooser = new JFileChooser("D:/Imagenes");
				   ImagePreviewPanel preview = new ImagePreviewPanel();
				   chooser.setAccessory(preview);
				   chooser.addPropertyChangeListener(preview);
				   chooser.showOpenDialog(chooser);
				   if(chooser.getSelectedFile()!= null){
					  Resizer resiser = new Resizer();
					  String strimagen = formater(chooser.getSelectedFile().toString());
					  toUpload = strimagen;
					  image.setIcon(resiser.resizer(strimagen));
				}
				   
			}
		});
		btnBuscar.setBounds(192, 300, 90, 32);
		contentPane.add(btnBuscar);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 343, 278, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane texto = new JTextPane();
		texto.setBackground(SystemColor.info);
		texto.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		texto.setBounds(5, 5, 267, 80);
		panel.add(texto);
		

		JLabel loading = new JLabel("");
		loading.setIcon(new ImageIcon(Ui.class.getResource("/media/loading.gif")));
		loading.setBounds(100, 295, 40, 40);
		contentPane.add(loading);
		loading.setVisible(false);
		
		
		
		JButton btnPost = new JButton("");
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Publisher publisher = new Publisher();
//				System.out.println(publisher.isDone());
//				publisher.shutdown();
//				System.out.println(publisher.isDone());
				
				
				String comentario = texto.getText();
				publisher.setFoto(toUpload);
				publisher.setComentario(comentario);
				Thread thread = new Thread(publisher);
				thread.start();
				String caca = thread.getState().toString();
				System.out.println("The thread has been "+caca);
				
			}
		});
		btnPost.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPost.setIcon(new ImageIcon(Ui.class.getResource("/media/BtnPost.png")));
		btnPost.setBounds(192, 444, 90, 32);
		contentPane.add(btnPost);
		
		JButton programar = new JButton("");
		programar.setIcon(new ImageIcon(Ui.class.getResource("/media/BtnProgramar.png")));
		programar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		programar.setHorizontalTextPosition(SwingConstants.CENTER);
		programar.setBounds(6, 444, 90, 32);
		contentPane.add(programar);
		
		
		
		
	}
	

	
public String  formater(String path) {
	String formated  = path.replaceAll("\\\\", "/");
	
	return formated;
	
}
}
