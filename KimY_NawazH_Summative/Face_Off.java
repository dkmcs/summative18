import javax.swing.*;
import java.util.Random; 
import java.lang.Object;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Face_Off extends JFrame implements ActionListener { 
    private JButton btn[][];       // number of buttons - can be changed
    private Container c;
    private JPanel btnPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JButton fillRed = new JButton("New Game");     //can change aaaa   label on action buttons
    private JButton count = new JButton("How to Play");      //can change bbbb   label on action buttons
    private JButton change = new JButton("Change Buttons");
    private JButton clear = new JButton("Exit");          //label on action buttons
//----------------------------------variables
String fold = "img1";
ImageIcon hussain= new ImageIcon(fold+"\\block1.png");
ImageIcon youjin= new ImageIcon(fold+"\\block2.png");
String txt="";
int len = 0;
boolean[][] currentmap;
int cnt = 0;
boolean win = false;

//------------------------------------

	public Face_Off()   {
		super( "Face Off" );
		btn = new JButton[5][5];
		currentmap = createArray(); 
		btnPanel.setLayout(new GridLayout(btn.length,btn[0].length));
		southPanel.setLayout(new GridLayout(1,4));
		c = getContentPane();
		c.setLayout( new BorderLayout() );
        setResizable(false);
		  // create and add buttons
		
		for (int i = 0; i < btn.length; i++ ) {
			for (int j = 0; j < btn[0].length; j++ ) {
				btn[i][j] = new JButton(); //can change c   label on grid buttons
				btn[i][j].setMargin(new Insets(0, 0, 0, 0));
				if(currentmap[i][j])
					btn[i][j].setIcon(hussain);
				else
					btn[i][j].setIcon(youjin);
				btn[i][j].addActionListener( this );
				btnPanel.add(  btn[i][j] );
				btn[i][j].setActionCommand(i+" " +j);
			}  
		}
		newArray();
		
	updateScreen();
	fillRed.addActionListener( this );
	southPanel.add(fillRed);
	count.addActionListener( this );
	southPanel.add(count);
	clear.addActionListener( this );
	southPanel.add(clear);
	change.addActionListener( this );
	southPanel.add(change);
	
	c.add( southPanel, BorderLayout.SOUTH );
	c.add( btnPanel, BorderLayout.CENTER  );
	setSize( 600, 600 );                          //size of the window, can be changed
	setVisible(true);
    }
   
    public void actionPerformed( ActionEvent e ){ 
        
		JButton b = (JButton)e.getSource();     // now b is the button we clicked on
				   
		if (b.getText().equals("New Game")){ // NEW GAME
			currentmap = newArray();
			updateScreen();
		}
		else if (b.getText().equals("How to Play")){ // HOW TO PLAY
		    JOptionPane.showMessageDialog( null, "The mission is to get all of the faces the same! Clicking on a face will change itself and the faces directly above, below, and beside it. Good luck!", "How to Play:", JOptionPane.INFORMATION_MESSAGE );
		}
		else if (b.getText().equals("Change Buttons")){ //CHAGNE BUTTONS
			if(fold.equals("img1"))
				fold = "img2";
			else if(fold.equals("img2"))
				fold = "img3";
			else
				fold = "img1";
		hussain= new ImageIcon(fold+"\\block1.png");
        youjin= new ImageIcon(fold+"\\block2.png");
		updateScreen();
		}
		else if (b.getText().equals("Exit")){ //EXIT
		    System.exit(0);
		}
		else{ //ALL THE OTHER BUTTONS
			txt = b.getActionCommand();
			len = txt.length();
			String last = txt.substring(len-1);
			String first=txt.substring(0,1);  
			int var1 = Integer.parseInt(first);
			int var2 = Integer.parseInt(last);
			for (int i = 0; i < btn.length; i++ ) {
				for (int j = 0; j < btn[0].length; j++ ) {
					if (b.getActionCommand().equals(i+" " +j)){  //if gray - change to red
						if(currentmap[i][j] == currentmap[var1][var2]){
                            swap(i, j);
                            swap(i-1, j);
                            swap(i+1, j);
                            swap(i, j+1);
                            swap(i, j-1);
                            updateScreen();
						}
					}
				}
			}
			//Let win represent boolean to check board
			win = true;
			boolean cur = currentmap[0][0];
			for (int i = 0; i < btn.length; i++ ) {
				for (int j = 0; j < btn[0].length; j++ ) {
					if(cur != currentmap[i][j]){
						win = false;
						break;
					}
				}
			} 
			if(win){
				JOptionPane.showMessageDialog( null, "You Won!!", "Congratulations!!", JOptionPane.INFORMATION_MESSAGE);
				currentmap = newArray();
				updateScreen();
			}
		
		}		
	}// end actionPerformed	
    
	public void updateScreen(){
		for (int i = 0; i < btn.length; i++ ) 
			for (int j = 0; j < btn[0].length; j++ ) 
				if (currentmap[i][j])
					btn[i][j].setIcon(hussain);
				else 
					btn[i][j].setIcon(youjin);
    }
	
	public boolean[][] newArray(){
	boolean[][] arr = new boolean[5][5];
	int rand = 0;
	int rand2 = 0;
	int cnt = 0;
	while(cnt<5){
		for (int i = 0; i < arr.length; i++ ) {
			for (int j = 0; j < arr[0].length; j++ ) {
				rand = (int)(Math.random()*4)+1;
				rand2 = (int)(Math.random()*4)+1;
					if(currentmap[i][j] == currentmap[rand][rand2]){
						swap(i, j);
						swap(i-1, j);
						swap(i+1, j);
						swap(i, j+1);
						swap(i, j-1);
						updateScreen();
					}
			}
		}
	cnt++;
	}
	return currentmap;
	}
    public void swap(int i, int j) {
        try {
            currentmap[i][j] = !currentmap[i][j];
        } catch (IndexOutOfBoundsException e) {
        }
    }
	
	/*public static boolean[][] createArray(){
	boolean[][] arr = new boolean[5][5];
    Random r = new Random();
    for(int i = 0; i < arr.length; i++){
        for(int j = 0; j < arr[i].length; j++){
            arr[i][j] = r.nextBoolean();
			//System.out.print(arr[i][j]+"\t");
		}
    }   
	return arr;
	}*/
	
	public static boolean[][] createArray(){
	boolean[][] arr = new boolean[5][5];
    for(int i = 0; i < arr.length; i++){
        for(int j = 0; j < arr[i].length; j++){
            arr[i][j] = false;
		}
    }   
	return arr;
	}
	
	public static void main( String args[] ) {
		Face_Off app = new Face_Off();

		app.addWindowListener(
			new WindowAdapter() {
				public void windowClosing( WindowEvent e )
            {
               System.exit( 0 );
            }
			}
		);
	}   
}   
