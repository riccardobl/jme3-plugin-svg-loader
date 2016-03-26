# SVG Loader
An asset loader for jmonkeyengine that loads svg images with apache batik.

##Gradle
[https://jitpack.io/#riccardobl/jme3-plugin-svg-loader](https://jitpack.io/#riccardobl/jme3-plugin-svg-loader)

##Usage
```
  SVGLoader.init(assetManager);
  SVGImage svg=(SVGImage)assetManager.loadAsset("awesome_tiger.svg");
  com.jme3.texture.Image img=svg.getRaster(new Vector2f(x_dimension,y_dimension));
  svg.finalize();
```
