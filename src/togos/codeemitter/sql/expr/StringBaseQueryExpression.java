package togos.codeemitter.sql.expr;

import java.io.IOException;

import togos.codeemitter.sql.QueryQuoter;

public abstract class StringBaseQueryExpression implements QueryExpression
{
	@Override public void toSql(QueryQuoter quoter, Appendable dest) throws IOException {
		dest.append(toSql(quoter));
	}
}
