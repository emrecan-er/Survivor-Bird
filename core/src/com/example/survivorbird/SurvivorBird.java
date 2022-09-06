package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture background;
	Texture bird;
	Texture enemy1;
	Texture enemy2;
	Texture enemy3;
	Texture enemy4;
	Texture booster;
	float birdX=0;
	float birdY=0;
	int gameState=0;
	float velocity=0;
	float gravity=0.5f;
	float enemyVelocity=2;
	Random random;
	float score=0;
	int scoredEnemy=0;
	BitmapFont font;
	BitmapFont font2;

	Circle birdCircle;
	//ShapeRenderer shapeRenderer;

	int numberOfEnemies=4;
	float []enemyX = new float[numberOfEnemies];
	float []enemyOffset= new float[numberOfEnemies];
	float []enemyOffset2= new float[numberOfEnemies];
	float []enemyOffset3= new float[numberOfEnemies];
	float []enemyOffset4= new float[numberOfEnemies];
	float []enemyOffset5= new float[numberOfEnemies];
	float distance=0;
	Circle []enemyCircle;
	Circle []enemyCircle2;
	Circle []enemyCircle3;
	Circle []enemyCircle4;
	Circle []boosterCircle;

	@Override
	public void create () {

		batch = new SpriteBatch();
		background = new Texture("background.png");
		img = new Texture("studio.png");
		bird = new Texture("bird.png");
		enemy1= new Texture("enemy.png");
		enemy2= new Texture("enemy.png");
		enemy3= new Texture("enemy.png");
		enemy4= new Texture("enemy.png");
		booster=new Texture("booster.png");

		distance=Gdx.graphics.getWidth()/3;
		//shapeRenderer=new ShapeRenderer();
		random=new Random();
		birdX=Gdx.graphics.getWidth()/8;
		birdY=Gdx.graphics.getHeight()/2;
		birdCircle=new Circle();
		enemyCircle=new Circle[numberOfEnemies];
		enemyCircle2=new Circle[numberOfEnemies];
		enemyCircle3=new Circle[numberOfEnemies];
		enemyCircle4=new Circle[numberOfEnemies];
		boosterCircle=new Circle[numberOfEnemies];
		font= new BitmapFont();
		font2=new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(6);
		font.setColor(Color.WHITE);
		font.getData().setScale(4);

		for(int i =0;i<numberOfEnemies;i++){

			enemyOffset[i]=random.nextFloat()*(Gdx.graphics.getHeight());
			enemyOffset2[i]=random.nextFloat()*(Gdx.graphics.getHeight());
			enemyOffset3[i]=random.nextFloat()*(Gdx.graphics.getHeight());
			enemyOffset4[i]=random.nextFloat()*(Gdx.graphics.getHeight());
			enemyOffset5[i]=random.nextFloat()*(Gdx.graphics.getHeight());


			enemyX[i]=Gdx.graphics.getWidth()-enemy1.getWidth()/2+i*distance;

			enemyCircle[i]= new Circle();
			enemyCircle2[i]= new Circle();
			enemyCircle3[i]= new Circle();
			enemyCircle4[i]= new Circle();
			boosterCircle[i]=new Circle();

		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(gameState==1){

			if(enemyX[scoredEnemy]<Gdx.graphics.getWidth()/8)
			{
				score++;
				if(scoredEnemy<numberOfEnemies-1){
					scoredEnemy++;
				}
				else{
					scoredEnemy=0;
				}
			}
			if(Gdx.input.justTouched())
			{
				velocity=-13;
			}
			for(int i=0;i<numberOfEnemies;i++)
			{



				if(enemyX[i]<0){
					enemyX[i]+=numberOfEnemies*distance;

					enemyOffset[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-250);
					enemyOffset2[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-250);
					enemyOffset3[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-250);
					enemyOffset4[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-250);
					enemyOffset5[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);


				}
				else
				{
					enemyX[i]-=enemyVelocity;
				}

				enemyX[i]=enemyX[i]-enemyVelocity;
				batch.draw(enemy1,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
				batch.draw(enemy2,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset2[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
				batch.draw(enemy3,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset3[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
				batch.draw(enemy4,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset4[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
				batch.draw(booster,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffset5[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
				enemyCircle[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
				enemyCircle2[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset2[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
				enemyCircle3[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset3[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
				enemyCircle4[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset4[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
				boosterCircle[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset5[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
			}


			if(birdY>0&&birdY<Gdx.graphics.getHeight()-2)
			{
				velocity+=gravity;
				birdY=birdY-velocity;
			}
			else{
				gameState=2;
			}
		}
		else if(gameState==0)
		{
			if(Gdx.input.justTouched()){
				gameState=1;
			}
		}
		else if(gameState==2)
		{
			font2.draw(batch,"Game Over Tap to Play Again",300,Gdx.graphics.getHeight()/2);
			if(Gdx.input.justTouched()){
				gameState=1;
			}
			birdY=Gdx.graphics.getHeight()/2;
			for(int i =0;i<numberOfEnemies;i++){

				enemyOffset[i]=random.nextFloat()*(Gdx.graphics.getHeight());
				enemyOffset2[i]=random.nextFloat()*(Gdx.graphics.getHeight());
				enemyOffset3[i]=random.nextFloat()*(Gdx.graphics.getHeight());
				enemyOffset4[i]=random.nextFloat()*(Gdx.graphics.getHeight());
				enemyOffset5[i]=random.nextFloat()*(Gdx.graphics.getHeight());

				enemyX[i]=Gdx.graphics.getWidth()-enemy1.getWidth()/2+i*distance;

				enemyCircle[i]= new Circle();
				enemyCircle2[i]= new Circle();
				enemyCircle3[i]= new Circle();
				enemyCircle4[i]= new Circle();
				boosterCircle[i]=new Circle();

			}
			velocity=0;
			score=0;
			scoredEnemy=0;


		}

		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
		batch.draw(img, 0, 0);
		font.draw(batch,String.valueOf((int)score),100,200);

		batch.end();
		birdCircle.set(birdX+Gdx.graphics.getWidth()/30,birdY+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/40);

		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);

		for(int i=0;i<numberOfEnemies;i++)
		{

			//shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
			//shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset2[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
			//shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffset3[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth()/30);
			//shapeRenderer.circle(boosterCircle[i].x,boosterCircle[i].y,boosterCircle[i].radius);

			if(Intersector.overlaps(birdCircle,enemyCircle[i])||Intersector.overlaps(birdCircle,enemyCircle2[i])||Intersector.overlaps(birdCircle,enemyCircle3[i])||Intersector.overlaps(birdCircle,enemyCircle4[i]))
			{
			gameState=2;
			}
			if (Intersector.overlaps(birdCircle,boosterCircle[i])){
				score+=0.05;
			}

		}


		//shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
