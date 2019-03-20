// Info 
//      On Fullscreen - Dims (1535, y) roughly..

package ie.tudublin;

import processing.core.*;

public class UI extends PApplet
{
    Button b;
    MovingCircle mc;
    Radar radar;

    float t, r;
    Sphere sphere = new Sphere(80, 20 * radians(t += (TWO_PI / 360)));


    boolean[] keys = new boolean[1024]; 

    //Arc Details
    int AX = width / 2;
    int AY = height / 2;
    int lar = 300;
    int alt = 300;
    int ang1 = 1;
    int ang2 = 1;
    int ang3 = 1;

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
        //b = new Button(this, 50, 50, 100, 50, "I am a button");
        //mc = new MovingCircle(this, width / 2, height / 2, 50);
        radar = new Radar(this, width - (width / 8), height - (height / 6) - 20, 200);

        // Sphere
        t = 0;
        r = 130;
    }

    public void draw()
    {
        background(0);
        //b.render();

        //mc.update();
        //mc.render();

        radar.update();
        radar.render();

        /*if (checkKey(LEFT))
        {
            System.out.println("Left arrow key pressed");
        }*/

        drawArc();
        topRight();
        drawGrid();
        //drawRing();
        drawSphere();

        // Areas
      
    }


    public void drawArc()
    {
        stroke(255);
        strokeWeight(2);
        smooth();

        noFill();

        ang1 += 1;
        ang2 += 1;
        ang3 += 1;
        pushMatrix();
        translate(width / 2 - 50, height / 2 - 50);
        arc(AX, AY, lar, alt, radians(ang1), radians(ang1+300));
        arc(AX, AY, lar+50, alt+50, radians(-ang2), radians(-ang2+150));
        popMatrix();
    }

    public void topRight()
    {
        pushMatrix();
            translate(width - (width / 8), height / 6);

            noFill();
            stroke(0, 255, 255);
            strokeWeight(2);
            polygon(6, 0, 0, 90);

            stroke(255);
            strokeWeight(0.5f);
            polygon(6, 0, 0, 65);
            polygon(6, 0, 0, 50);
            polygon(6, 0, 0, 35);

        popMatrix();
    }

    void polygon(int n, float cx, float cy, float r) {
        float angle = 360.0f / n;
      
        beginShape();
        for (int i = 0; i < n; i++) {
          vertex(cx + r * cos(radians(angle * i)),
            cy + r * sin(radians(angle * i)));
        }
        endShape(CLOSE);
      }

    public void drawGrid()
    {
        float frame = 20;
        float bigF = 80;
        float angleD = 50;
        float fractW = width / 5;
        float fractH = height / 5;

        float radius = 10;
        fill(255);

        stroke(255, 255, 255); //255-0-255 when finished 
        strokeWeight(0.8f);
        
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
            ellipse(width - bigF, height / 3, radius, radius);
        line(width - bigF, height / 3, width - bigF, height / 3 + bigF);
        line(width - bigF, height / 3 + bigF, width - frame, height / 3 + bigF +60);
        line(width - frame, height / 3 + bigF +60, width - frame, height / 10 * 9);
        line(width - frame, height / 10 * 9 , width - 60, height - bigF / 2 - 10);
        line(width - 60, height - bigF / 2 -10, width - 80,height - bigF / 2 -10 );
        line(width - 80,height - bigF / 2 -10, width - bigF - 30, height - frame);
        line(width - bigF - 30, height - frame, width - (width / 5), height - frame);
        line(width - (width / 5), height - frame, width / 5 * 4, height - frame);
        line(width / 5 * 4, height - frame, width / 5 * 4 - (bigF / 2), height - frame - bigF / 2);
        line(width / 5 * 4 - (bigF / 2), height - frame - bigF / 2, width / 5 * 4 - (bigF / 2 ) - (bigF * 2) ,height - frame - bigF / 2);
        line(width / 5 * 4 - (bigF / 2 ) - (bigF * 2) ,height - frame - bigF / 2, width / 5 * 4 - (bigF / 2 ) - (bigF * 2) - bigF /2, height - frame);
        line(width / 5 * 4 - (bigF / 2 ) - (bigF * 2) - bigF /2, height - frame, width / 2, height - frame);
            ellipse(width / 2, height - frame, radius, radius);
    }

    public void drawSphere()
    {
        float rotateX = 20 * radians(t += (TWO_PI / 360));

        translate(width / 2, height / 2);
        rotateY(rotateX);
        noFill();
        stroke(0, 255, 255);
        strokeWeight(0.5f);
        sphere(r);
    }

    /*public void drawRing()
    {
        pushMatrix();
        float h = height / 2;
        float w = width / 2;
        
        stroke(0, 255, 255);
        noFill();
        strokeWeight(2);
        ellipse(w, h, 300, 300);
        popMatrix();
    }*/

    
}

