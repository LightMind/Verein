package plan.ui.lesson;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class RectGlassPane extends JComponent {
	private static final long serialVersionUID = -9094893829268569777L;

	public RectGlassPane() {
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0.3f, 0.35f, 0.3f, 0.3f));
		if (rect != null) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);

		}
	}

	private Rectangle rect = null;

	public void setRectangle(Rectangle r) {
		rect = r;
	}

}
