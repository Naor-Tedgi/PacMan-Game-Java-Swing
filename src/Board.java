import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class represents the board of the game which manage all the items on the board
 */
public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private Pacman pacman;
	GameFrame gameFrame;
	private Ghost[] ghostSet;
	private JPanel background;
	private JPanel itemsBoard;
	private JLayeredPane multiBoard;
	private int mapHeight;
	private int mapWidth;
	private double squareHeight;
	private double squareWidth;
	int score;
	JPanel p ;
	JLabel lab1;
	int lives;
	final int W=1; // Wall.
	final int F=2; // Crossroads with food 
	final int E=3; // Empty crossroads
	private int board[][] = {
			//-----------------------X-----------------------------//
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
			{W,W,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,W,W},
			{E,E,E,E,E,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
			{F,F,F,F,F,F,F,F,F,F,W,E,E,E,E,E,E,W,F,F,F,F,F,F,F,F,F,F},
			{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,W},
			{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
			{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
			{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
			{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}
	};

	//Constructor
	public Board(GameFrame thisGame) throws IOException{
		gameFrame=thisGame;
		ghostSet =new Ghost[4];
		score=0;
		lives=3;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		mapHeight=416;
		mapWidth=429;
		this.setBackground(Color.black);
		squareHeight=mapHeight/31;
		squareWidth=mapWidth/28;
		// Creates and set-up the layered pane.
		multiBoard = new JLayeredPane();
		multiBoard.setPreferredSize(new Dimension(mapHeight, mapWidth));
		// Creates the background.
		JPanel background = createBackground();
		background.setBounds(0, 0, mapWidth, mapHeight);
		multiBoard.add(background, new Integer(0));
		// Creates the items board - Placing food in proper places
		CreateItemBoard();
		itemsBoard.setOpaque(false);
		itemsBoard.setSize(mapWidth, mapHeight);
		itemsBoard.setBackground(null);
		multiBoard.add(itemsBoard,new Integer (1));
		// Places Pacman on the board.
		pacman = placePacman();
		pacman.setOpaque(false);
		pacman.setSize(mapWidth, mapHeight);
		multiBoard.add(pacman,new Integer(3));
		pacman.setFocusable(true);
		pacman.setBoard(this);

		
		
		CreateStatusBar();
		add(p);
		
		ghostSet[0]=new RedGhost(12*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostSet[1]=new BlueGhost(13*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostSet[2]=new PinkGhost(14*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostSet[3]=new OrangeGhost(15*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		
		
		
		for(int i = 0; i<4;i++){
			ghostSet[i].setOpaque(false);
			ghostSet[i].setSize(mapWidth, mapHeight);
			multiBoard.add(ghostSet[i],new Integer(4));
			ghostSet[i].setBoard(this);
		}
		
	
				
		multiBoard.setVisible(true);
		
		this.add(multiBoard);
		

		

	}

	/*
	 * Create The StatusBar
	 * Score Counter
	 * and Lives Figure
	 */
	private void CreateStatusBar(){
		
		try {
		    	lab1 = new JLabel("User Name", JLabel.LEFT);
		     lab1.setBackground(Color.BLUE);
			 lab1.setText("Your Score is : "+score);
			 Font f = new Font("Broadway", Font.BOLD, 18);
		     
			 lab1.setFont(f);

			p = new JPanel(){
				private static final long serialVersionUID = 1L;
				private Image pac = ImageIO.read(getClass().getClassLoader().getResource("img/"  +"Heart.png"));
				public void paint (Graphics g){
					super.paint(g);
						for(int i = 0;i<lives;i++)
							g.drawImage(pac, 28*i, 1, null);
					
				}
				
			};
			
			 p.setLayout(new FlowLayout()); 
			 p.add(lab1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		
	}

	/**
	 * This function draws the food on the board
	 */
	private void CreateItemBoard(){
		try {
			itemsBoard = new JPanel(){
				private static final long serialVersionUID = 1L;
				private Image food =  ImageIO.read(getClass().getClassLoader().getResource("img/"+"NormalPoint.png"));
				public void paint (Graphics g){
					super.paint(g);
					for (int i=0; i<board.length; i++)
						for (int j=0; j<board[i].length; j++) {
							if(board[i][j]==F)
								g.drawImage(food, (int)(j*(squareWidth)+squareWidth/2), (int)(i*(squareHeight)+squareHeight/2), null);
						}
					
				}
				
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function draws the background of the board
	 * @return JPanel = the background
	 */
	private JPanel createBackground(){
		try {
			background = new JPanel(){
				private static final long serialVersionUID = 1L;
				private Image map = ImageIO.read(getClass().getClassLoader().getResource("img/"+"Map.bmp"));		
				public void paint( Graphics g ) { 
					super.paint(g);
					g.drawImage(map, 0, 0, null);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		background.setVisible(true);
		background.setBackground(Color.black);
		return background;
	}

	/**
	 * This function return a new pucman with his initial place
	 * @return Pacman = the initial pacman
	 */
	public Pacman placePacman(){
		return new Pacman(13*squareWidth+squareWidth/2,22.5*(squareHeight)+squareHeight/2);
	}

	public Ghost placeGhost(int i){
		return new Ghost(12*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
	}
	
	public int getMapWidth(){
		return mapWidth;
	}

	public int getMapHeight(){
		return mapHeight;
	}

	public String toString(){
		String s="";
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++)
				s=s+","+board[i][j];
			s=s+"\n";
		}
		return s;
	}

	public void addItemToMultiBoard(Component comp , Integer num){
		multiBoard.add(comp,num);
	}

	public boolean isClear(int i, int j) {
			return !(board[i][j]==1);
	}

	public void eat(int i, int j) {
		if(board[i][j]==2){
		   board[i][j]=3;
		   score++;
		   if(score>1)
			   lab1.setText("Your Score is : " +score);
		}
		
		if (score==300){
			winGameEvent();
		}
	}

	private void winGameEvent() {
		stopAllCharacters();
		
		int result = JOptionPane.showConfirmDialog(this, "Start a new game?", "Way To Go! You Wone The Game", JOptionPane.YES_NO_OPTION);
		
		switch (result){
		case (0):
			try {
				this.removeAll();
				gameFrame.createBoard();				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			break;
			case (1):
			System.exit(0);
			break;
		}
	}

	public void restart() {
		lives--;
	
		p.getRootPane().validate();
		p.getRootPane().repaint();
		pacman.startingPoint(13*squareWidth+squareWidth/2,22.5*(squareHeight)+squareHeight/2);
		for(int i=0; i <4;i++){
			ghostSet[i].startingPoint((12+i)*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
			ghostSet[i].rounds=0;
			ghostSet[i].startProcess=false;
		}
		
		if (lives==0){
			endGameEvent();
		}
		
	}

	private void endGameEvent() {
		
		stopAllCharacters();
		
		int result = JOptionPane.showConfirmDialog(this, "Start a new game?", "Game Over my friend", JOptionPane.YES_NO_OPTION);
		
		
		
		
		switch (result){
		case (0):
			try {
				this.removeAll();
				gameFrame.createBoard();				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			break;
			case (1):
			System.exit(0);
			break;
		}
			
		
	}

	private void stopAllCharacters() {

		pacman.stopTimer();
		for(int i =0;i<4;i++){
			ghostSet[i].stopTimer();
		}
		
		
	}

	

	
	
	

}
