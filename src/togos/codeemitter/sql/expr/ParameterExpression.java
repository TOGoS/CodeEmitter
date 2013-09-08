package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

class ParameterExpression extends StringBaseQueryExpression
{
	public final String parameterName;
	
	public ParameterExpression( String parameterName ) {
		this.parameterName = parameterName;
	}
	
	public String toSql( QueryQuoter quoter ) {
		return quoter.quoteParameter(parameterName);
	}
}
