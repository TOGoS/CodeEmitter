package togos.codeemitter.sql;

public interface SQLQuoter
{
	public String quoteIdentifier( String ident );
	public String quoteText( String ident );
}
