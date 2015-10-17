package revise;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class GUIExample extends JFrame
{
	
	public GUIExample()
	{
		initialUI();
		
	}
	
	private void initialUI()
	{
		setTitle("My First GUI");
		setSize(640,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTextArea textarea = new JTextArea("This is an editable TextArea...");
		JScrollPane scrollpane = new JScrollPane(textarea);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		textarea.setFont(new Font("Serif",Font.ITALIC,12));
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		//setContentPane(textarea);
		getContentPane().add(scrollpane);
		
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu file = new JMenu("File");
		menubar.add(file);
		file.setMnemonic(KeyEvent.VK_F);
		
		
		ImageIcon newIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/new.png");
		JMenuItem nMenuItem = new JMenuItem("New",newIcon);
		file.add(nMenuItem);
		
		ImageIcon openIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/open.png");
		JMenuItem oMenuItem = new JMenuItem("Open",openIcon);
		file.add(oMenuItem);
		
		ImageIcon saveIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/save.png");
		JMenuItem sMenuItem = new JMenuItem("Save",saveIcon);
		sMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		file.add(sMenuItem);
		
		ImageIcon printIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/print.png");
		JMenuItem prntMenuItem = new JMenuItem("Print",printIcon);
		prntMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		file.add(prntMenuItem);
		
		ImageIcon exitIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/exit.png");
		JMenuItem eMenuItem = new JMenuItem("Exit",exitIcon);
		eMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
		eMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(EXIT_ON_CLOSE);
			}
		});
		file.add(eMenuItem);
		
		
		setJMenuBar(menubar);
		JMenu edit = new JMenu("Edit");
		menubar.add(edit);
		edit.setMnemonic(KeyEvent.VK_E);
		
		ImageIcon cutIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/cut.png");
		JMenuItem ctMenuItem = new JMenuItem("Cut",cutIcon);
		ctMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		ctMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				textarea.cut();
			}
		});
		
		edit.add(ctMenuItem);
		
		ImageIcon copyIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/copy.png");
		JMenuItem cpMenuItem = new JMenuItem("Copy",copyIcon);
		cpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		cpMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				textarea.copy();
			}
		});
		edit.add(cpMenuItem);
		
		
		
		ImageIcon pasteIcon = new ImageIcon("/Studies/Semister2/PRA/Home/GUI/GUI/src/guiexample/paste.png");
		JMenuItem pMenuItem = new JMenuItem("Paste",pasteIcon);
		pMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		
	
		
		pMenuItem.addActionListener(new ActionListener(){
		
			
			public void actionPerformed(ActionEvent e)
			{
				if (textarea.getSelectedText()!=null)
				{
					pMenuItem.setEnabled(true);
					
				}
				else
				{
					pMenuItem.setEnabled(false);
				}
				textarea.paste();
			}
		});
		edit.add(pMenuItem);
		
	
		
	
		
		
		
		
		
		
		
		
		
		
	}
}
