import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    private Image backIcon;
    public BackGroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }
    @Override
    public void paint(Graphics graphics){
        graphics.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
