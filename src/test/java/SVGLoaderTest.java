/**
 * @name SVGLoader
 * @version 1.0
 * @author Riccardo Balbo
 * @license
 * 	This is free and unencumbered software released into the public domain.
 * 	Anyone is free to copy, modify, publish, use, compile, sell, or
 * 	distribute this software, either in source code form or as a compiled
 * 	binary, for any purpose, commercial or non-commercial, and by any
 * 	means.
 * 	In jurisdictions that recognize copyright laws, the author or authors
 * 	of this software dedicate any and all copyright interest in the
 * 	software to the public domain. We make this dedication for the benefit
 * 	of the public at large and to the detriment of our heirs and
 * 	successors. We intend this dedication to be an overt act of
 * 	relinquishment in perpetuity of all present and future rights to this
 * 	software under copyright law.
 * 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * 	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * 	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * 	IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * 	OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * 	ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * 	OTHER DEALINGS IN THE SOFTWARE.
 * 	For more information, please refer to <http://unlicense.org>
 */

import net.forkforge.jme3_plugins.svg_loader.SVGImage;
import net.forkforge.jme3_plugins.svg_loader.SVGLoader;

import org.apache.batik.transcoder.TranscoderException;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;

public class SVGLoaderTest extends SimpleApplication{
	public static void main(String[] args) {
		new SVGLoaderTest().start();
	}

	@Override
	public void simpleInitApp() {
		SVGLoader.init(assetManager);
		SVGImage svg=(SVGImage)assetManager.loadAsset("awesome_tiger.svg");

		try{
			addInstance(svg,64,0,0);
			addInstance(svg,128,64,0);
			addInstance(svg,512,192,0);
		}catch(TranscoderException e){
			e.printStackTrace();
		}
		
		try{
			svg.finalize();
		}catch(Throwable e){
			e.printStackTrace();
		}
		
	}
	
	private void addInstance(SVGImage svg,int l,int x,int y) throws TranscoderException{
		Picture p1=new Picture("Test1");
		Image img=svg.getRaster(new Vector2f(l,l));
		p1.setWidth(l);
		p1.setHeight(l);
		Texture2D texture=new Texture2D(img);
		p1.setTexture(assetManager,texture,true);
		guiNode.attachChild(p1);
		p1.setPosition(x,settings.getHeight()-l-y);
	}
}
