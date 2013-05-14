package togos.asyncstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

public final class StreamUtil
{
	private StreamUtil() { }
	
	public static void pipe( InputStream r, StreamDestination<byte[]> d ) throws Exception {
		byte[] buffer = new byte[1024];
		int i;
		while( (i = r.read(buffer)) > 0 ) {
			d.data( Arrays.copyOf(buffer, i) );
		}
		d.end();
	}
	
	public static void pipe( Reader r, StreamDestination<char[]> d ) throws Exception {
		char[] buffer = new char[1024];
		int i;
		while( (i = r.read(buffer)) > 0 ) {
			d.data( Arrays.copyOf(buffer, i) );
		}
		d.end();
	}
	
	public static void pipe( StreamSource<char[]> source, final Writer w, final boolean closeOnEnd ) {
		source.pipe(new StreamDestination<char[]>() {
			@Override public void data(char[] value) throws IOException {
				w.write(value);
			}
			@Override public void end() throws Exception {
				if( closeOnEnd ) w.close();
			}
		});
	}
}
