import net.forkforge.jme3_plugins.svg_loader.SVGImage;
import net.forkforge.jme3_plugins.svg_loader.SVGLoader;

import org.apache.batik.transcoder.TranscoderException;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
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
		SVGImage svg=(SVGImage)assetManager.loadAsset("publicdomain.loopyasiandragonframe.svg");

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
		this.getViewPort().setBackgroundColor(ColorRGBA.White);
		
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
