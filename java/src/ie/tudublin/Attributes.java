package ie.tudublin;

import processing.data.TableRow;

public class Attributes
{
    String name;
    String strength; 
    String power;
    String speed;
    String agility;
    String sight;
    String balance;

    public Attributes(TableRow row)
    {
        name = row.getString("Name");
        strength = row.getString("Strength");
        speed = row.getString("Speed");
        power = row.getString("Power");
        sight = row.getString("Sight");
        balance = row.getString("Balance");
        agility = row.getString("Agility");
    }

    public String toString()
    {
        return name + "\t" + power + "\t" + speed + "\t" + agility + "\t" + strength + "\t" + sight + "\t" + balance;  
    }


}