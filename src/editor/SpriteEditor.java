package editor;

import java.util.List;
import java.util.stream.Collectors;

import br.com.engine.componentes.builders.ScriptBuilder;
import br.com.engine.componentes.builders.SpriteFontBuilder;
import br.com.engine.componentes.drawable.Cube;
import br.com.engine.componentes.drawable.SpriteFont;
import br.com.engine.core.GameObject;
import br.com.engine.core.Scene;
import br.com.engine.input.KeyBoard;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SpriteEditor extends Scene
{
	long tempoPassado = 0;

	private GameObject selecionado;
	
	boolean h, w;
	
	@Override
	public void setup() 
	{
		super.setup();
		
		add( new SpriteFontBuilder()
			.setColor( Color.RED )
			.setSize( 12 )
			.setText( " * Add Quadrado - Aperte 'A' \n * Setas para Mover Objetos selecionados \n * Objetos selecionados s�o amarelos." )
			.build( ) );
		
		GameObject scala = new SpriteFontBuilder()
			.setColor( Color.RED )
			.setSize( 12 )
			.setText( " Scala: " )
			.build( );
		scala.getPosition().setPosition(0, 50);
		add( scala );
		
		GameObject heigth = new SpriteFontBuilder()
				.setColor( Color.BLUE )
				.setTag( "heigth" )
				.setSize( 12 )
				.setText( " H" )
				.build( );
		heigth.getPosition().setPosition( 40, 50 );
		
		heigth.addComponente( ScriptBuilder.createNoTime( ()->{
			KeyBoard.infInstace().ifKeyPressed( KeyCode.H, ()-> {
				if( h )
					heigth.getComponent( SpriteFont.class ).setColor( Color.YELLOW );
				else
					heigth.getComponent( SpriteFont.class ).setColor( Color.BLUE );
				
				h = !h;
			});
		}));
		
		add( heigth );
		
		GameObject width = new SpriteFontBuilder()
				.setColor( Color.BLUE )
				.setTag( "width" )
				.setSize( 12 )
				.setText( " W" )
				.build( );
		width.getPosition().setPosition( 55, 50);
		
		width.addComponente( ScriptBuilder.createNoTime( ()->{
			KeyBoard.infInstace().ifKeyPressed( KeyCode.W, ()-> {
				if( w )
					width.getComponent( SpriteFont.class ).setColor( Color.YELLOW );
				else
					width.getComponent( SpriteFont.class ).setColor( Color.BLUE );
				
				w = !w;
			});
		}));
		
		add( width );
	}
	
	@Override
	public void update(long time)
	{
		super.update(time);
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.UP, ()-> selecionado.getPosition().less( 0, 3 ) );
		KeyBoard.infInstace().ifKeyPressed( KeyCode.DOWN, ()-> selecionado.getPosition().plus( 0, 3 ) );
		KeyBoard.infInstace().ifKeyPressed( KeyCode.LEFT, ()-> selecionado.getPosition().less( 3, 0 ) );
		KeyBoard.infInstace().ifKeyPressed( KeyCode.RIGHT, ()-> selecionado.getPosition().plus( 3, 0 ) );
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.A, ()->
		{
			if( tempoPassado > 100 )
			{
				criarQuadrado( );
				tempoPassado = 0;
			}
		});
		
		KeyBoard.infInstace().ifKeyPressed( KeyCode.SPACE, ()->
		{
			if( tempoPassado > 100 )
			{
				alteraQuadradoSelecionado( );
				tempoPassado = 0;
			}
		});
		
		tempoPassado += time;
	}

	private void alteraQuadradoSelecionado( )
	{
		List<GameObject> obterquadrados = obterquadrados();
		
		for( int i = 0; i < obterquadrados.size( ); i++ )
		{
			if( selecionado == null )
			{
				selecionado = obterquadrados.get( i );
				
				break;
			}
			else if( selecionado == obterquadrados.get( i ) )
			{
				if( i + 1 == obterquadrados.size( ) )
				{
					selecionado = obterquadrados.get( 0 );
				}
				else
				{
					selecionado = obterquadrados.get( i + 1 );
				}
				
				break;
			}
		}
		
		obterquadrados().forEach( obj -> obj.getComponent( Cube.class ).setColor( "blue" ) );
		
		selecionado.getComponent( Cube.class ).setColor( "yellow" );
	}

	private void criarQuadrado( )
	{
		GameObject go = new GameObject( "quad_"+ obterquadrados().size() );
		go.addComponente( new Cube( 100, 100, "blue" ) );
		add( go );
		
		if( selecionado == null )
		{
			selecionado = go;
		}
	}

	private List<GameObject> obterquadrados() {
		return getNode().stream().filter( it -> it.getTag().startsWith( "quad_" )).collect( Collectors.toList(  ));
	}
}