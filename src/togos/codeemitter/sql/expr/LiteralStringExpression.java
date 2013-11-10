package togos.codeemitter.sql.expr;

import togos.codeemitter.sql.QueryQuoter;

public class LiteralStringExpression extends StringBaseQueryExpression
{
	String text;
	
	public LiteralStringExpression( String text ) {
		this.text = text;
	}
	
	@Override public String toSql(QueryQuoter quoter) {
		return quoter.quoteText(text);
	}
}
