package togos.codeemitter.php;

import java.util.List;

import togos.asyncstream.BaseCharStreamSource;
import togos.codeemitter.ExpressionEmitter;
import togos.codeemitter.TextWriter;
import togos.codeemitter.structure.ClassDefinition;
import togos.codeemitter.structure.FieldDefinition;
import togos.codeemitter.structure.LongName;
import togos.codeemitter.structure.MemberVisibility;
import togos.lang.CompileError;
import togos.lang.SourceLocation;

public class PHPEmitter extends BaseCharStreamSource implements ExpressionEmitter<Exception>
{
	public final TextWriter textWriter = new TextWriter(this.getSourceOutputAsAppendable());
	
	@Override
	public void emitScalarLiteral(Object v, SourceLocation sLoc) throws Exception {
		if( v == null ) {
			textWriter.write("null");
		} else if( v == Boolean.FALSE ) {
			textWriter.write("false");
		} else if( v == Boolean.TRUE ) {
			textWriter.write("true");
		} else if( v instanceof Number ) {
			textWriter.write(v.toString());
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
	
	protected void emitPrimaryConstructor( List<FieldDefinition> fields ) throws Exception {
		textWriter.startLine("public function __construct(");
		boolean first = true;
		for( FieldDefinition def : fields ) {
			if( !first ) textWriter.write(", ");
			textWriter.write("$"+def.name);
			first = false;
		}
		textWriter.endLine(") {");
		textWriter.indentMore();
		for( FieldDefinition def : fields ) {
			textWriter.writeLine("$this->"+def.name+" = $"+def.name);
		}
		textWriter.endIndentedBlock("}");
	}
	
	protected void emitClassBody( ClassDefinition cd ) throws Exception {
		for( FieldDefinition fd : cd.instanceFields ) {
			textWriter.writeLine( visibilityModifier(fd.visibility)+" $"+fd.name+";");
		}
		if( cd.primaryConstructorInitializedFields.size() > 0 ) {
			textWriter.giveTheNextLineSomeSpace();
			emitPrimaryConstructor(cd.primaryConstructorInitializedFields);
		}
	}
	
	public void emitClass( ClassDefinition cd ) throws Exception {
		textWriter.writeLine("class " + phpClassName(cd.name));
		textWriter.startIndentedBlock("{");
		textWriter.thatWasASpacerLine();
		emitClassBody(cd);
		textWriter.endIndentedBlock("}");
	}
	
	public void emitOpenTagLine() throws Exception {
		textWriter.writeLine("<?php");
	}
}
