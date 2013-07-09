package togos.codeemitter.json;

import togos.asyncstream.StreamDestination;
import togos.codeemitter.TextWriter;

public class JSONEmitter
{
	public final TextWriter tw;
	public JSONEmitter( TextWriter tw ) {
		this.tw = tw;
	}
	public JSONEmitter( StreamDestination<char[]> dest ) {
		this( new TextWriter(dest) );
	}
	
	static enum State {
		NONE, OBJECT_OPENED, ITEMS_WRITTEN
	}
	
	int depth = 0;
	State state = State.NONE;
	
	// Call before writing each item in a list or key in a map
	public void preItem() throws Exception {
		switch( state ) {
		case NONE:
			throw new Exception("Can't open items from 'none' state!  (Must have opened a list or map)");
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
	
	public void open( String delimiter ) throws Exception {
		tw.write(delimiter);
		state = State.OBJECT_OPENED;
		++depth;
	}
	
	public void close( String delimiter ) throws Exception {
		switch( state ) {
		case NONE:
			throw new Exception("Can't go closing here; nothing opened");
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
	
	public void literal(String l) throws Exception {
		tw.write(l);
	}
	
	public void item( Object value ) throws Exception {
		preItem();
		value( value );
	}
	
	public void mapsTo() throws Exception {
		tw.write(": ");
	}
	
	public void openMapping( String key ) throws Exception {
		preItem();
		string( key );
		mapsTo();
	}
	
	public void mapping( String key, Object value ) throws Exception {
		preItem();
		string( key );
		mapsTo();
		value( value );
	}
	
	public void openList() throws Exception { open("["); }
	public void closeList() throws Exception { close("]"); }
	public void openMap() throws Exception { open("{"); }
	public void closeMap() throws Exception { close("}"); }
	
	public void string( String s ) throws Exception {
		// TODO: Make better
		tw.write('"' + s.replace("\\","\\\\").replace("\"", "\\\"") + '"');
	}
	
	public void number( Number n ) throws Exception {
		literal( n.toString() );
	}
	
	public void bool( boolean b ) throws Exception {
		literal( Boolean.toString(b) );
	}
	
	public void value( Object value ) throws Exception {
		if( value instanceof String ) {
			string( (String)value );
		} else if( value instanceof Number ) {
			number( (Number)value );
		} else if( value instanceof Boolean ) {
			bool( (Boolean)value );
		} else {
			throw new Exception("Don't know how to encode scalar "+value.getClass()+" as JSON");
		}
	}
}
