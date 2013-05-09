package togos.asyncstream;

import java.io.StringWriter;

public class StringCollector implements StreamDestination<char[]>
{
	final StringWriter sw = new StringWriter();
	
	@Override
	public void data(char[] value) throws Exception {
		sw.write(value);
	}
	
	@Override
	public void end() throws Exception {
	}
	
	public String toString() {
		return sw.toString();
	}
}
