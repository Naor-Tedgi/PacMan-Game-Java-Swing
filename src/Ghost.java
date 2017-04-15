import java.util.Random;

public class Ghost extends Character {
private static final long serialVersionUID = 1L;
public boolean startProcess;
public Ghost(double startingPointX, double startingPointY) {
		super(startingPointX, startingPointY);
		block=false;
		startProcess=false;
		}
	
    public void move() {
    	
	RandomMove();	
		if (eatPacMan()){
		this.board.restart();
		startProcess=false;
	}
	
	repaint();

	
	}

	private boolean eatPacMan() {
		return Math.sqrt(Math.pow(this.x-pacmanX, 2)+Math.pow(this.y-pacmanY,2))<15;
	}


	@SuppressWarnings("unused")
	private void setDirection(int Tx ,int Ty ,int Sx , int Sy) {
		//turn left
		if (Tx<Sx){
			deltaX=-1; deltaY=0;
		}
		//turn right
		if (Tx>Sx){
			deltaX=1; deltaY=0;

		}
		//turn down
		if (Ty<Sy){
			deltaX=0; deltaY=1;


		}
		//turn up
		if (Ty>Sy){
			deltaX=0; deltaY=-1;

		}
			
			super.move();

	}
	 
	private void RandomMove() {
		
		
		//get outside the box;
		if (!startProcess){
			deltaX=0; deltaY=-1;
			this.x=x+deltaX;
			this.y=y+deltaY;
			if(this.y==140){
				startProcess=true;
			}
				return;
		}
		
	
		//Random Move Not Working..
		
		if (block){
			block=false;
			Random Rnd1 = new Random();
			Random Rnd2 = new Random();

			int i = Rnd1.nextInt(900);
			int j = Rnd2.nextInt(900);

			i=i*j;
			i=i%4;
			switch(i){
			case 0:
				deltaX=0; deltaY=-1;
				break;
			case 1:
				deltaX=0; deltaY=1;
				  break;
			case 2:
				deltaX=-1; deltaY=0;
				  break;
			case 3:
				deltaX=1; deltaY=0;
				  break;

			}
		}
			
		
	
		
		
		
		
		super.move();
	
	
	}
/*
	public static Node Uniform_Cost_Search(Node Start,Node Goal)
	{
	    GetSucc x = new GetSucc();
	    ArrayList<Node> children = new ArrayList<Node>();
	    Stack<Node> Fringe = new Stack<Node>();
	    Fringe.push(Start);
	    while (Fringe.size() != 0)
	    {
	        Node Parent = Fringe.pop();
	      
	        if ((Parent.StateX == Goal.StateX && children.size()>0 &&
	        	Parent.StateY == Goal.StateY) ||
	        	(airDist(Parent,Goal) && children.size()>0)
	        	)
	        {
	            return (x.GetSussessor_Reverse(children)).get(1);
	        }//end if
	        children = x.GetSussessor(Parent);
	        for (int i = 0; i < children.size(); i++)
	        {
	            Fringe.push(children.get(i));

	        }
	        
	    }//end while 
		return Start ;
	    
	    

	}// end method
*/


	public void stopTimer() {
		super.stopTimer();
	}
	

	
	
	}
	 
	


