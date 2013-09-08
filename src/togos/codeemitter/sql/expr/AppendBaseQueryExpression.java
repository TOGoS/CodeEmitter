package togos.codeemitter.sql.expr;

import java.io.IOException;

import togos.codeemitter.sql.QueryQuoter;

public abstract class AppendBaseQueryExpression implements QueryExpression
{
	@Override public String toSql(QueryQuoter quoter) {
		StringBuilder sb = new StringBuilder();
		try {
			toSql( quoter, sb );
		} catch( IOException e ) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}
