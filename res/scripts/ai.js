var Vector = Java.type( "br.com.engine.Vector2" );

var velocidade = new Vector( 3, 1 );

function update( time )
{
	if( gameObject.getPosition( ).getX( ) >= screen.getWidth( ) - (gameObject.getComponent( spriteClass ).getWidth( ) / 2) ||
		gameObject.getPosition( ).getX( ) < 0 )
	{	
		velocidade.reverseX( ); 
	}
	
	if( gameObject.getPosition( ).getY( ) >= screen.getHeight( ) - (gameObject.getComponent( spriteClass ).getHeight( ) / 2) ||
		gameObject.getPosition( ).getY( ) < 0 )
	{
		velocidade.reverseY( );
	}
		
	gameObject.getPosition( ).plus( velocidade );
}