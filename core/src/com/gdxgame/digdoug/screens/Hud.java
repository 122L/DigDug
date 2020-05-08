package com.gdxgame.digdoug.screens;

import javax.swing.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud implements Disposable{

    public Stage stage;

    private Integer worldTimer;
    private boolean timeUp;
    private float timeCount;
    private static Integer score;

    Label countdownLabel;
    Label timeLabel;
    Label scoreNameLabel;
    static Label scoreLabel;
    Object[] options = {"Log Score", "Quit"};
    ImageIcon logo = new ImageIcon("logo.png");

    public Hud(SpriteBatch sb){
        Viewport viewport;
        worldTimer = 45;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreNameLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreNameLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
                countdownLabel.setText(String.format("%03d", worldTimer));
            } else {
                timeUp = true;
            }
            timeCount = 0;
        }
        if(timeUp){
            int selection;
            selection = JOptionPane.showOptionDialog(null, "Game over! Would you like to record your score?", "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo, options, options[1]);
            if(selection == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "Score Recorded... Goodbye!");
                Gdx.app.exit();
            }
            else{
                JOptionPane.showMessageDialog(null, "Goodbye!");
                Gdx.app.exit();
            }
        }
    }
    public void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%03d", score));

    }

    public void dispose(){
        stage.dispose();
    }
}
