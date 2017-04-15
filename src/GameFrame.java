import java.awt.Dimension;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents the game
 */

	public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Board gameBoard;
	//Constructor
	public GameFrame() throws IOException{
		super("The Pac-Man Game");
		createBoard();
	}

	/**
	 * This class crates the board of the game 
	 * @throws IOException 
	 */
	public void createBoard() throws IOException
	{
		JPanel container =new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameBoard = new Board(this);
		gameBoard.setAlignmentX(LEFT_ALIGNMENT);
		container.add(gameBoard);
		container.setSize(450, 416);
		this.add(container,0);
		this.setSize(new Dimension(440, 480));
		this.setVisible(true);
		
	}


	public static void main(String args[]) throws Throwable {
				new GameFrame();
	}

}
