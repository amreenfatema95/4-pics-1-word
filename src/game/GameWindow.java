package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame
{
	private List <FourPictures> gameList= new ArrayList<FourPictures>();
	private int levelCount=0;
	public GameWindow(List <FourPictures> userGameList) 
	{
		gameList=userGameList;
		setSize(320,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		gameGUI(userGameList.get(levelCount));
	}
	
	public void gameGUI(FourPictures game)
	{
		//Creating a border layout.
				setLayout(new BorderLayout());
				
				//Create a top panel
				JPanel top = new JPanel();
			    add(top, BorderLayout.NORTH);
				
			    //Adding a label to the top panel
			    String toplabel = "Guess: "+game.revealed;
			    JLabel guess = new JLabel(labelFormat(toplabel,"top"));
				top.add(guess);

				//Creating a centre panel containing a 2*2 grid
				JPanel center = new JPanel(new GridLayout(2,2));
				add(center, BorderLayout.CENTER);
				
				//Adding images to the centre grid
				ImageIcon image[] = new ImageIcon[game.pictures.length];
				JLabel cLabel[] = new JLabel[game.pictures.length];
				for(int i=0; i<game.pictures.length;i++)
				{
					image[i] = new ImageIcon(game.pictures[i]);
					cLabel[i] = new JLabel(image[i]);
					center.add(cLabel[i]);
				}
					
				//Calculate the number of columns 
				int numberOfColumns=(int) Math.ceil((game.choices.length()/2));
				if (game.choices.length()%2==0)
				{
					numberOfColumns=(game.choices.length()/2)+1;
				}
				
				//Create a bottom panel 
				JPanel bPanel = new JPanel();
				add(bPanel,BorderLayout.SOUTH);
				
				//Setting a BorderLayout in the bottom panel
				bPanel.setLayout(new BorderLayout());
				
				//Adding a grid of choices to the top half of the bottom panel
				JPanel bottom = new JPanel(new GridLayout(2,numberOfColumns));
				bPanel.add(bottom,BorderLayout.NORTH);
				
				/*Adding a button for the next level at the bottom of the game window 
				and is disabled at all times till the current level is completed*/
				JButton nextLevel = new JButton("next level");
				bPanel.add(nextLevel,BorderLayout.SOUTH);
				nextLevel.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						getContentPane().removeAll();
						repaint();
						
						if (levelCount<gameList.size())
						{
							gameGUI(gameList.get(levelCount));
						}
						else
						{
							ImageIcon finallyCompleted = new ImageIcon("congrats.png");
							JLabel completed = new JLabel(finallyCompleted);
							add(completed);
							revalidate();
						}
					}
			});
				nextLevel.setEnabled(false);
				
				//Add buttons to the bottom panel
				JButton choice[] = new JButton[game.choices.length()+1];
				for(int i=0;i<game.choices.length()+1;i++)
				{
				    if (i!=game.choices.length())
				    {
				    	choice[i] = new JButton(game.choices.substring(i, i+1));
				    }
				    else
				    {
				    	choice[i] = new JButton("?");
				    }
					bottom.add(choice[i]);
				}
				
				//Adding an action listener for each button in the bottom panel
				for(JButton b:choice)
				{
					b.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							char keyPressed=e.getActionCommand().charAt(0);

							if (e.getActionCommand().charAt(0)=='?')
							{
								for (int p=0;p<game.revealed.length();p++)
								{
									if (game.revealed.charAt(p)=='_')
									{
										keyPressed=game.answer.charAt(p/2);
										p=game.revealed.length();
									}
									
								}
							}
							game.updateRevealed(keyPressed);
							
							//Repainting the top label
							String  newtoplabel = "Guessed: "+game.revealed;
							guess.setText(labelFormat(newtoplabel,"top"));
							
							//Makes the selected button invisible
							for(JButton element:choice)
							{
								if (e.getSource()==element)
								{
									element.setVisible(false);
								}
								if (element.getText().charAt(0)==keyPressed)
								{
									element.setVisible(false);
								}
							}
							
							//Updates the bottom panel to indicate level completion
							int counter=0;
							for(int k=0;k<game.revealed.length();k++)
							{
								if(game.revealed.charAt(k)=='_')
								{
									counter++;
								}
							}
							if (counter==0)
							{
								bottom.setVisible(false);
								JPanel finalBottom = new JPanel();
								bPanel.add(finalBottom, BorderLayout.NORTH);
								/*Update the bottom label to indicate level completion and
								enables the button for the next level*/
								String correct = "Correct!";
								JLabel correctLabel = new JLabel(labelFormat(correct,""));
								finalBottom.add(correctLabel);
								finalBottom.setVisible(true);
								levelCount++;
								nextLevel.setEnabled(true);
							}
						}
					});	
				}
				pack();
			}
			
			//This method formats the labels using HTML formatting
			public String labelFormat(String userlabel,String labeltype)
			{
				if(labeltype == "top")
				{
					return "<html><tag><h1><b>"+userlabel+"</h1></b></tag></html>";
				}
				else
				{
					return "<html><tag><font size=72><b>"+userlabel+"</font></b></tag></html>";
				}
			}
			
	}
	
