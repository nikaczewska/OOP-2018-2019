package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{

	int selected1 = -1;
	int selected2 = -1;


    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {
        //loadData();
        //printStars();
        border = width * 0.05f;
		
		//Testing the map function
		System.out.println(
			map(25,20,30,30,40)
		);
		
		System.out.println(
			map1(25,20,30,30,40)
		);
		
		System.out.println(
			map(0,-100,200,100,500)
		);
		
		System.out.println(
			map1(0,-100,200,100,500)
		);
		
		Star s = new Star();
		s.setDisplayName("DIT");
		stars.add(s);
		
		Star s1 = stars.get(0);
		
		s1.setDisplayName("TU Dublin");
		
		System.out.println(s);
		System.out.println(stars.get(0));
		System.out.println(s1);
		
    }
	

    public void loadData()
    {
        Table table = loadTable("HabHYG15ly.csv", "header");
        
        /*
        for(int i = 0 ; i < table.getRowCount() ; i ++)
        {
            TableRow row = table.getRow(i);
            System.out.println(row.getString("Display Name"));
            System.out.println(row.getString("Hab?"));            
        }
        */

        for(TableRow row:table.rows())
        {
            Star star = new Star(row);
            stars.add(star);     
        }
    }

    public void printStars()
    {
        for(Star star:stars)
        {
            System.out.println(star);
        }
    }

    float border;

    private void drawGrid()
    {
        textAlign(CENTER, CENTER);
        for(int i = -5 ; i <= 5 ; i ++)
        {
            float x = map(i, -5, 5, border, width - border);
            stroke(0, 0, 255);
            line(x, border, x, height - border);
            fill(255);
            text(i, x, border / 2);
            stroke(0, 0, 255);
            line(border, x, width - border, x);
            fill(255);            
            text(i, border / 2, x);
        }
    }

    public void drawStars()
    {
        textAlign(LEFT, CENTER);
        for(Star s:stars)
        {
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);

            stroke(255, 255, 0);
            noFill();
            ellipse(x, y, s.getAbsMag(), s.getAbsMag());

            stroke(0, 255, 255);
            line(x, y - 5, x, y + 5);
            line(x - 5, y, x + 5, y);
            fill(255);
            text(s.getDisplayName(), x + 20, y);
            
            

        }
    } 
	
	private float map1 (float a, float b, float c, float d, float e)
	{
		float range1 = c - b;
		float howFar = a - b;
		
		float range2 = e - d;
		
		return d + (howFar / range1) * range2;
		
	}

    public void mouseClicked()
    {
		for(int i = 0; i < stars.size(); i++)
		{
			Star s = stars.get(i);
			float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
			
			if(dist(mouseX,mouseY, x, y) < s.getAbsMag() / 2)
			{
				if(selected1 == -1)
				{
					selected1 = i;
					break;
				}
				
				if(selected2 == -1)
				{
					selected2 = i;
				}
			}
		}
        //mouseX, mouseY;    
		
    }

    public void draw()
    {
        background(0);
        drawGrid();
        drawStars();
		mouseClicked();
    }

    private ArrayList<Star> stars = new ArrayList<Star>();

}