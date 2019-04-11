package ie.tudublin;

import processing.core.PApplet;

public class Sphere extends PApplet
{
    float sphereSize;
    float rotate;
    float t = 0;

    Sphere(float s, float r)
    {
        sphereSize = s;
        rotate = r;
    }

    public Sphere(String name) {
	}

	void update()
    {
        sphereSize = 100;
        rotate = 20 * radians(t += (TWO_PI / 360));
        rotateY(rotate);
        sphere(sphereSize);
    }
}