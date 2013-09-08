package togos.codeemitter.sql.expr;

import java.io.IOException;

import togos.codeemitter.sql.QueryQuoter;

public class FunctionCallExpression extends AppendBaseQueryExpression
{
	protected final String functionName;
	protected final QueryExpression[] arguments;
	
	public FunctionCallExpression( String functionName, QueryExpression...args ) {
		this.functionName = functionName;
		this.arguments = args;
	}
	
	@Override public void toSql(QueryQuoter quoter, Appendable dest) throws IOException {
		dest.append(functionName);
		dest.append("(");
		for( int i=0; i<arguments.length; ++i ) {
			if( i > 0 ) dest.append(", ");
			arguments[i].toSql(quoter, dest);
		}
		dest.append(")");
	}
}
