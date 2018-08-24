import javax.swing.JFrame;

public class GUI implements Runnable
{
	public void run() 
	{
		//This is how the user inputs the grid size
		GridInput Input = new GridInput();
		//User inputs width and height
		Input.UserAssignWidth();
		Input.UserAssignHeight();
		
	    //Create the Window
        JFrame frame = new JFrame("Game of Life");
        //Closes the window if the cross is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content Pane based on our Control class
        Control newContentPane = new Control();
        //Sets the frame to opaque
        newContentPane.setOpaque(true);
        //Puts the pane into the window
        frame.setContentPane(newContentPane);
        //Sets size of frame to 1000 by 1000.
        frame.setSize(1000,1000);
        //Disables the resizing of the window
        frame.setResizable(false);
        //Displays the frame
        frame.setVisible(true);
    }
}