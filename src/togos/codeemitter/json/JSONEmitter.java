package togos.codeemitter.json;

import java.io.IOException;

import togos.codeemitter.InvalidEmittanceException;
import togos.codeemitter.TextWriter;

public class JSONEmitter
{
	public final TextWriter tw;
	public JSONEmitter( TextWriter tw ) {
		this.tw = tw;
	}
	public JSONEmitter( Appendable dest ) {
		this( new TextWriter(dest) );
	}
	
	static enum State {
		NONE, OBJECT_OPENED, ITEMS_WRITTEN
	}
	
	int depth = 0;
	State state = State.NONE;
	
	// Call before writing each item in a list or key in a map
	public void preItem() throws IOException {
		switch( state ) {
		case NONE:
			throw new InvalidEmittanceException("Can't open items from 'none' state!  (Must have opened a list or map)");
		case ITEMS_WRITTEN:
			tw.write(",");
			tw.endLine();
			tw.startLine();
			break;
		case OBJECT_OPENED:
			tw.endLine();
			tw.indentMore();
			tw.startLine();
			break;
		}
		state = State.ITEMS_WRITTEN;
	};
	
	public void open( String delimiter ) throws IOException {
		tw.write(delimiter);
		state = State.OBJECT_OPENED;
		++depth;
	}
	
	public void close( String delimiter ) throws IOException {
		switch( state ) {
		case NONE:
			throw new InvalidEmittanceException("Can't go closing here; nothing opened");
		case ITEMS_WRITTEN:
			tw.endLine();
			tw.indentLess();
			tw.startLine();
			// Fall through!
		case OBJECT_OPENED:
			tw.write(delimiter);
		}
		--depth;
		state = State.ITEMS_WRITTEN;
	}
	
	public void literal(String l) throws IOException {
		tw.write(l);
	}
	
	public void item( Object value ) throws IOException {
		preItem();
		value( value );
	}
	
	public void mapsTo() throws IOException {
		tw.write(": ");
	}
	
	public void openMapping( String key ) throws IOException {
		preItem();
		string( key );
		mapsTo();
	}
	
	public void mapping( String key, Object value ) throws IOException {
		preItem();
		string( key );
		mapsTo();
		value( value );
	}
	
	public void openList() throws IOException { open("["); }
	public void closeList() throws IOException { close("]"); }
	public void openMap() throws IOException { open("{"); }
	public void closeMap() throws IOException { close("}"); }
	
	public void string( String s ) throws IOException {
		// TODO: Make better
		tw.write('"' + s.replace("\\","\\\\").replace("\"", "\\\"") + '"');
	}
	
	public void number( Number n ) throws IOException {
		literal( n.toString() );
	}
	
	public void bool( boolean b ) throws IOException {
		literal( Boolean.toString(b) );
	}
	
	public void value( Object value ) throws IOException {
		if( value instanceof String ) {
			string( (String)value );
		} else if( value instanceof Number ) {
			number( (Number)value );
		} else if( value instanceof Boolean ) {
			bool( (Boolean)value );
		} else {
			throw new InvalidEmittanceException("Don't know how to encode scalar "+value.getClass()+" as JSON");
		}
	}
}
