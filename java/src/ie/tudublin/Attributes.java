package ie.tudublin;

import processing.data.TableRow;

public class Attributes
{
    private String name;
    private float strength; 
    private float power;
    private float speed;
    private float agility;
    private float sight;
    private float balance;

    Attributes(String name, float strength, float power, float speed, float agility, float sight, float balance)
    {
       this.name = name;
       this.strength = strength;
       this.power = power;
       this.speed = speed;
       this.agility = agility;
       this.sight = sight;
       this.balance = balance;
    }

    public Attributes(TableRow tr)
    {
        this(tr.getString("name"), tr.getFloat("strength"), tr.getFloat("power"), tr.getFloat("speed")
        , tr.getFloat("agility"), tr.getFloat("sight"), tr.getFloat("balance"));
    }

  

    public String toString()
    {
        return name + "\t" + power + "\t" + speed + "\t" + agility + "\t" + strength + "\t" + sight + "\t" + balance;  
    }



    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public float getBalance()
    {
        return balance;
    }

    public void setBalance(float balance)
    {
        this.balance = balance;
    }


    public float getSight()
    {
        return sight;
    }

    public void setSight(float sight)
    {
        this.sight = sight;
    }


    public float getAgility()
    {
        return agility;
    }

    public void setAgility(float agility)
    {
        this.agility = agility;
    }


    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }


    public float getPower()
    {
        return power;
    }

    public void setPower(float power)
    {
        this.power = power;
    }


    public float getStrength()
    {
        return strength;
    }

    public void setStrength(float strength)
    {
        this.strength = strength;
    }


}