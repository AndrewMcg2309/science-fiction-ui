// Info 
//      On Fullscreen - Dims (1535, y) roughly..

package ie.tudublin;

import processing.core.PApplet;

public class UI extends PApplet
{
    Button b;
    MovingCircle mc;
    Radar radar;

    float t, r;
    Sphere sphere = new Sphere(80, 20 * radians(t += (TWO_PI / 360)));


    boolean[] keys = new boolean[1024]; 

    public void keyPressed()
    {
        keys[keyCode] = true;
    }
    
    public void keyReleased()
    {
        keys[keyCode] = true;
    }

    public boolean checkKey(int c)
    {
        return keys[c] || keys [Character.toUpperCase(c)];
    }


    public void settings()
    {
        fullScreen(P3D);
    }

    public void setup()
    {
        b = new Button(this, 50, 50, 100, 50, "I am a button");
        mc = new MovingCircle(this, width / 2, height / 2, 50);
        radar = new Radar(this, width / 4, height / 4, 200);

        // Sphere
        t = 0;
        r = 130;
    }

    public void draw()
    {
        background(0);
        b.render();

        mc.update();
        mc.render();

        radar.update();
        radar.render();

        if (checkKey(LEFT))
        {
            System.out.println("Left arrow key pressed");
        }

        drawGrid();
        drawRing();
        drawSphere();
    }

    public void drawGrid()
    {
        float frame = 20;
        float angleD = 50;
        float fractW = width / 5;
        float fractH = height / 5;

        float radius = 10;
        fill(255);

        stroke(255, 255, 255); //255-0-255 when finished 
        
        // Top Left

        // Top Right
        
            ellipse(fractW * 4 - (angleD / 2), frame + angleD / 2, radius, radius);
        line(fractW * 4 - (angleD / 2), frame + angleD / 2, fractW * 4, frame);
        line(fractW * 4, frame, width - (frame + angleD), frame);
        line(width - (frame + angleD), frame, width - frame, frame + angleD);
        line(width - frame, frame + angleD, width - frame, fractH);
        line(width - frame, fractH, width - (frame + angleD / 2), fractH + angleD / 2);
            ellipse(width - (frame + angleD / 2), fractH + angleD / 2, radius, radius);

        //Bottom Left

        // Bottom Right
    }

    public void drawSphere()
    {
        float rotateX = 20 * radians(t += (TWO_PI / 360));

        translate(width / 2, height / 2);
        rotateY(rotateX);
        noFill();
        stroke(0, 255, 255);
        strokeWeight(1);
        sphere(r);
    }

    public void drawRing()
    {
        pushMatrix();
        float h = height / 2;
        float w = width / 2;
        
        stroke(0, 255, 255);
        noFill();
        strokeWeight(2);
        ellipse(w, h, 300, 300);
        popMatrix();
    }
}

