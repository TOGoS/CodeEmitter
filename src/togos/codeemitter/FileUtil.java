package togos.codeemitter;

import java.io.File;

public class FileUtil
{
	public static void mkParentDirs( File f ) {
		File p = f.getParentFile();
		if( p != null && !p.exists() ) p.mkdirs();
	}
}
