package togos.codeemitter.php;

import java.io.IOException;
import java.util.List;

import togos.codeemitter.ExpressionEmitter;
import togos.codeemitter.TextWriter;
import togos.codeemitter.structure.ClassDefinition;
import togos.codeemitter.structure.FieldDefinition;
import togos.codeemitter.structure.LongName;
import togos.codeemitter.structure.MemberVisibility;
import togos.lang.CompileError;
import togos.lang.SourceLocation;

public class PHPEmitter implements ExpressionEmitter<Exception>
{
	final TextWriter w;
	public PHPEmitter( TextWriter w ) {
		this.w = w;
	}
	
	@Override
	public void emitScalarLiteral(Object v, SourceLocation sLoc) throws Exception {
		if( v == null ) {
			w.write("null");
		} else if( v == Boolean.FALSE ) {
			w.write("false");
		} else if( v == Boolean.TRUE ) {
			w.write("true");
		} else {
			throw new CompileError("Can't encode "+v+" as a scalar literal", sLoc);
		}
	}
	
	String phpClassName( LongName cn ) {
		String n = "";
		for( int i=0; i<cn.parts.length; ++i ) {
			if( n.length() > 0 ) n += "_";
			n += cn.parts[i];
		}
		return n;
	}
	
	protected String visibilityModifier( MemberVisibility vis ) {
		switch( vis ) {
		case PRIVATE: return "private";
		case PUBLIC: return "public";
		case PROTECTED: return "protected";
		default: throw new RuntimeException(vis+" visibility not supported");
		}
	}
	
	protected void emitPrimaryConstructor( List<FieldDefinition> fields ) throws IOException {
		w.startLine("public function __construct(");
		boolean first = true;
		for( FieldDefinition def : fields ) {
			if( !first ) w.write(", ");
			w.write("$"+def.name);
			first = false;
		}
		w.startIndentedBlock(") {");
		for( FieldDefinition def : fields ) {
			w.writeLine("$this->"+def.name+" = $"+def.name);
		}
		w.endIndentedBlock("}");
	}
	
	protected void emitClassBody( ClassDefinition cd ) throws IOException {
		for( FieldDefinition fd : cd.instanceFields ) {
			w.writeLine( visibilityModifier(fd.visibility)+" $"+fd.name+";");
		}
		if( cd.primaryConstructorInitializedFields.size() > 0 ) {
			w.giveTheNextLineSomeSpace();
			emitPrimaryConstructor(cd.primaryConstructorInitializedFields);
		}
	}
	
	public void emitClass( ClassDefinition cd ) throws IOException {
		w.writeLine("class " + phpClassName(cd.name));
		w.startIndentedBlock("{");
		w.thatWasASpacerLine();
		emitClassBody(cd);
		w.endIndentedBlock("}");
	}
}
