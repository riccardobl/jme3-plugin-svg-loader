# SVG Loader
An asset loader for jmonkeyengine that loads svg images.

##Download 
Latest release: [Version 1.0](https://bintray.com/artifact/download/riccardo/jme3-plugins/net/forkforge/jme3_plugins/svg_loader/1.0/svg_loader-1.0.jar)

Dependencies: [Apache Batik](http://apache.panu.it/xmlgraphics/batik/binaries/)

##Maven
```
<repository>
  <snapshots>
    <enabled>false</enabled>
  </snapshots>
  <id>bintray-riccardo-jme3-plugins</id>
  <name>bintray-riccardo-jme3-plugins</name>
  <url>http://dl.bintray.com/riccardo/jme3-plugins</url>
</repository>
```

```
<dependency>
  <groupId>net.forkforge.jme3_plugins</groupId>
  <artifactId>svg_loader</artifactId>
  <version>1.0</version>
  <type>jar</type>
</dependency>
```

##Usage
```
  SVGLoader.init(assetManager);
  SVGImage svg=(SVGImage)assetManager.loadAsset("awesome_tiger.svg");
  com.jme3.texture.Image img=svg.getRaster(new Vector2f(x_dimension,y_dimension));
```
