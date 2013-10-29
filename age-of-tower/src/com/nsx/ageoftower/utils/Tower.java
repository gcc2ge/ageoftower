package com.nsx.ageoftower.utils;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.nsx.ageoftower.event.AotEvent;
import com.nsx.ageoftower.screen.AbstractScreen;

public class Tower extends Group implements  EventListener{
	public static final int STATE_DISABLE = 0;
	public static final int STATE_ENABLE = 1;
	
	int _state;
	boolean _clicked = false;
	Image _img;
	private Vector2 _p;
	private Range _range;
	
	public Tower(int tileWidth, int tileHeight, int xIndex, int yIndex) {
		this.setSize(tileWidth, tileHeight);
		this.setPosition(tileWidth*xIndex,AbstractScreen.GAME_VIEWPORT_HEIGHT-tileHeight*(yIndex+1));
		_p = new Vector2(tileWidth*xIndex, AbstractScreen.GAME_VIEWPORT_HEIGHT-tileHeight*(yIndex+1));
	
		//-- the tower image
		Texture t = new Texture(Gdx.files.internal("data/tower1.png"));
		TextureRegion region = new TextureRegion(t, 0, 0, 32, 32);
		_img = new Image(region);
		_img.setSize(32,32);
		this.addActor(_img);
		
		_range = new Range();
		_range.setX(-54);
		_range.setY(-54);
		this.addActor(_range);
		
		//-- pour recuperer les evenements
		this.addListener(this);
		_state = STATE_DISABLE;
	}
	
	public void setState(int s) {
		_state = s;
	}
	
	public Range getRange(){
		return _range;
	}
	
	public int getState(){
		return _state;
}	

	@Override
	public void act(float delta){
		switch(_state){
		case STATE_DISABLE:
			_img.setVisible(false);
			_range.setVisible(false);
			break;
		case STATE_ENABLE:
			_img.setVisible(true);
			_range.setVisible(true);
			break;
		}
		super.act(delta);
	}
	
	
	@Override
	public boolean handle(Event event) {
		
		if(event instanceof AotEvent){
			System.out.println("    Tower AotEvent received:"+event);
			switch(((AotEvent)event).getType()){
				case restartLevel: //-- evenement declanch� par le  
					this.setState(STATE_DISABLE);
					break;
				case gameOver: //-- evenement declanch� par le  
					//-- boom?
					break;
			}
		}
		
		if (!(event instanceof InputEvent)) return false;
		if(((InputEvent)event).getButton() == 0 && event.getTarget()==this){
			if( !_clicked){
				this.fire(new AotEvent(AotEvent.Type.towerClicked,this));
				_clicked = true;
			}else{
				_clicked = false;
			}
		}
		return true;
	}

}
