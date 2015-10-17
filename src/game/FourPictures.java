package game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class FourPictures 
{
	public String[] pictures=new String[4];
	public String choices;
	public String answer;
	public String revealed;
	
	public FourPictures(String useranswer,String letters,String[] userpictures)
	{
		choices=letters;
		pictures=Arrays.copyOf(userpictures,userpictures.length);
		answer=useranswer;
		
		//initialising the revealed string
		int i=1;
		revealed = "_";
		while (i<answer.length())
		{
				revealed =revealed+" _";
				i++;
		}
	}
	
	//This method updates the revealed String as the player selects the correct letter.
	public void updateRevealed(char input)
	{
		
		char[] chars=new char[revealed.length()];
		chars = revealed.toCharArray();
		
		for(int i=0;i<answer.length();i++)
		{
			if (answer.substring(i, i+1).charAt(0)==input)
			{
					chars[2*i]=input;
			}	
		}
		revealed=String.copyValueOf(chars);	
	}
	
	
	public static void main(String args[]) throws Exception
	{
		//Using a fixed set of images
		String filenames[] = {"Paris1.jpg", "Paris2.jpg","Paris3.jpg", "Paris4.jpg"};
		
		//Creating two games that run independently of each other
		FourPictures game1 = new FourPictures("paris","pysitrla", filenames);
		FourPictures game2 = new FourPictures("paris","eipfrasncywv", filenames);
				
		//Initialise the two games		
		/*GameWindow playGame1 = new GameWindow(game1);
		playGame1.setVisible(true);*/
		/*GameWindow playGame2 = new GameWindow(game2);
		playGame2.setVisible(true);*/
			
		//Reading data from the given url
		URL levels = new URL("http://www.inf.kcl.ac.uk/staff/andrew/fourpictures/two-levels.txt");
	    BufferedReader in = new BufferedReader(new InputStreamReader(levels.openStream()));

	    int lineCount = 0;
	    String lineInput;
	    ArrayList<String> input = new ArrayList<String>();
	    while((lineInput = in.readLine())!=null)
	    {
	    	input.add(lineInput);
	    	lineCount++;    	
	    }
	    in.close();
	    
	    //Define the number of levels in my game
	    int gameCount = (int) Math.floor(lineCount/6);
	    List <FourPictures> gameLevel1 = new ArrayList<FourPictures>(gameCount);
	    List <FourPictures> gameLevel2 = new ArrayList<FourPictures>(gameCount);
	    gameLevel1.add(game1);
	    gameLevel2.add(game2);
	    //Define game details for each level
	    String gameAnswer;
	    String gameChoices;
	    String gamePictures[] = new String[4];
	    
	    for(int i=0; i<gameLevel1.size()+6;i=i+6)
	    {
    		gameAnswer = input.get(i);
    		gameChoices = input.get(i+1);
    		int x = 0;
	    	for(int j=i+2;j<i+6;j++)
	    	{
	    		BufferedImage gameImage = ImageIO.read(new URL(input.get(j)));
	    		File imageFile = new File("gameImage"+j+".jpg");
	    		ImageIO.write(gameImage,"jpg",imageFile);
	    		gamePictures[x] = "gameImage"+j+".jpg";
	    		x++;
	    	}
	    	gameLevel1.add(new FourPictures(gameAnswer,gameChoices,gamePictures));
	    	gameLevel2.add(new FourPictures(gameAnswer,gameChoices,gamePictures));
	    }
	    GameWindow playGame1 = new GameWindow(gameLevel1);
		playGame1.setVisible(true);
		GameWindow playGame2 = new GameWindow(gameLevel2);
		playGame2.setVisible(true);
	}
}