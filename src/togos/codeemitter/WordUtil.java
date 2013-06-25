package togos.codeemitter;

public class WordUtil
{
	/**
	 * Transform certain words that need to be treated specially for
	 * camel/pascal-casification.
	 */
	protected static final String deweird( String phrase ) {
		return phrase.replace("e-mail", "email");
	}
	
	protected static final String toPascalCase( String[] words ) {
		String r = "";
		for( String word : words ) {
			if( word.length() > 0 ) {
				r += word.substring(0,1).toUpperCase() + word.substring(1);
			}
		}
		return r;
	}
	
	public static final String toPascalCase( String phrase ) {
		phrase = deweird(phrase);
		return toPascalCase( phrase.split("[ -]") );
	}
	
	protected static final String toCamelCase( String[] words ) {
		String r = "";
		for( String word : words ) {
			if( word.length() > 0 ) {
				String w = word.toLowerCase();
				if( r.length() == 0 ) {
					r += w;
				} else {
					r += w.substring(0,1).toUpperCase() + w.substring(1);
				}
			}
		}
		return r;
	}
	
	public static final String toCamelCase( String phrase ) {
		phrase = deweird(phrase);
		return toCamelCase( phrase.split("[ -]") );
	}
	
	public static String phpQuote(String text) {
		if( text.contains("\n") || text.contains("\t") ) {
			return '"' + text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n","\\n").replace("\t","\\t").replace("\r","\\r")+'"';	
		} else {
			return "'" + text.replace("\\", "\\\\").replace("'", "\\'")+"'";
		}
	}
	
	public static String jsQuote(String text) {
		return '"' + text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n","\\n").replace("\t","\\t").replace("\r","\\r")+'"';
	}
	
	public static String join( String separator, Object[] parts ) {
		String res = "";
		for( Object p : parts ) {
			if( res.length() > 0 ) res += separator;
			res += p.toString();
		}
		return res;
	}
	
	public static String toDashSeparated( String[] words ) {
		return join( "-", words );
	}
	
	public static String toDashSeparated(String phrase) {
		phrase = deweird(phrase);
		return toDashSeparated( phrase.split("[ -]") );
	}

	public static String pluralize(String phrase) {
		if( phrase.endsWith("is") ) {
			// E.g. 'diagnosis'
			return phrase.substring(0,phrase.length()-2)+"es";
		} else if( phrase.endsWith("y") ) {
			return phrase.substring(0,phrase.length()-1)+"ies";
		} else if( phrase.endsWith("s") ) {
			return phrase + "es";
		} else {
			return phrase + "s";
		}
	}
	
	public static String indent( String indent, String source ) {
		return indent + source.replace("\n", "\n"+indent);
	}
}
