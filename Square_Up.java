import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random; 

public class Square_Up extends JFrame implements ActionListener { 
   private JButton btn[][] = new JButton[5][5];       // number of buttons - can be changed

   private Container c;
   private JPanel btnPanel = new JPanel();
   private JPanel southPanel = new JPanel();
   private JButton fillRed = new JButton("New Game");     //can change aaaa   label on action buttons
   private JButton count = new JButton("How to Play");      //can change bbbb   label on action buttons
   private JButton clear = new JButton("Exit");          //label on action buttons
//----------------------------------variables


ImageIcon moon= new ImageIcon("block1.png");
ImageIcon star= new ImageIcon("block2.png");
String txt="";
int var = 0;
int len = 0;
boolean[][] currentmap = createArray(); 
int x;

//------------------------------------

   public Square_Up()   {
      super( "Square Up" );

      btnPanel.setLayout(new GridLayout(btn.length,btn[0].length));
	  southPanel.setLayout(new GridLayout(1,3));

      c = getContentPane();
      c.setLayout( new BorderLayout() );

      // create and add buttons
	int rand2 = (int)(Math.random()*2)+1;
	if(rand2==1){
		for (int i = 0; i < btn.length; i++ ) {
			for (int j = 0; j < btn[0].length; j++ ) {
				btn[i][j] = new JButton(i+" " +j); //can change c   label on grid buttons
				if(currentmap[i][j])
					btn[i][j].setIcon(moon);
				else
					btn[i][j].setIcon(star);
				btn[i][j].addActionListener( this );
				btnPanel.add(  btn[i][j] );
				btn[i][j].setActionCommand(i+" " +j);
			}  
		}
		updateScreen();
	}
	fillRed.addActionListener( this );
	southPanel.add(fillRed);
	count.addActionListener( this );
	southPanel.add(count);
	clear.addActionListener( this );
	southPanel.add(clear);
	  
	c.add( southPanel, BorderLayout.SOUTH );
	c.add( btnPanel, BorderLayout.CENTER  );
	setSize( 600, 600 );                          //size of the window, can be changed
	setVisible(true);
	  //----------------------------------
	  
	  
	  
	  
	  
	  
	  //===-------------------------------
    }
    public void updateScreen(){
		for (int i = 0; i < btn.length; i++ ) 
			for (int j = 0; j < btn[0].length; j++ ) 
				if (currentmap[i][j])
					btn[i][j].setIcon(moon);
				else 
					btn[i][j].setIcon(star);
    }
   
    public void actionPerformed( ActionEvent e ){ 
        
		JButton b = (JButton)e.getSource();     // now b is the button we clicked on
				   
		if (b.getText().equals("New Game")){ // NEW GAME
			updateScreen();
		}
		else if (b.getText().equals("How to Play")){ // HOW TO PLAY
		    JOptionPane.showMessageDialog( null, "The mission is to get all of the lights off! Clicking on a lightbulb will change itself and the bulbs directly above, below, and beside it. Good luck!", "How to Play:", JOptionPane.INFORMATION_MESSAGE );
		}
		else if (b.getText().equals("Exit")){ //EXIT
		    System.exit(0);
		}
		else{ //ALL THE OTHER BUTTONS
			txt = b.getActionCommand();
			System.out.println(txt);
			len = txt.length();
			String last = txt.substring(len-1);
			String first=txt.substring(0,1);  
			int var1 = Integer.parseInt(first);
			int var2 = Integer.parseInt(last);
			JButton up2 = new JButton();
			JButton down2 = new JButton();
			JButton left2 = new JButton();
			JButton right2 = new JButton();
			for (int i = 0; i < btn.length; i++ ) {
				for (int j = 0; j < btn[0].length; j++ ) {
					if(i > 0)
						up2 = new JButton();
				}
			}
			for (int i = 0; i < btn.length; i++ ) {
				for (int j = 0; j < btn[0].length; j++ ) {
					if (b.getText().equals(i+" " +j)){  //if gray - change to red
						if(currentmap[i][j] == currentmap[var1][var2]){
							currentmap[i][j]=!currentmap[i][j];

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
		}		
	}// end actionPerformed	
		//gotta put smth saying if all blocks are one kind of block, then finish the game with a message saying "you won! play again?"
    

    private void swap(int i, int j) {
        currentmap[i][j] = !currentmap[i][j];
    }
	
	public static boolean[][] createArray(){
	boolean[][] arr = new boolean[5][5];
    Random r = new Random();
    for(int i = 0; i < arr.length; i++){
        for(int j = 0; j < arr[i].length; j++){
            arr[i][j] = r.nextBoolean();
			System.out.print(arr[i][j]+"\t");
		}
    }   
	return arr;
	}
	
	public static void main( String args[] ) {
		Square_Up app = new Square_Up();

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
