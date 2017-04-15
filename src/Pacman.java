import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * This class represents the Pacman in the game

 */

public class Pacman extends Character implements KeyListener  {
	private static final long serialVersionUID = 1L;

	
	//Constructorre
	public Pacman(double startingPointX, double startingPointY){
		super(startingPointX,startingPointY);
		setHeroLoc(startingPointX,startingPointY);
		this.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus(true);
		
		timer = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == timer)
					move();
			}
		});

		timer.start();
		
	}


	public void move(){
			super.move();
			setHeroLoc(this.x,this.y);
			repaint();

	}
	/**
	 * This function sets the creature direction to be up 
	 */	
	public void setMoveUp(){
		deltaX=0; deltaY=-1;
	}
	/**
	 * This function sets the creature direction to be down 
	 */	
	public void setMoveDown(){
		deltaX=0; deltaY=1;
	}
	/**
	 * This function sets the creature direction to be right 
	 */	
	public void setMoveRight(){
		deltaX=1; deltaY=0;
	}
	/**
	 * This function sets the creature direction to be left 
	 */	
	public void setMoveLeft(){
		deltaX=-1; deltaY=0;
	}

	/**
	 * @return x = x index on the board
	 */	
	public double getXIndex(){
		return this.x;
	}
	
	/**
	 * @return y = y index on the board
	 */	
	public double getYIndex(){
		return this.y;
	}
	
	/**
	 * @param x = x index on the board
	 */	
	public void setXindex(double x){
		this.x=x;
	}
	
	/**
	 * @param y = y index on the board
	 */	
	public void setYindex(double y){
		this.y=y;
	}
	
	/**
	 * initial the creature direction
	 */	
	public void zeroDeltaXY(){
		this.deltaX=0;
		this.deltaY=0;
	}

	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT){
			image = leftIcone();
			setMoveLeft();
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT){
			image = rightIcone();
			setMoveRight();
		}
		if (e.getKeyCode()==KeyEvent.VK_UP){
			image = upIcone();
			setMoveUp();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN){
			image = downIcone();
			setMoveDown();
		}
	}

	public ImageIcon leftIcone() {
		return new ImageIcon(getClass().getClassLoader().getResource("img/"  +"PacmanLeft.png"));
	}
	public ImageIcon rightIcone() {
		return new ImageIcon(getClass().getClassLoader().getResource("img/" +"PacmanRight.png"));
	}
	public ImageIcon upIcone() {
		return new ImageIcon(getClass().getClassLoader().getResource("img/" +"PacmanUp.png"));
	}
	public ImageIcon downIcone() {
		return new  ImageIcon(getClass().getClassLoader().getResource("img/" +"PacmanDown.png"));
	}


	public void startingPoint(double d, double e) {
		image = leftIcone();
		super.startingPoint(d, e);
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
