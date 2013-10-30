package togos.codeemitter;

import java.io.IOException;

public class TextWriter
{
	final Appendable dest;
	public TextWriter( Appendable dest ) {
		this.dest = dest;
	}
	
	static final int SPACER_LINE = 0x01;
	
	protected int previousLineFlags; 
	public int indentLevel = 0;
	public String indentSequence = "\t";
	
	public void write( String text ) throws IOException {
		dest.append(text);
	}
	
	public void writeIndent() throws IOException {
		for( int i=0; i<indentLevel; ++i ) {
			write(indentSequence);
		}
	}
	
	public String correctIndent( String text ) {
		String replacement = "\n";
		for( int i=0; i<indentLevel; ++i ) replacement += indentSequence;
		
		return text.replace("\n", replacement);
	}
	
	public void indentMore() { ++indentLevel; }
	public void indentLess() { --indentLevel; }
	
	public void startLine() throws IOException {
		writeIndent();
	}
	
	public void startLine(String text) throws IOException {
		startLine();
		write(text);
	}
	
	public void endLine() throws IOException {
		write("\n");
	}
	
	public void endLine(String text) throws IOException {
		write(text);
		endLine();
	}
	
	public void writeLine( String text, int flags ) throws IOException {
		startLine();
		write(text);
		endLine();
		previousLineFlags = flags;
	}
	
	public void writeLine( String text ) throws IOException {
		writeLine(text, 0);
	}
	
	public void startIndentedBlock( String delimiter ) throws IOException {
		writeLine( delimiter );
		indentMore();
	}
	
	public void endIndentedBlock( String delimiter ) throws IOException {
		indentLess();
		writeLine( delimiter );
	}
	
	public void giveTheNextLineSomeSpace() throws IOException {
		if( (previousLineFlags & SPACER_LINE) == 0 ) {
			writeLine( "", SPACER_LINE );
		}
	}

	public void thatWasASpacerLine() {
		previousLineFlags |= SPACER_LINE;
	}
}
