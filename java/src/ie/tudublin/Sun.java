//Polymorphism

package ie.tudublin;

public class Sun extends Sphere
{

    Sun(float s, float r)
    {
        super(s, r);
    }

    void update()
    {
        //change in size
        sphereSize = 50;
        rotate = 20 * radians(t += (TWO_PI / 360));
        rotateY(rotate);
        sphere(sphereSize);
    }
}