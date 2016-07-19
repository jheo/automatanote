import javax.swing.*;
import java.awt.event.*;

import java.awt.*;


public class TitleWindow extends JPanel{
	private final static long serialVersionUID = 17515561L;
	
	private Image image;
	private boolean exitWindow = false;
		
	public TitleWindow(String imagePath){
		Toolkit t = Toolkit.getDefaultToolkit();
		image = t.getImage(imagePath);
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				exitWindow = true;
			}
		});
		
	}
	
	public boolean getExitCondition() {
		return exitWindow;
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	
}
