package togos.codeemitter.sql;

public interface QueryQuoter extends SQLQuoter
{
	public String quoteParameter( String name );
}
