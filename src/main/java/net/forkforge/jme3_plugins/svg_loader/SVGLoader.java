package net.forkforge.jme3_plugins.svg_loader;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;

public class SVGLoader implements AssetLoader{
	public static void init(AssetManager assetManager) {
		assetManager.registerLoader(SVGLoader.class,"svg");
	}

	public Object load(AssetInfo assetInfo) throws IOException {
		InputStream is=assetInfo.openStream();
		try{
			byte bytes[]=IOUtils.toByteArray(is);
			return new SVGImage(bytes);
		}finally{
			is.close();
		}
	}
}