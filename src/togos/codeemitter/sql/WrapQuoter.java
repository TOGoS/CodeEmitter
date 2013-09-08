package togos.codeemitter.sql;

public class WrapQuoter implements QueryQuoter
{
	SQLQuoter sqlQuoter;
	String preParam, postParam;
	
	public WrapQuoter( SQLQuoter sqlQuoter, String preParam, String postParam ) {
		this.sqlQuoter = sqlQuoter;
		this.preParam = preParam;
		this.postParam = postParam;
	}
	
	@Override public String quoteIdentifier(String ident) {
		return sqlQuoter.quoteIdentifier(ident);
	}
	
	@Override public String quoteText(String text) {
		return sqlQuoter.quoteText(text);
	}
	
	@Override public String quoteParameter(String name) {
		return preParam + name + postParam;
	}
}
