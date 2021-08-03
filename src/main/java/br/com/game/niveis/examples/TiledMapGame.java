package br.com.game.niveis.examples;

import br.com.engine.componentes.drawable.TmxMap;
import br.com.engine.core.GameObject;
import br.com.engine.core.Scene;
import br.com.engine.input.KeyBoard;
import javafx.scene.input.KeyCode;

public class TiledMapGame extends Scene {

    private final float velocidadeCamera = 10;

    @Override
    public void setup() {
        super.setup();

        GameObject mapa = new GameObject();
        mapa.addComponente(new TmxMap("ola_mapa"));

        add(mapa);
    }

    @Override
    public void update(long time) {
        super.update(time);

        KeyBoard.infInstace().ifKeyPressed( KeyCode.D, ()-> 
			getObject("default_camera").getPosition().plus(velocidadeCamera, 0f) );
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.A, ()-> 
			getObject("default_camera").getPosition().less(velocidadeCamera, 0f) );
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.W, ()-> 
			getObject("default_camera").getPosition().less(0f, velocidadeCamera) );
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.S, ()-> 
			getObject("default_camera").getPosition().plus(0f, velocidadeCamera) );
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.SPACE, ()-> 
			getObject("default_camera").getPosition().setPosition(0f, 0f) );
    }
}
