package ie.tudublin;
import processing.core.PApplet;

public class Radar extends PApplet
{
    private float x;
    private float y;
    private float diameter = 50;
    private float radius;
    private float theta;
    UI ui;

    public Radar(UI ui, float x, float y, float diameter)
    {
        this.ui = ui;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        radius = diameter / 2;
        this.theta = 0; 
    }
    
    public void render()
    {
        ui.stroke(255);
        ui.noFill();
        ui.ellipse(x, y, diameter, diameter);
        ui.fill(255);
        // Static field
        float x1 = x + (float)Math.sin(theta) * radius;
        float y1 = y - (float)Math.cos(theta) * radius;
        ui.line(x,y, x1, y1);

    }

    public void update()
    {
        theta += 0.01f;
    }

}