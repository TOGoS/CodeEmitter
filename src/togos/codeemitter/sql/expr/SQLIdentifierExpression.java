package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

public class SQLIdentifierExpression extends StringBaseQueryExpression
{
	protected final String identifier;
	
	public SQLIdentifierExpression( String identifier ) {
		this.identifier = identifier;
	}
	
	@Override public String toSql(QueryQuoter quoter) {
		return quoter.quoteIdentifier(identifier);
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
