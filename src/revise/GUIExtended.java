package revise;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUIExtended 
{
	
	
	public GUIExtended()
	{
		extendedGUI();
	}
	
	private void extendedGUI()
	{
		JFrame frame=new JFrame();
		frame.setSize(320, 210);
		frame.setTitle("Find/Replace");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		JPanel top = new JPanel(new GridLayout(3,2));
		frame.add(top, BorderLayout.CENTER); //??
		
		JLabel findReplace = new JLabel("Find/Replace");
		top.add(findReplace);
		
		JTextField ftxt = new JTextField();
		top.add(ftxt);
		
		JLabel replaceWith = new JLabel("Replace with");
		top.add(replaceWith);
		
		JTextField rtxt = new JTextField();
		top.add(rtxt);
		
		JRadioButton fbutton = new JRadioButton("Find");
		JRadioButton rbutton = new JRadioButton("Replace");
		ButtonGroup group = new ButtonGroup();
		group.add(rbutton);
		group.add(fbutton);
		fbutton.setSelected(true);
		top.add(fbutton);
		top.add(rbutton);
		
		JPanel bottom = new JPanel(new FlowLayout());
		frame.add(bottom,BorderLayout.SOUTH);
		JButton ok = new JButton("OK");
		bottom.add(ok);
		ok.setVisible(false);
		JButton cancel = new JButton("Cancel");
		bottom.add(cancel);
		frame.pack();
		
		
		
		
		
		
	
		
		
		
		
		
		frame.setVisible(true);
		
	}
	
	
}
