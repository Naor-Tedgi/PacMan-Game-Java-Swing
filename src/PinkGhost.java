import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PinkGhost extends Ghost {


	private static final long serialVersionUID = 1L;

	public PinkGhost(double startingPointX, double startingPointY) {
		super(startingPointX, startingPointY);
		image = new ImageIcon(getClass().getClassLoader().getResource("img/" +"PinkRight.png"));
		timer = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == timer)
					move();
			}
		});

		timer.start();
		
		

	}
}
