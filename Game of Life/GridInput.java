import javax.swing.JOptionPane;
public class GridInput {
	//creates 2 integers that will be used to process the users input
	static int GridSizeW = 0;
	static int GridSizeH = 0;
	//Used to input the grid width
	public void UserAssignWidth(){
		//String obtained from user input
		String a = JOptionPane.showInputDialog("Enter grid height between 0 and 500:");
		//string converted to integer
		GridSizeW = Integer.parseInt(a);
		//if the value is greater than 500 or less than 0 the user inputs again
		if (GridSizeW>500 || GridSizeW<0){
			//Input called again until the input is correct
			UserAssignWidth();
			}
				
	}
	//used to input the grid height
	public void UserAssignHeight(){
		String a = JOptionPane.showInputDialog("Enter grid width between 0 and 500:");
		GridSizeH = Integer.parseInt(a);
		//if the value is greater than 500 or less than 0 the user inputs again
		if (GridSizeH>500 || GridSizeH<0){
			//Input called again until the input is correct
			UserAssignHeight();
			}
	}
}
