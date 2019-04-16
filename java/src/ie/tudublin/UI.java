// Andrew McGowan
//      Sci-fi UI for OOP
                 
package ie.tudublin;

import java.util.ArrayList;
import processing.core.*;
import processing.data.*;
import ddf.minim.AudioInput;
import ddf.minim.Minim;

public class UI extends PApplet
{
    // Heart rate monitor
    PVector h1, h2, h3;
    int heart;

    //Array List
    ArrayList<Attributes> attributes = new ArrayList<Attributes>();
    Table table; 

    // Audio Input ( commms )
    public static int SAMPLE_RATE = 44100;
    public static int RESOLUTION = 16;
    public static int FRAME_SIZE = 1125;
    Minim minim;
    AudioInput ai;

    // IMPORTANT - this will decide whats going to be above the sound bar
    // 1 = purple soldier,, 2 = yellow soldier,, 3 = red, soldier,, 4 == ALL soldiers
    int COLOR_W = 1;

    // Images
    PImage img;
    PImage person, people, settings, power, location;
    PImage sun;

    // Radarss
    Radar radar, radar1, radar2;
    Radar radar00, radar11, radar22;

    // Loading Rings
    float ring1, ring2, ring3, ring4;

    // All Spheres , Polymorphism
    float t, r, m, s, sa;
    Sphere sphere = new Sphere(80, 20 * radians(t += (TWO_PI / 360)));
    Sphere sunObject = new Sun(80, 20 * radians(t += (TWO_PI / 360)));
    
    // Boolean for Keys
    boolean[] keys = new boolean[1024]; 

    // Rotating Arcs
    float N = 50;
    float da = TWO_PI/N;
    float radDot = 100;
    PVector dotCircle = new PVector(322 , 220);

    //Arc Details
    int AX = width / 2;
    int AY = height / 2;
    int rad = 300;
    int ang1 = 1;
    int ang2 = 1;

    // Details
    String David = "90--70--95--77--80--55";
    String Conor = "70--98--80--88--95--70";
    String Niall = "68--88--81--97--60--90";
    String location1 = "41°24'12.2 N 2°10'26.5 E ";
    String location2 = "123°69'2.6 N 2°98'75.562 E ";
    String location3 = "312°24'20.55 N 7°199'56.5 E ";

    // For changing the size of locations
    int change;


    public void settings()
    {
        fullScreen(P3D, SPAN);
        loadTable();

        // All images
        img = loadImage("circle.png");
        people = loadImage("people.png");
        person = loadImage("person.png");
        location = loadImage("location.png");
        settings = loadImage("settings.png");
        power = loadImage("power.png");
        sun = loadImage("sun.jpg");

        // Audio 
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, RESOLUTION);

