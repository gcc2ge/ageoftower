package com.nsx.ageoftower.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class AotTimer extends Label {
	//-- constante partique
	public static final int STATE_STOPED = 0;
	public static final int STATE_RUNNING = 1;
	
	int _secondsLeft;
	int _secondsTotal;
	int _state;
	float _secondsSpent;
	String _text;
	LabelStyle _labelStyle;
	
	
	public AotTimer(String text, int total, LabelStyle labelStyle){
		super(text, labelStyle);
		_labelStyle = labelStyle;
		_state = STATE_STOPED;
		_text = text;
		this.setSeconds(total);
	}
	
	public void setSeconds(int sec){
		_secondsTotal = sec;
		reset();
	}
	
	public void reset(){
		_secondsLeft = _secondsTotal;
	}
	
	public void start(){
		_state = STATE_RUNNING;
	}
	
	public void stop(){
		_state = STATE_STOPED;
	}
	
	public boolean isRunning(){
		return _state == STATE_RUNNING;
	}
	
	@Override
	public void act(float delta){
		if(_state == STATE_RUNNING){
			_secondsSpent += delta;
		}
		_secondsLeft =  (_secondsTotal-(int)_secondsSpent); 
		this.setText(_text+" : "+_secondsLeft);
		super.act(delta);
	}

	
	public void setFontSize(LabelStyle lbls){
		this.setStyle(lbls);
	}
	public void setFontScale(float fontScaleX,float fontScaleY){
		this.setFontScale(fontScaleX, fontScaleY);
	}
	
}
