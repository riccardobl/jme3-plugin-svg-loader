package net.forkforge.jme3_plugins.svg_loader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import com.jme3.math.Vector2f;
import com.jme3.texture.Image;
import com.jme3.texture.plugins.AWTLoader;

public class SVGImage{
	protected static class BufferedOut extends TranscoderOutput{public BufferedImage IMG;}
	public static AWTLoader AWT=new AWTLoader();
	protected byte[] SVG;
	protected ImageTranscoder CONVERTER;

	public SVGImage(byte[] svg){
		SVG=svg;
		CONVERTER=new ImageTranscoder(){
			@Override
			public BufferedImage createImage(int w, int h) {
				return new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
			}
			@Override
			public void writeImage(BufferedImage img, TranscoderOutput out) throws TranscoderException {
				((BufferedOut)out).IMG=img;
			}
		};
	}
	
	public void finalize() throws Throwable{
		SVG=null;
		CONVERTER=null;
		super.finalize();
	}

	public BufferedImage getBufferedImage(Vector2f size) throws TranscoderException {
		ByteArrayInputStream bais=new ByteArrayInputStream(SVG);
		BufferedOut out=new BufferedOut();
		try{
			TranscoderInput svg=new TranscoderInput(bais);
			if(size!=null){
				CONVERTER.addTranscodingHint(ImageTranscoder.KEY_WIDTH,size.x);
				CONVERTER.addTranscodingHint(ImageTranscoder.KEY_HEIGHT,size.y);
			}
			CONVERTER.transcode(svg,out);
		}finally{
			try{
				bais.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return out.IMG;
	}

	public Image getRaster() throws TranscoderException {
		return getRaster(null);
	}

	public Image getRaster(Vector2f size) throws TranscoderException {
		return AWT.load(getBufferedImage(size),true);
	}
}
