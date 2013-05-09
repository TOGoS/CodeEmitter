package togos.codeemitter.sql;

import togos.asyncstream.BaseStreamSource;
import togos.codeemitter.ExpressionEmitter;
import togos.codeemitter.TextWriter;
import togos.codeemitter.structure.rdb.AutoIncrementExpression;
import togos.codeemitter.structure.rdb.ColumnDefinition;
import togos.codeemitter.structure.rdb.ForeignKeyConstraint;
import togos.codeemitter.structure.rdb.IndexDefinition;
import togos.codeemitter.structure.rdb.TableDefinition;
import togos.lang.CompileError;
import togos.lang.SourceLocation;

public class SQLEmitter extends BaseStreamSource<char[]> implements ExpressionEmitter<Exception>
{
	TextWriter w = new TextWriter(this.asDestination());
	
	protected static String doubleCharEscape( String text, char c ) {
		return text.replace( ""+c, ""+c+c );
	}
	
	public String quoteIdentifier( String ident ) {
		return '"' + doubleCharEscape(ident, '"') + '"';
	}
	
	public String quoteText( String ident ) {
		return "'" + doubleCharEscape(ident, '\'') + "'";
	}
	
	protected String emitAutoIncrementColumnModifier() {
		throw new UnsupportedOperationException();
	}
	
	public void emitColumnDefinition( ColumnDefinition cd ) throws Exception {
		w.write(quoteIdentifier(cd.name)+" "+cd.type);
		if( cd.defaultValue == AutoIncrementExpression.INSTANCE ) {
			emitAutoIncrementColumnModifier();
		} else if( cd.defaultValue != null ) {
			w.write(" DEFAULT ");
			cd.defaultValue.emit(this);
		}
		if( !cd.nullable ) {
			w.write(" NOT NULL");
		}
	}
	
	public void emitIndexDefinition( IndexDefinition id ) throws Exception {
		w.write("INDEX ");
		if( id.name != null ) {
			w.write(quoteIdentifier(id.name));
			w.write(" ");
		}
		w.write("(");
		boolean nc = false;
		for( String cn : id.indexedColumnNames ) {
			if( nc ) w.write(", ");
			w.write( quoteIdentifier(cn) );
			nc = true;
		}
		w.write(")");
	}
	
	public void emitForeignKeyConstraint( ForeignKeyConstraint fkc ) throws Exception {
		w.write("FOREIGN KEY (");
		boolean nc = false;
		for( String cn : fkc.localColumnNames ) {
			if( nc ) w.write(", ");
			w.write( quoteIdentifier(cn) );
			nc = true;
		}
		w.write(") REFERENCES ");
		w.write(quoteIdentifier(fkc.foreignTableName));
		w.write(" (");
		nc = false;
		for( String cn : fkc.foreignColumnNames ) {
			if( nc ) w.write(", ");
			w.write( quoteIdentifier(cn) );
			nc = true;
		}
		w.write(")");
	}
	
	protected void getReadyToEmitAComponent( boolean anyComponentsAlreadyEmitted ) throws Exception {
		w.endLine(anyComponentsAlreadyEmitted ? "," : "");
		w.startLine();
	}
	
	public void emitTableCreation( TableDefinition td ) throws Exception {
		w.write("CREATE TABLE "+quoteIdentifier(td.name) + " (");
		w.indentMore();
		boolean anyComponentsEmitted = false;
		for( ColumnDefinition cd : td.columns ) {
			getReadyToEmitAComponent( anyComponentsEmitted );
			emitColumnDefinition(cd);
			anyComponentsEmitted = true;
		}
		if( td.primaryKeyColumnNames.size() > 0 ) {
			getReadyToEmitAComponent( anyComponentsEmitted );
			w.write("PRIMARY KEY (");
			boolean nc = false;
			for( String pkcn : td.primaryKeyColumnNames ) {
				if( nc ) w.write(", ");
				w.write(quoteIdentifier(pkcn));
				nc = true;
			}
			w.write(")");
			anyComponentsEmitted = true;
		}
		for( IndexDefinition id : td.indexes ) {
			getReadyToEmitAComponent( anyComponentsEmitted );
			emitIndexDefinition(id);
			anyComponentsEmitted = true;
		}
		for( ForeignKeyConstraint fkc : td.foreignKeyConstraints ) {
			getReadyToEmitAComponent( anyComponentsEmitted );
			emitForeignKeyConstraint(fkc);
			anyComponentsEmitted = true;
		}
		
		if( anyComponentsEmitted ) {
			w.endLine();
			w.indentLess();
			w.writeLine(");");
		} else {
			w.indentLess();
			w.endLine(");");
		}
	}

	@Override
	public void emitScalarLiteral(Object v, SourceLocation sLoc) throws Exception {
		if( v == null ) {
			w.write("NULL");
		} else if( v == Boolean.FALSE ) {
			w.write("FALSE");
		} else if( v == Boolean.TRUE ) {
			w.write("TRUE");
		} else if( v instanceof Number ) {
			w.write(v.toString());
		} else if( v instanceof String ) {
			w.write(quoteText((String)v));
		} else {
			throw new CompileError("Can't encode "+v+" as a scalar literal", sLoc);
		}
	}
}
