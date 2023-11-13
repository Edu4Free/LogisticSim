package interfaces;

import java.awt.*;

public interface GraphicObjects {

    public Shape getShape();

    public void setAngle(double angle);
    public double getAngle();

    public void fowards();
    public void backwards();
    public void stop();

    public void setSpeed(double speed); // Min = 0.5, Max = 3.0
}
