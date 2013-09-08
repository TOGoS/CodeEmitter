package togos.codeemitter.sql.expr;

public class Expressions
{
	public static QueryExpression ident( String identifier ) {
		return new SQLIdentifierExpression(identifier);
	}
	
	public static QueryExpression str( String text ) {
		return new LiteralStringExpression(text);
	}
	
	public static QueryExpression num( int number ) {
		return new LiteralNumberExpression(number);
	}
	
	public static QueryExpression cv( String tableAlias, String columnName ) {
		return new ColumnValueExpression( ident(tableAlias), columnName);
	}

	public static QueryExpression infix( QueryExpression e1, String op, QueryExpression e2 ) {
		return new InfixOperatorExpression(e1, op, e2);
	}
	
	public static QueryExpression fcall( String funcName, QueryExpression...args ) {
		return new FunctionCallExpression( funcName, args );
	}
	
	public static QueryExpression equal( QueryExpression e1, QueryExpression e2 ) {
		return infix(e1, "=", e2);
	}
	
	public static QueryExpression columnsEqual( String alias1, String column1, String alias2, String column2 ) {
		return equal( cv(alias1,  column1), cv(alias2,  column2) );
	}
	
	public static QueryExpression columnsEqual( String alias1, String alias2, String sharedColumn ) {
		return equal( cv(alias1,  sharedColumn), cv(alias2,  sharedColumn) );
	}
}
