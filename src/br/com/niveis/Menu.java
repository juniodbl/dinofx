package br.com.niveis;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import br.com.engine.componentes.audio.AudioEffect;
import br.com.engine.componentes.builders.ScriptBuilder;
import br.com.engine.componentes.drawable.SpriteFont;
import br.com.engine.core.ControleBase;
import br.com.engine.core.GameObject;
import br.com.engine.core.Scene;
import br.com.engine.core.annotation.Bootable;
import br.com.engine.input.KeyBoard;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

@Bootable
public class Menu extends Scene {
    private boolean isInitializedBGSound;
    private AudioEffect bgSound;

    private int current = 0;
    private List<SpriteFont> fonts = new ArrayList<>();

    private float transitionTime = 100;
    private float elapsedTransitionTime = 0;

    @Override
    public void setup() {
        super.setup();

        SpriteFont font = new SpriteFont("font", 50);
        GameObject itemMenu0 = new GameObject("itensMenu");
        itemMenu0.addComponente(font);
        font.setText("Escolha o Mapa");
        font.setColor(Color.RED);
        itemMenu0.getPosition().setPosition(10, 0);
        add(itemMenu0);

        createItemMenu("1- Mapa 001");
        createItemMenu("2- Mapa 002");
        createItemMenu("3- Mapa 003");
        createItemMenu("4- Mapa 004");
        createItemMenu("5- Mapa 005");
        createItemMenu("6- Mapa 006");
        createItemMenu("7- Mapa 007");
        createItemMenu("8- Mapa 008");

        GameObject obj = new GameObject("audio");
        this.bgSound = new AudioEffect("rainy_city.wav");
        obj.addComponente(this.bgSound);
        add(obj);
    }

    private void createItemMenu(String text) {
        GameObject objItemMenu = new GameObject("itensMenu");
        SpriteFont font = new SpriteFont("font", 50);
        objItemMenu.addComponente(font);
        font.setText(text);
        fonts.add(font);
        int index = fonts.size() - 1;
        objItemMenu.addComponente(ScriptBuilder.createNoTime(()->{
            if( current == index ) {
                font.setColor(Color.YELLOW);
            } else {
                font.setColor(Color.BLACK);
            }
        }));
        objItemMenu.getPosition().setPosition(10, (fonts.size()*50)+20);
        add(objItemMenu);
    }
    
    @Override
    public void update(long time) {
        super.update(time);
        elapsedTransitionTime += time;

        if(!isInitializedBGSound) {
            this.bgSound.setVolume(0.1);
            this.bgSound.play();
            this.isInitializedBGSound = true;
        }

        KeyBoard.infInstace().ifKeyPressed(KeyCode.DOWN, ()->{
            if( elapsedTransitionTime > transitionTime ) {
                current += 1;
            }

            elapsedTransitionTime = 0;
        });

        KeyBoard.infInstace().ifKeyPressed(KeyCode.UP, ()->{
            if( elapsedTransitionTime > transitionTime ) {
                current -= 1;
            }

            elapsedTransitionTime = 0;
        });

        KeyBoard.infInstace().ifKeyPressed(KeyCode.ENTER, ()->{
            ControleBase.getInstance().nextScene(current);
        });

        if( current > this.fonts.size() - 1 || current < 0 ) {
            current = 0;
        }
    }
}
