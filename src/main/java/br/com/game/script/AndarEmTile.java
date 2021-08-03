package br.com.game.script;

import br.com.engine.componentes.SimpleComponent;
import br.com.engine.core.Vector2;

public class AndarEmTile extends SimpleComponent
{
	private Vector2 pontoa;
	private Vector2 velocidade;
	
	private Vector2 atual;
	
	boolean andando = false;
	
	float x, y;
	
	@Override
	public void setup( )
	{
		
	}

	public void mover( Vector2 pontoa, Vector2 velocidade )
	{
		if( !andando || 
				( this.pontoa !=  null && !this.pontoa.equals( pontoa ) ) )
		{
			this.pontoa = pontoa;
			this.velocidade = new Vector2( velocidade.x, velocidade.y );
		}
	}
	
	@Override
	public void update( long time ) 
	{
		if( pontoa != null )
		{
			if( !andando )
			{
				Vector2 diff = pontoa.diff( getParent().getPosition() );
				
				if( diff.x != 0 || diff.y != 0 )
				{
					andando = true;
					
					atual = new Vector2( diff.x, diff.y );
					
					if( atual.x < 0 )
						velocidade.x = velocidade.x*-1;
					if( atual.x == 0 )
						velocidade.x = 0;
					if( Math.abs( atual.x ) < Math.abs( velocidade.x ) )
						velocidade.x = atual.x;
					
					if( atual.y < 0 )
						velocidade.y = velocidade.y*-1;
					if( atual.y == 0 )
						velocidade.y = 0;
					if( Math.abs( atual.y ) < Math.abs( velocidade.y ) )
						velocidade.y = atual.y;
				}
			}

			if( andando && atual != null )
			{
				getParent( ).getPosition( ).plus( velocidade );
				
				atual.x = atual.x - velocidade.x;
				atual.y = atual.y - velocidade.y;
				
				if(atual.x <=0 && atual.y<=0){
					andando = false;
					pontoa = null;
				}
				
				if( atual.x <= 0 )
					velocidade.x = 0;
				
				if( atual.y <= 0 )
					velocidade.y = 0;
			}
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
	}
}