package ie.tudublin;

import processing.core.PApplet;

public class MovingCircle
{
    private float x;
    private float dx = 1;
    private float y;
    private float diameter;
    private float radius;
    UI ui;

    public MovingCircle(UI ui, float x, float y, float diameter)
    {
        this.ui = ui;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        radius = diameter / 2;

    }
    
    public void render()
    {
        ui.stroke(255);
        ui.noFill();
        ui.ellipse(x, y, diameter, diameter);
        ui.fill(255);
        // Static field
        ui.textAlign(PApplet.CENTER, PApplet.CENTER);
        ui.text("I am a moving circle", x, y);
    }

    public void update()
    {
        x += dx;
        if ((x > ui.width - radius) || (x < radius))
        {
            dx *= -1;
        }
    }
}
