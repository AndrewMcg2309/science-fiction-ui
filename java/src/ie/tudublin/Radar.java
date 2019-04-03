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

    int i = 0;

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
        ui.strokeWeight(2);
        //ui.stroke(0, 255, 255);
        ui.noFill();
        ui.ellipse(x, y, diameter, diameter);
        ui.fill(255);
        ui.stroke(255);
        ui.noFill();
        ui.ellipse(x, y, 20, 20);
        ui.fill(255);
        // Static field
        float x1 = x + (float)Math.sin(theta) * radius;
        float y1 = y - (float)Math.cos(theta) * radius;
        ui.line(x, y, x1, y1);

    }

    public void update()
    {
        theta += random(0.05f, 0.15f);
    }

}