        // Heart rate
        h1 = new PVector(3090, 860);
        h2 = new PVector(3090, 1060);
        h3 = new PVector(3090, 1260);
        heart = 0;
    }

    void loadTable()
    {
        table = loadTable("attributes.csv", "header");

        for(processing.data.TableRow tr : table.rows())
        {
            Attributes p = new Attributes(tr);
            attributes.add(p);
            println(p.toString());
        }
    }

    public void setup()
    {
        //b = new Button(this, 50, 50, 100, 50, "I am a button");
        //mc = new MovingCircle(this, width / 2, height / 2, 50);
        radar = new Radar(this, width / 22, 640, 100);
        radar1 = new Radar(this, width / 22, 765, 100);
        radar2 = new Radar(this, width / 22, 890, 100);
        
        // Sphere
        t = 0;
        r = 300;
        m = 0;
        s = 100;
        sa = 70;

        // Rings
        ring1 = 330;
        ring2 = 310;
        ring3 = 290;
        ring4 = 270;

        change = 0;

    }


    public void draw()
    {
        background(0);
        stroke(0, 255, 255);
        strokeWeight(0.6f);

        // Lines following mouse w/ circle
        line(mouseX, 0, mouseX, height);
        line(0, mouseY, width, mouseY);
        noFill();
        if(mousePressed == true)
        {
            fill(255);
            ellipse(mouseX, mouseY, 30, 30);
        }
        ellipse(mouseX, mouseY, 30, 30);
        text("x: "+  mouseX + " y: " + mouseY, 30, 45);
        
        
        pushMatrix();
        translate(50, 0);
        if(COLOR_W == 1)
        {
            fill(255, 0, 255);
            stroke(255, 0, 255);
            ellipse(530, 1500, 120,120);
            image(person, 470, 1440, 120,120);
            noFill();
        }
        else if(COLOR_W == 2)
            {
                fill(255, 255, 0);
                stroke(255, 255, 0);
                ellipse(530, 1500, 120,120);
                image(person, 470, 1440, 120,120);
                noFill();
            }
            else if(COLOR_W == 3)
            {
                fill(255, 50, 50);
                stroke(255, 50, 50);
                ellipse(530, 1500, 120,120);
                image(person, 470, 1440, 120,120);
                noFill();
            }
            else if(COLOR_W ==4)
            {
                // 1
                fill(255, 0, 255);
                stroke(255, 0, 255);
                ellipse(400, 1500, 120,120);
                image(person, 340, 1440, 120,120);
                noFill();

                //2
                fill(255, 255, 0);
                stroke(255, 255, 0);
                ellipse(530, 1500, 120,120);
                image(person, 470, 1440, 120,120);
                noFill();

                //3
                fill(255, 50, 50);
                stroke(255, 50, 50);
                ellipse(660, 1500, 120,120);
                image(person, 600, 1440, 120,120);
                noFill();

            }

        // Key Pressed for Audio
        if( keyPressed == true)
        { 
            stroke(255);
            float h = 1900;
            for(int i = 0 ; i < ai.bufferSize() ; i++)
            {
                stroke(map(i, 100, FRAME_SIZE, 0, 255), 255, 255);
                line(i, h, i, h + ai.left.get(i) * h);
            } 
        }
        popMatrix();
        
    
        //PURPLE
        stroke(255, 0, 255);
        radar.update();
        radar.render();

        //YELLOW
        stroke(255, 255, 0);
        radar1.update();
        radar1.render();

        //RED
        stroke(255, 0, 0);
        radar2.update();
        radar2.render();

        extraArc();
        soldiers();
        buttons();
        drawArc();
        topRight();
        drawGrid();
        drawSphere();
        drawLeftMid();
        heartMonitor();
        drawDetails();

        image(sun, 1100, 252, 400, 369);
        image(img, width / 3 + 155, height / 4 + 55, 970, 970);
    }

    public void drawDetails()
    {
        stroke(255);
        fill(255);
        textSize(30);
        text("Rank: ", 3080, 1650);
        text("Name: ", 3080, 1750);
        text("Attrs: ", 3080, 1850);
        text("ID: ", 3080, 1950);
        text("Status: ", 3080, 2050);

        text("Logs", 2670, 1650);
        line(2650, 1660, 2755, 1660);
    }

    public void heartMonitor()
    {
        stroke(255);
        fill(255);
        float radius = 17;
        smooth();

        strokeWeight(0.7f);
        line(3090, 860, 3640, 860);
        line(3090, 1060, 3640, 1060);
        line(3090, 1260, 3640, 1260);

        textSize(17);
        text("67 bpm", 3558, 920);
        text("64 bpm", 3558, 1120);
        text("0 bpm", 3558, 1320);

        strokeWeight(1);

        // Purple soldier
        stroke(0, 255, 0);
        fill(0, 255, 0);
        ellipse(h1.x, h1.y, radius, radius);
        if(heart == 0)
        {
            h1.x += 6;
        }
        if(h1.x > 3300 && h1.x < 3340)
        {
            h1.y -= 9;
        }
        if(h1.x > 3340 && h1.x < 3420)
        {
            h1.y +=9;
        }
        if(h1.x > 3420 && h1.x < 3465)
        {
            h1.y -= 9;
        }
        if(h1.x > 3640)
        {
            h1.x = 3090;
        }

        // Yellow Soldier
        stroke(0, 255, 0);
        fill(0, 255, 0);
        ellipse(h2.x, h2.y, radius, radius);
        if(heart == 0)
        {
            h2.x += 6;
        }
        if(h2.x > 3300 && h2.x < 3340)
        {
            h2.y -= 9;
        }
        if(h2.x > 3340 && h2.x < 3420)
        {
            h2.y +=9;
        }
        if(h2.x > 3420 && h2.x < 3465)
        {
            h2.y -= 9;
        }
        if(h2.x > 3640)
        {
            h2.x = 3090;
        }

        // Red Soldier
        strokeWeight(1);
        stroke(255, 0, 0);
        fill(255, 0, 0);
        ellipse(h3.x, h3.y, radius, radius);

        if(heart == 0)
        {
            h3.x += 6;
        }
        if(h3.x > 3640)
        {
            h3.x = 3090;
        }
          
        stroke(255);
    }


    public void mousePressed()
    {
        // Buttons in top left
        if(mouseX > 900 && mouseX < 1000 && mouseY > 75 && mouseY < 135)
        {
            COLOR_W = 1;
        }
        
        if(mouseX > 1030 && mouseX < 1130 && mouseY > 75 && mouseY < 135)
        {
            COLOR_W = 4;
        }

        // Icons on right of soldiers

        if(mouseX > 2900 && mouseX < 3700 && mouseY > 760 && mouseY < 960)
        {
            COLOR_W = 1;
        }
        if(mouseX > 2900 && mouseX < 3700 && mouseY > 960 && mouseY < 1160)
        {
            COLOR_W = 2;
        }
        if(mouseX > 2900 && mouseX < 3700 && mouseY > 1160 && mouseY < 1360)
        {
            COLOR_W = 3;
        }


        // For location button
        if(mouseX > 1160 && mouseX < 1260 && mouseY > 75 && mouseY < 135)
        {
            m = 30;
        }

        if(mouseX > 1290 && mouseX < 1390 && mouseY > 75 && mouseY < 135)
        {
            m = 0;
        }

        // Exit
        if(mouseX > 770 && mouseX < 870 && mouseY > 75 && mouseY < 135)
        {
            exit();
        }
    }

    public void extraArc()
    {
        stroke(255);
        strokeWeight(2);
        smooth();
        noFill();

        ang1 += 1;
        ang2 += 0.5f;

        fill(0, 255, 255);
        textSize(40);
        text("91%", 285, 230);
        noFill();
        
        pushMatrix();

            translate(270, 175);
            stroke(255);
            arc(AX, AY, rad, rad, radians(-ang2), radians(-ang2+150));
            arc(AX, AY, rad + 15, rad + 15, radians(ang2), radians(ang2+150));
            arc(AX, AY, rad + 30, rad + 30, radians(-ang1), radians(-ang1+150));
            
        popMatrix();

        pushMatrix();

            for(float a = 0 ; a < TWO_PI ; a += da) 
            {
                ellipse(dotCircle.x + radDot * cos(a), dotCircle.y + radDot * sin(a), 5, 5);
            }

        popMatrix();
    }

    public void soldiers()
    {
        float lenI = 100;

        float wid1 = 2900;
        float wid2 = 800;

        float disX = 2200;
        float disY = height / 8 * 6f + 250; 

        //Soldier 1
        noFill();
        stroke(255);
        strokeWeight(2);
        rect(wid1, 760, wid2, 200, 50);
        textSize(30);

        fill(255, 0, 255);
        stroke(255, 0, 255);
        ellipse(3000, 860, 120, 120);
            image(person, 2950, 815, lenI, lenI);
        
            if(mouseX >= wid1 && mouseX <= (wid1 + wid2) && mouseY >= 760 && mouseY <= 960 )
            {
                strokeWeight(4);

                line(3229, 130, 3140, 361);
                line(3140, 361, 3245, 555);
                line(3245, 555, 3433, 487);
                line(3433, 487, 3570, 360);
                line(3570, 360, 3498, 115);
                line(3498, 115, 3229, 130);

                pushMatrix();
                    noFill();
                    //ellipse(disX, disY, 130, 130);
                    stroke(255, 0, 255);
                    radar00 = new Radar(this, disX, disY, 300);
                    radar00.render();
                popMatrix();

                fill(0, 255, 255);
                text("Captain", 3200, 1650);
                text("David Mulally", 3200, 1750);
                text(David, 3200, 1850);
                text("234-FF-J889", 3200, 1950);
                fill(0, 255, 0);
                text("Online", 3200, 2050);
                fill(0, 255, 255);

                text(location1, 2000, 2056);

                text("Day 1 : Hello, cant beli..", 2510, 1750);
                text("Day 2 : Still no sign of..", 2510, 1850);
                text("Day 3 : Unsuccessful day..", 2510, 1950);
                text("Day 4 : I have been hear..", 2510, 2050);
            }

        //Soldier 2
        noFill();
        stroke(255);
        strokeWeight(2);
        rect(wid1, 960, wid2, 200, 50);

        fill(255, 255, 0);
        stroke(255, 255, 0);
        ellipse(3000, 1060, 120, 120);
            image(person, 2950, 1015, lenI, lenI);

            if(mouseX >= wid1 && mouseX <= (wid1 + wid2) && mouseY >= 960 && mouseY <= 1160 )
            {
                strokeWeight(4);

                line(3263, 189, 3094, 357);
                line(3094, 357, 3222, 600);
                line(3222, 600, 3454, 527);
                line(3454, 527, 3582, 361);
                line(3582, 361, 3462, 182);
                line(3462, 182, 3263, 189);

                pushMatrix();
                    noFill();
                    //ellipse(disX, disY, 130, 130);
                    stroke(255, 255, 0);
                    radar11 = new Radar(this, disX, disY, 300);
                    radar11.render();
                popMatrix();

                fill(0, 255, 255);
                text("Corporal", 3200, 1650);
                text("Conor Dwyer", 3200, 1750);
                text(Conor, 3200, 1850);
                text("444-UF-J112", 3200, 1950);
                fill(0, 255, 0);
                text("Online", 3200, 2050);
                fill(0, 255, 255);

                text(location2, 2000, 2056);

                text("Day 1 : First day down he..", 2510, 1750);
                text("Day 2 : No a sound after d..", 2510, 1850);
                text("Day 3 : Lost comms but I a..", 2510, 1950);
                text("Day 4 : I fear no life is o..", 2510, 2050);
            }
        

        //Soldier 3
        noFill();
        stroke(255);
        strokeWeight(2);
        rect(wid1, 1160, wid2, 200, 50);

        fill(255, 50, 50);
        stroke(255, 55, 55);
        ellipse(3000, 1260, 120, 120);
            image(person, 2950, 1215, lenI, lenI);

            if(mouseX >= wid1 && mouseX <= (wid1 + wid2) && mouseY >= 1160 && mouseY <= 1360 )
            {
                strokeWeight(4);

                line(3268, 199, 3108, 362);
                line(3108, 368, 3282, 496);
                line(3282, 496, 3490, 586);
                line(3490, 586, 3644, 360);
                line(3644, 360, 3461, 185);
                line(3461, 185, 3268, 199);

                pushMatrix();
                    noFill();
                    //ellipse(disX, disY, 130, 130);
                    stroke(255, 50, 50);
                    radar22 = new Radar(this, disX, disY, 300);
                    radar22.render();

                    fill(255, 0, 0);
                    ellipse(40, 40, 10, 10);
                popMatrix();

                fill(0, 255, 255);
                text("Corproal", 3200, 1650);
                text("Niall Lee", 3200, 1750);
                text(Niall, 3200, 1850);
                text("999-FF-Q234", 3200, 1950);
                fill(255, 0, 0);
                text("Unresponsive", 3200, 2050);
                fill(0, 255, 255);

                text(location3, 2000, 2056);

                text("Day 1 : Morning team, I hav..", 2510, 1750);
                text("Day 2 : Im sure I heard nois..", 2510, 1850);
                text("Day 3 : Noises now waking me..", 2510, 1950);
            }
    }

    public void buttons()
    {
        float lenX = 100;
        float lenY = 60;

        fill(255);
        stroke(0, 255, 255);

        rect(770, 75, lenX, lenY, 10);
        rect(900, 75, lenX, lenY, 10);
        rect(1030, 75, lenX, lenY, 10);
        rect(1160, 75, lenX, lenY, 10);
        rect(1290, 75, lenX, lenY, 10);

        // Icons
        image(power, 795, 80, 50, 50);
        image(person, 925, 80, 50, 50);
        image(people, 1055, 80, 50, 50);
        image(location, 1185, 80, 50, 50);
        image(settings, 1315, 80, 50, 50);
        

    }

    public void drawLeftMid()
    {
        float frame = 50;
        stroke(255);
        noFill();
        strokeWeight(2);
        rect(frame, height / 4, 700, 450, 15);

        // Enemies in radar
        
        stroke(255, 0, 0);
        fill(255, 0, 0);
        ellipse(160, 867, 5, 5);
        ellipse(198, 876, 5, 5);
        ellipse(195, 901, 5, 5);
        ellipse(182, 862, 5, 5);

        textSize(30);
        stroke(255);
        fill(255);
        text(location1, 250,650);

        text(location2, 250,770);

        text(location3, 250,890);
    }


    public void drawArc()
    {
        stroke(255);
        strokeWeight(2);
        smooth();
        noFill();

        ang1 += 1;
        ang2 += 5;
        pushMatrix();
        translate(width / 2 - 50, height / 2 - 50);
            //arc(AX, AY, rad + 500, rad + 500, radians(ang1), radians(ang1+300));
            arc(AX, AY, rad + 600, rad + 200, radians(-ang2), radians(-ang2+150));
            
        popMatrix();
    }

    public void topRight()
    {
        pushMatrix();
            translate(width - (width / 8), height / 6);

            noFill();
            stroke(0, 255, 255);
            strokeWeight(2);
            polygon(6, 0, 0, 300);

            stroke(255);
            strokeWeight(0.5f);
            polygon(6, 0, 0, 200);
            polygon(6, 0, 0, 250);
            polygon(6, 0, 0, 150);

        popMatrix();

        // Attributes
        stroke(255);
        textSize(30);
        text("Strength", 3130, 75);
        text("Power", 3480, 75);
        text("Speed", 2950, 370);
        text("Sight", 3670, 370);
        text("Balance", 3130, 670);
        text("Agility", 3480, 670);

        stroke(255);
        strokeWeight(0.7f);

        line(3210, 100, 3510, 620);
        line(3210, 620, 3510, 100);
        line(3060, 360, 3660, 360);

    }


    // Polygon for attributes in thop right corner
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

        float radius = 20;
        fill(255);

        stroke(255, 255, 255); //255-0-255 when finished 
        strokeWeight(2f);
        
        // Top Left

            ellipse(width / 6, height / 5, radius, radius);
        line(width / 6, height / 5, frame * 2, height / 5);
        line(frame * 2, height / 5, frame, height / 5 - frame);
        line(frame, height / 5 - frame, frame, frame * 2);
        line(frame, frame * 2, frame * 2, frame);
        line(frame * 2, frame, width / 6, frame);
        line(width / 6, frame, width / 6 + frame, frame * 2);
        line(width / 6 + frame, frame * 2, width / 6 + frame + angleD, frame * 2);
        line(width / 6 + frame + angleD, frame * 2, width / 6 + frame * 2 + angleD, frame);
        line(width / 6 + frame * 2 + angleD, frame, width / 2, frame);
        line(width / 2, frame, width / 2 + bigF / 2, frame + bigF / 2);
            ellipse(width / 2 + bigF / 2, frame + bigF / 2, radius, radius);
        

        // Top Right
        
            ellipse(fractW * 4 - (angleD / 2), frame + angleD / 2, radius, radius);
        line(fractW * 4 - (angleD / 2), frame + angleD / 2, fractW * 4, frame);
        line(fractW * 4, frame, width - (frame + angleD), frame);
        line(width - (frame + angleD), frame, width - frame, frame + angleD);
        line(width - frame, frame + angleD, width - frame, fractH);
        line(width - frame, fractH, width - (frame + angleD / 2), fractH + angleD / 2);
            ellipse(width - (frame + angleD / 2), fractH + angleD / 2, radius, radius);

        //Bottom Left

            ellipse(frame + angleD / 2, height - (height / 4) - angleD / 2, radius, radius);
        line(frame + angleD / 2, height - (height / 4) - angleD / 2, frame, height - (height / 4));
        line(frame, height - (height / 4), frame, height - frame);
        line(frame, height - frame, width / 3 - bigF, height - frame);
        line(width / 3 - bigF, height - frame, (width / 3 - bigF) + angleD / 2, (height - frame) - angleD / 2);
            ellipse((width / 3 - bigF) + angleD / 2, (height - frame) - angleD / 2, radius, radius);

                // Grid for Console
                noFill();
                stroke(0, 255, 255);
                strokeWeight(2);
                    textSize(40);
                    text("Comms", angleD * 10, height / 8 * 6 - 5);
                        rect(angleD, height / 8 * 6, width / 3 - 150, 490, 150);
                stroke(255);
                strokeWeight(2);
                fill(255);

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

                ellipse(width / 2, height - frame * 6, radius, radius);
            line(width / 2, height - frame * 6, width /2, 1700);
            line(width /2, 1700, width / 2 + 90, 1600);
            line(width / 2 + 90, 1600, 3700, 1600);
                ellipse(3700, 1600, radius, radius);
    }

    public void drawSphere()
    {
        float rotateX = 20 * radians(t += (TWO_PI / 360));
        
        // Main Planet
        pushMatrix();

            translate(width / 2, height / 2);
            rotateY(rotateX);
            noFill();
            stroke(0, 255, 255);
            strokeWeight(1f);
            sphere(r);

            // Soldier Locations on planet
            fill(255, 0, 0);
            stroke(255, 0, 0);
            translate(r - 80, r - 100);
            sphere(m);

            fill(255, 0, 255);
            stroke(255, 0, 255);
            translate(70 , -250);
            sphere(m);

            fill(255, 255, 0);
            stroke(255, 255, 0);
            translate(- 585, 70);
            sphere(m);

        popMatrix();

        // Sun
        pushMatrix();

            translate(1300, 450);
            rotateY(rotateX);
            stroke(204, 102, 0);
            noFill();
            sphere(s);

        popMatrix();

        // Ringed Planet
        pushMatrix();

            translate(2500, 450);
            rotateY(rotateX);
            stroke(255, 155, 155);
            noFill();
            sphere(sa);

            strokeWeight(3);
                rotateX(HALF_PI-.60f);
                ellipse(0, 0, ring1, ring1);
                ellipse(0, 0, ring2, ring2);
                ellipse(0, 0, ring3, ring3);
                ellipse(0, 0, ring4, ring4);

                if(mouseX >= 2430 && mouseX <= 2570 && mouseY >= 380 && mouseY <= 520)
            {
                sa = 100;
                ring1 = 390;
                ring2 = 370;
                ring3 = 350;
                ring4 = 330;
            }
            else
            {
                sa = 70;
                ring1 = 330;
                ring2 = 310;
                ring3 = 290;
                ring4 = 270;
            }
        popMatrix();       
    }
}
