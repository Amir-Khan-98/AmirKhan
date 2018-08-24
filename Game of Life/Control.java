import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

//inherits functionality from 'JPanel' and 'JActionListener'
public class Control extends JPanel implements ActionListener 
{
	//Creates the initial array
	public static boolean[][] GridArray;
	//Creates the copy array
	public static boolean[][] CopyArray;
	//declares the iteration number
	private static int iteration = 0;
	private static final long serialVersionUID = 1862962349L;
	//converts the users input to integers
	public static int GSizeW=GridInput.GridSizeW;
	public static int GSizeH=GridInput.GridSizeH;
	//declares the buttons that will be used
	private JButton startbutton,pausebutton,restartbutton;
	//Creates the labels
	private JLabel labelonevalue,labelonecaption;
	//The timer iterates the program 
	private Timer iterator;
	
	public Control() 
	{
		//Declares the arrays with the sizes of the users input
		GridArray = new boolean [GSizeH][GSizeW];
		CopyArray = new boolean [GSizeH][GSizeW];
		//calls the CreateArray and CopyArray methods
		CreateArray();
		CopyArray();
	
		//Caption for the start button
		startbutton = new JButton("Start");
		//Centres the text vertically
		startbutton.setVerticalTextPosition(AbstractButton.CENTER);
		//Centres the text horizontally
		startbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		//Short cut key ALT + S
		startbutton.setMnemonic(KeyEvent.VK_S);
		//Create the event/action 'Start' when clicked
		startbutton.setActionCommand("Start");
		
		//Caption for the restart button
		restartbutton = new JButton("Restart");
		//Centres the text vertically
		restartbutton.setVerticalTextPosition(AbstractButton.CENTER);
		//Centres the text horizontally
		restartbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		//Short cut key ALT+R
		restartbutton.setMnemonic(KeyEvent.VK_R);
		//Create the event/action 'Restart' when clicked
		restartbutton.setActionCommand("Restart");




		//Caption for the Pause button
		pausebutton = new JButton("Pause");
		//Centres the text vertically
		pausebutton.setVerticalTextPosition(AbstractButton.CENTER);
		//Centres the text horizontally
		pausebutton.setHorizontalTextPosition(AbstractButton.CENTER);
		//Short cut key ALT+P
		pausebutton.setMnemonic(KeyEvent.VK_P);
		//Create the event/action 'Pause' when clicked
		pausebutton.setActionCommand("Pause");
		//Disables the pause button
		pausebutton.setEnabled(false);

		//Listen for actions from the three buttons
		//The current instance of 'this' class will process the actions for the buttons 
		startbutton.addActionListener(this);
		pausebutton.addActionListener(this);
		restartbutton.addActionListener(this);

		//Displays a tool tip when cursor is hovered over the corresponding button
		startbutton.setToolTipText("Click this button to start logging...");
		pausebutton.setToolTipText("Click this button to pause logging");
		

		//Create the labels
		labelonecaption = new JLabel("Iteration number:");
		labelonevalue = new JLabel("0");
		

		//Adds the buttons and labels to the window
		add(startbutton);
		add(pausebutton);
		add(restartbutton);
		add(labelonecaption);
		add(labelonevalue);

		//Create a timer every second (1000 m/s)
		//The code will be run every time the timer is done
		iterator = new Timer(500, this);
		//Create the event/action 'Iterator' every second
		iterator.setActionCommand("Iterator");
		//Start the timer straight away
		iterator.setInitialDelay(0);





	}//method for copying GridArray to CopyArray
	public static void CopyArray(){
		//iterates through the array
		for(int i=0;i<GSizeH-1;++i)
		{
			for(int j=0;j<GSizeW-1;++j)
			{
				//if the cell in the GridArray is true, then the same cell in the new array will also be true
				if (GridArray[i][j]==true){
					CopyArray[i][j] = true;
				}
				//if the cell in the GridArray is false, then the same cell in the new array will also be false
				else {
					CopyArray[i][j] = false;
				}
			}

		}
	}
	//method for creating the array with random blocks
	public static void CreateArray(){
		//iterates through the array
		for(int i=0;i<GSizeH-1;++i)
		{
			for(int j=0;j<GSizeW-1;++j)
			{
				//obtains a random number between 0 and 9
				Random rand = new Random();
				int r = rand.nextInt(10);
				//if the random number is 1 then the cell will be alive else it will be dead. this ensures more dead cells than alive to begin with.
				if (r==1){
					GridArray[i][j] = true;
				}
				else {
					GridArray[i][j] = false;
				}
			}
		}
	}
	//This is the method for the rules of the game of life
	public static void Rules(){
		//iterates through the array
		for(int i=0;i<GSizeH-1;++i)
		{
			for(int j=0;j<GSizeW-1;++j)
			{
				//calls the neighbour method to obtain the number of neighbours
				int n = neighbour(i,j);
				
				if (CopyArray[i][j]==true){
					//if an alive cell has less than 2 or more than 3 alive cells neighbouring it, the cell will die
					if (n<2 || n>3){
						GridArray[i][j]=false;
					}
					//if an alive cell has 2 or 3 alive cells neighbouring it, the cell will stay alive
					else if (n==2 || n==3){
						GridArray[i][j]=true;
					}
				}
				
				else if (CopyArray[i][j]==false){
					//if a dead cell has 3 neighbours, the cell will come alive
					if (n==3){
						GridArray[i][j]=true;
					}
					//if a dead cell doesn't have 3 cells neighbouring it, it will stay dead
					else{
						GridArray[i][j]=false;
					}
				}
			}
		}
	}
	//method for calculating neighbours
	public static int neighbour (int i , int j){
		int count = 0;
		//gets number for the left cell
		int left = j-1;
		//gets number for the right cell
		int right = j+1;
		//gets number for the above cell
		int above = i-1;
		//gets number for the below cell
		int below = i+1;
		// if the current cell is a corner, the values of the array are adjusted to loop round the grid
		if (left<0){
			left=GSizeW-1;
		}
		if (above<0){
			above=GSizeH-1;
		}
		if (right>GSizeW -1){
			right=0;
		}
		if (below>GSizeH-1){
			below=0;
		}
		//checks if the top left cell is alive and adds to the counter
		if (CopyArray[above][left]==true){
			count++;
		}
		//checks if the left cell is alive and adds to the counter
		if (CopyArray[i][left]==true){
			count++;
		}
		//checks if the above cell is alive and adds to the counter
		if (CopyArray[above][j]==true){
			count++;
		}
		//checks if the below cell is alive and adds to the counter
		if (CopyArray[below][j]==true){
			count++;
		}
		//checks if the right left cell is alive and adds to the counter
		if (CopyArray[i][right]==true){
			count++;
		}
		//checks if the bottom right cell is alive and adds to the counter
		if (CopyArray[below][right]==true){
			count++;
		}
		//checks if the bottom left cell is alive and adds to the counter
		if (CopyArray[below][left]==true){
			count++;
		}
		//checks if the top right cell is alive and adds to the counter
		if (CopyArray[above][right]==true){
			count++;
		}
			// returns the number of neighbours
			return count;
			
	}

	
	//Every time a button is clicked or a timer event occurs this method is run
	public void actionPerformed(ActionEvent e) 
	{
		//Check for the 'Start' event
		if (e.getActionCommand().equals("Start")) 
		{
			//Disable the 'Start' button
			startbutton.setEnabled(false);
			//Enable the 'Stop' button
			pausebutton.setEnabled(true);
			//Start the 'Iterator'
			iterator.start();
		}



		//Check for the 'Pause' event
		if (e.getActionCommand().equals("Pause"))
		{
			//Enable the 'Start' button
			startbutton.setEnabled(true);
			//Disable the 'Pause' button
			pausebutton.setEnabled(false);
			//Stop the 'Iterator'
			iterator.stop();

		}
		if (e.getActionCommand().equals("Restart")) 
		{
			//enable the 'Start' button
			startbutton.setEnabled(true);
			//calls the CreateArray method as it needs a fresh board
			CreateArray();
			//calls the CopyArray method to copy the current array
			CopyArray();
			//resets iteration number
			iteration=0;
			//labels the iteration label to the iteration number
			labelonevalue.setText(Integer.toString(iteration));
			//repaints the board to refresh it
			repaint();
		}
		//Check for the 'Iterator' event
		if (e.getActionCommand().equals("Iterator"))
		{
			//labels the iteration label to the iteration number
			labelonevalue.setText(Integer.toString(iteration));
			//COpies the array
			CopyArray();
			//uses the rules method to change the current array
			Rules();
			//Address to the location you want the file, for logging the iterations, to be in. A new file is created for each iteration
			String link="C://Users//Amir Khan//Documents//GLog//GOLlog"+iteration+".txt";
			//Using try-catch, the FileWrite method is called to write to a file, if a path doesn't exist it will return an error.
			try {
				FileWrite(link);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//refreshes the grid
			repaint();
		}
		//Test for the 'Exit' action
		if (e.getActionCommand().equals("Exit"))
		{
			//Stop the timer
			iterator.stop();
			//Get the parent JFrame of this panel
			JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
			//Hide the Window
			parent.setVisible(false);
			//Get rid of the Window
			parent.dispose();
		}    	
	}
	//method used to write the iterations to a text file
	public static void FileWrite(String File) throws IOException
	{
		//creates a new file writer
		FileWriter writehandle = new FileWriter(File);

		   BufferedWriter bw = new BufferedWriter(writehandle);
		   // Iterates through the array and writes them to a text file 
		   for(int i=0;i<GSizeW;++i)
		   {
			   for(int j=0;j<GSizeH;++j){
				   //create a string called line that will be used for the filewrite
				   String line;
				   //Checks the number of neighbours of each cell
				   int n = neighbour(i,j);
				   //Checks if it is the very first line of the array, if so, the iteration number will be added to the file
				   if (i==0 && j==0){
					   line="Iteration log ="+iteration;
					   //writes to the file
					   bw.write(line);
					   //goes to the next line of the file
					   bw.newLine();
				   }
				   //if the cell if true then it is alive, it is then added to the file with the number of neighbours
				   if (GridArray[i][j]==true){
					   line = "alive ("+n+") neighbours, ";
				   }// if the cell is false then it is dead
				   else{
					   line="dead ("+n+") neighbours, ";
				   }
				   // writes to the file
				   bw.write(line);
				   
			   }
			   //goes to next line in file
			   bw.newLine();
		   }
		   //closes the file writer
		   bw.close();
		   writehandle.close();

	}




	//Override the super class paint method
	@Override
	public void paint(Graphics g) 
	{
		// creates the size of each cell based on the two inputs
		int GSizeConverted = (500 / ((GSizeH+GSizeW)/2));
		
		//Call the super class paint method
		super.paint(g);
		//Create a colour for our rectangle
		Color colour = Color.BLACK;
		//iterates through the array
		for (int i=0;i<GSizeH;i++)
		{
			for (int j=0;j<GSizeW;j++)
			{
				
				
				if (GridArray[i][j]==true && CopyArray[i][j]==true){
					
				}
				//if the cell in the array is true, the colour for the cell will be green
				if (GridArray[i][j]==true)
				{
					colour = Color.GREEN;
				}
				//Otherwise, the colour will be black
				else 
				{
					colour = Color.BLACK;
				}
				//sets the colour of the cell to be written to the correct colour
				g.setColor(colour);
				//Draws the grid, each iteration moves the drawn cell to the right or below the previous cell
				g.fillRect((100) + (GSizeConverted * (i-1)),(100) + (GSizeConverted * (j-1)),GSizeConverted,GSizeConverted);
			}
		}
		
		//increments the iteration
		iteration++;
	}
}