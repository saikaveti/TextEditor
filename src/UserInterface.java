import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class UserInterface extends JFrame {
	File userFile;
	public Container container;

	public JMenuBar menuBar;

	public JTextArea textArea;

	public JMenu file;
	public JMenu edit;

	public JMenuItem newFile;
	public JMenuItem openFile;
	public JMenuItem saveFile;
	public JMenuItem saveAsFile;

	public JMenuItem closeWindow;
	public JMenuItem cutText;
	public JMenuItem copyText;
	public JMenuItem pasteText;

	public UserInterface() {
		container = getContentPane();

		setTitle("Text Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);

		textArea = new JTextArea(0, 0);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(textArea);

		file = new JMenu("File");

		edit = new JMenu("Edit");

		newFile = new JMenuItem("New");
		newFile.addActionListener(new NewFileActionListener());
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

		openFile = new JMenuItem("Open");
		openFile.addActionListener(new OpenFileActionListener());
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		saveFile = new JMenuItem("Save");
		saveFile.addActionListener(new SaveFileActionListener());
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		closeWindow = new JMenuItem("Close Editor");
		closeWindow.addActionListener(new CloseFileActionListener());

		cutText = new JMenuItem(new DefaultEditorKit.CutAction());
		cutText.setText("Cut");

		copyText = new JMenuItem(new DefaultEditorKit.CopyAction());
		copyText.setText("Copy");

		pasteText = new JMenuItem(new DefaultEditorKit.PasteAction());
		pasteText.setText("Paste");

		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);

		edit.add(closeWindow);
		edit.add(cutText);
		edit.add(copyText);
		edit.add(pasteText);

		menuBar = new JMenuBar();
		menuBar.add(file);
		menuBar.add(edit);

		this.setJMenuBar(menuBar);

	}

	public class NewFileActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
			Frame frame = new Frame("Save Current File?");
			int confirmDialog = JOptionPane.showConfirmDialog(
					frame,
					"Would you like the save the current file?",
					"Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (confirmDialog == JFileChooser.APPROVE_OPTION) {
				try{
					Writer writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(userFile)));
					writer.write(textArea.getText());
					writer.close();
				}
				catch(NullPointerException n){
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a directory");
					int valueNew = fileChooser.showSaveDialog(UserInterface.this);
					if (valueNew == JFileChooser.APPROVE_OPTION) {
						try {
							userFile = fileChooser.getSelectedFile();
							String fileName = userFile.toString();
							if(fileName.contains(".")){
								fileName = fileName.substring(0, fileName.lastIndexOf('.'));
							}
							fileName=fileName+".txt";
							File userFile=new File(fileName);
							Writer writer = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(userFile)));
							writer.write(textArea.getText());
							writer.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			textArea.setText("");
		}
	}

	public class OpenFileActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Frame frame = new Frame("Save Current File?");
			int confirmDialog = JOptionPane.showConfirmDialog(
					frame,
					"Would you like the save the current file?",
					"Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (confirmDialog == JFileChooser.APPROVE_OPTION) {
				try{
					Writer writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(userFile)));
					writer.write(textArea.getText());
					writer.close();
				}
				catch(NullPointerException n){
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a directory");
					int valueNew = fileChooser.showSaveDialog(UserInterface.this);
					if (valueNew == JFileChooser.APPROVE_OPTION) {
						try {
							userFile = fileChooser.getSelectedFile();
							String fileName = userFile.toString();
							if(fileName.contains(".")){
								fileName = fileName.substring(0, fileName.lastIndexOf('.'));
							}
							fileName=fileName+".txt";
							File userFile=new File(fileName);
							Writer writer = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(userFile)));
							writer.write(textArea.getText());
							writer.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			textArea.setText("");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose document");   

			int valueNew = fileChooser.showOpenDialog(UserInterface.this);
			if (valueNew == JFileChooser.APPROVE_OPTION) {
				try {
					userFile = fileChooser.getSelectedFile();
					BufferedReader reader = new BufferedReader(new FileReader(userFile));
					String nextLine="";
					while((nextLine=reader.readLine()) != null){
					textArea.append(nextLine);
					}
				}
				catch(NullPointerException o){
					userFile = fileChooser.getSelectedFile();
					String fileName = userFile.toString();
					if(fileName.contains(".")){
						fileName = fileName.substring(0, fileName.lastIndexOf('.'));
					}
					fileName=fileName+".txt";
					File userFile=new File(fileName);
					try {
						userFile.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

		public class SaveFileActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try{
					Writer writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(userFile)));
					writer.write(textArea.getText());
					writer.close();
				}
				catch(NullPointerException n){
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a directory");   

					int valueNew = fileChooser.showSaveDialog(UserInterface.this);

					if (valueNew == JFileChooser.APPROVE_OPTION) {
						try {
							userFile = fileChooser.getSelectedFile();
							String fileName = userFile.toString();
							if(fileName.contains(".")){
								fileName = fileName.substring(0, fileName.lastIndexOf('.'));
							}
							fileName=fileName+".txt";
							File userFile=new File(fileName);
							userFile.createNewFile();
							Writer writer = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(userFile)));
							writer.write(textArea.getText());
							writer.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

		public class CloseFileActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		}

	}
