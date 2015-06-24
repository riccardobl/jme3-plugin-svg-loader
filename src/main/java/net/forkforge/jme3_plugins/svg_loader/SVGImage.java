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
