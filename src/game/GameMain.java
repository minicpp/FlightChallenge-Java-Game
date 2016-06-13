package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import helper.Application;

public class GameMain extends Application{

	private Image star;
	int x,y;
	
	public static void main(String [] args){
		GameMain gameMain = new GameMain();
	}
	
	public void init(){
		
		//load resources
		ImageIcon ii = new ImageIcon("img/Golden.png");
        star = ii.getImage();
	}
	
	public void update(){
		++x;
		++y;
		if(x > Application.WINDOW_WIDTH){
			x = 0;
		}
		if(y > Application.WINDOW_HEIGHT){
			y = 0;
		}
	}
	
	public void draw(){
		this.drawImage(star, x, y);
	}

}
