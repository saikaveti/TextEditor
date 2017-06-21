import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class UserInterface extends JFrame{
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
		
		textArea = new JTextArea(0,0);
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
        
        saveFile = new JMenuItem("Save");
        
        closeWindow = new JMenuItem("Close Editor");
        
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

		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	public class OpenFileActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	public class SaveFileActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
}
