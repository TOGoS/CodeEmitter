package togos.codeemitter;

import togos.asyncstream.StreamDestination;

public class TextWriter
{
	final StreamDestination<char[]> dest;
	public TextWriter( StreamDestination<char[]> dest ) {
		this.dest = dest;
	}
	
	static final int SPACER_LINE = 0x01;
	
	protected int previousLineFlags; 
	public int indentLevel = 0;
	public String indentSequence = "\t";
	
	public void write( String text ) throws Exception {
		dest.data(text.toCharArray());
	}
	
	public void writeIndent() throws Exception {
		for( int i=0; i<indentLevel; ++i ) {
			write(indentSequence);
		}
	}
	
	public void indentMore() { ++indentLevel; }
	public void indentLess() { --indentLevel; }
	
	public void startLine() throws Exception {
		writeIndent();
	}
	
	public void startLine(String text) throws Exception {
		startLine();
		write(text);
	}
	
	public void endLine() throws Exception {
		write("\n");
	}
	
	public void endLine(String text) throws Exception {
		write(text);
		endLine();
	}
	
	public void writeLine( String text, int flags ) throws Exception {
		startLine();
		write(text);
		endLine();
		previousLineFlags = flags;
	}
	
	public void writeLine( String text ) throws Exception {
		writeLine(text, 0);
	}

	
	public void startIndentedBlock( String delimiter ) throws Exception {
		writeLine( delimiter );
		indentMore();
	}
	
	public void endIndentedBlock( String delimiter ) throws Exception {
		indentLess();
		writeLine( delimiter );
	}
	
	public void giveTheNextLineSomeSpace() throws Exception {
		if( (previousLineFlags & SPACER_LINE) == 0 ) {
			writeLine( "", SPACER_LINE );
		}
	}

	public void thatWasASpacerLine() {
		previousLineFlags |= SPACER_LINE;
	}
}
