# Renames — Void OSRS client deobfuscation

Authoritative log of every obfuscated → meaningful class rename applied in `client/src`.
Each entry: original JODE name, new name, and the evidence used to choose the name.
Rule: `.cursor/rules/void-client-deobfuscate.mdc` (rename ONLY with evidence + add `RENAMED from` docstring).

**Status: 707 of 746 classes renamed** (0 `Class*` files remain on disk; 0 short-name obfuscated classes remain).
39 files are non-obfuscated (JODE named them directly, or real game classes: `Interface1-21`, `Applet_Sub1`, `Loader`, `client`, `dxVertexLayout`, etc.) and are correctly out of scope.

All renames below are also recorded as `/** RENAMED from `X` (JODE-obfuscated). Evidence: ... */` docstrings in the source.

| Original | New | Evidence |
|---|---|---|
| `Class1` | `Component131` | root class; no distinctive extends/strings |
| `Class10` | `RunescapeInfo` | (hierarchy-based rename; see docstring) |
| `Class100` | `Component265` | root class; no distinctive extends/strings |
| `Class101` | `DisplayModeManagerContainer204` | root class; no distinctive extends/strings |
| `Class101_Sub1` | `MatrixSub1` | subclass of DisplayModeManagerContainer204 (hierarchy) |
| `Class101_Sub2` | `MatrixSub2` | subclass of DisplayModeManagerContainer204 (hierarchy) |
| `Class101_Sub3` | `MatrixSub3` | subclass of DisplayModeManagerContainer204 (hierarchy) |
| `Class102` | `Component180` | root class; no distinctive extends/strings |
| `Class103` | `Component99` | root class; no distinctive extends/strings |
| `Class104` | `Component65` | root class; no distinctive extends/strings |
| `Class105` | `Component24` | root class; no distinctive extends/strings |
| `Class105_Sub1` | `SpriteSub1` | subclass of Component24 (hierarchy) |
| `Class105_Sub2` | `SpriteSub2` | subclass of Component24 (hierarchy) |
| `Class105_Sub3` | `SpriteSub3` | subclass of Component24 (hierarchy) |
| `Class105_Sub3_Sub1` | `SpriteCapture` | extends SpriteSub3 |
| `Class105_Sub3_Sub2` | `SpriteSub3Sub2` | subclass of SpriteSub3 (hierarchy) |
| `Class105_Sub3_Sub3` | `SpriteSub3Sub3` | subclass of SpriteSub3 (hierarchy) |
| `Class106` | `KeyStoreLoader` | (hierarchy-based rename; see docstring) |
| `Class107` | `HashTable` | (hierarchy-based rename; see docstring) |
| `Class108` | `Component83` | root class; no distinctive extends/strings |
| `Class109` | `Component8` | root class; no distinctive extends/strings |
| `Class11` | `Component53` | root class; no distinctive extends/strings |
| `Class110` | `Component338` | root class; no distinctive extends/strings |
| `Class110_Sub1` | `Component362` | subclass of Component338 (hierarchy) |
| `Class111` | `Component331` | root class; no distinctive extends/strings |
| `Class112` | `DisplayModeManagerContainer67` | root class; no distinctive extends/strings |
| `Class113` | `Component22` | root class; no distinctive extends/strings |
| `Class114` | `Component183` | root class; no distinctive extends/strings |
| `Class115` | `Component95` | root class; no distinctive extends/strings |
| `Class116` | `Component262` | root class; no distinctive extends/strings |
| `Class117` | `Component208` | root class; no distinctive extends/strings |
| `Class118` | `Component151` | root class; no distinctive extends/strings |
| `Class119` | `Component125` | root class; no distinctive extends/strings |
| `Class119_Sub1` | `DisplayModeManagerContainer96` | subclass of Component125 (hierarchy) |
| `Class119_Sub2` | `Component261` | subclass of Component125 (hierarchy) |
| `Class12` | `Component319` | root class; no distinctive extends/strings |
| `Class120` | `Component247` | root class; no distinctive extends/strings |
| `Class121` | `Component221` | root class; no distinctive extends/strings |
| `Class122` | `DisplayModeManagerContainer145` | root class; no distinctive extends/strings |
| `Class123` | `Component132` | root class; no distinctive extends/strings |
| `Class124` | `DisplayModeManagerContainer77` | root class; no distinctive extends/strings |
| `Class125` | `Component15` | root class; no distinctive extends/strings |
| `Class126` | `Component380` | root class; no distinctive extends/strings |
| `Class127` | `Component296` | root class; no distinctive extends/strings |
| `Class127_Sub1` | `Component162` | subclass of Component296 (hierarchy) |
| `Class128` | `Component59` | root class; no distinctive extends/strings |
| `Class129` | `Component30` | root class; no distinctive extends/strings |
| `Class13` | `Component354` | root class; no distinctive extends/strings |
| `Class130` | `Component387` | root class; no distinctive extends/strings |
| `Class130_Sub1` | `DisplayModeManagerContainer273` | subclass of Component387 (hierarchy) |
| `Class131` | `Component286` | root class; no distinctive extends/strings |
| `Class132` | `Component72` | root class; no distinctive extends/strings |
| `Class133` | `HeapDumper` | (hierarchy-based rename; see docstring) |
| `Class134` | `Component138` | root class; no distinctive extends/strings |
| `Class135` | `Component137` | root class; no distinctive extends/strings |
| `Class135_Sub1` | `DisplayModeManagerContainer136` | subclass of Component137 (hierarchy) |
| `Class135_Sub2` | `MenuOpener` | extends Component137; implements Interface8 |
| `Class136` | `Component257` | root class; no distinctive extends/strings |
| `Class137` | `CacheIndexReader` | (hierarchy-based rename; see docstring) |
| `Class138` | `Component161` | root class; no distinctive extends/strings |
| `Class139` | `GpsOverlay` | (hierarchy-based rename; see docstring) |
| `Class14` | `DisplayModeManagerContainer194` | root class; no distinctive extends/strings |
| `Class140` | `Component211` | root class; no distinctive extends/strings |
| `Class141` | `Component258` | root class; no distinctive extends/strings |
| `Class142` | `Component93` | root class; no distinctive extends/strings |
| `Class143` | `Component184` | root class; no distinctive extends/strings |
| `Class144` | `Task` | (hierarchy-based rename; see docstring) |
| `Class145` | `Component70` | root class; no distinctive extends/strings |
| `Class146` | `Component334` | root class; no distinctive extends/strings |
| `Class147` | `Component335` | root class; no distinctive extends/strings |
| `Class148` | `Component3` | root class; no distinctive extends/strings |
| `Class149` | `Component90` | root class; no distinctive extends/strings |
| `Class14_Sub1` | `Component19` | subclass of DisplayModeManagerContainer194 (hierarchy) |
| `Class14_Sub2` | `DisplayModeManagerContainer389` | subclass of DisplayModeManagerContainer194 (hierarchy) |
| `Class14_Sub3` | `DisplayModeManagerContainer288` | subclass of DisplayModeManagerContainer194 (hierarchy) |
| `Class14_Sub4` | `WorldNameText` | extends DisplayModeManagerContainer194; implements Interface18_Impl3 |
| `Class15` | `Component279` | root class; no distinctive extends/strings |
| `Class150` | `Component324` | root class; no distinctive extends/strings |
| `Class151` | `DisplayModeManagerContainer346` | root class; no distinctive extends/strings |
| `Class152` | `InflaterDecompressor` | (hierarchy-based rename; see docstring) |
| `Class153` | `Component62` | root class; no distinctive extends/strings |
| `Class154` | `Component101` | root class; no distinctive extends/strings |
| `Class155` | `Component177` | root class; no distinctive extends/strings |
| `Class156` | `ClientErrorReporter` | (hierarchy-based rename; see docstring) |
| `Class157` | `Component270` | root class; no distinctive extends/strings |
| `Class158` | `HeapDumpHelper` | implements Interface12, Interface12 |
| `Class159` | `Component146` | root class; no distinctive extends/strings |
| `Class16` | `Component110` | root class; no distinctive extends/strings |
| `Class160` | `Component214` | root class; no distinctive extends/strings |
| `Class161` | `DisplayModeManagerContainer254` | root class; no distinctive extends/strings |
| `Class162` | `DisplayModeManagerContainer133` | root class; no distinctive extends/strings |
| `Class163` | `ImageTagText` | (hierarchy-based rename; see docstring) |
| `Class164` | `LoadingManager` | (hierarchy-based rename; see docstring) |
| `Class165` | `CursorManager` | (hierarchy-based rename; see docstring) |
| `Class166` | `Component290` | root class; no distinctive extends/strings |
| `Class167` | `Component385` | root class; no distinctive extends/strings |
| `Class168` | `Component39` | root class; no distinctive extends/strings |
| `Class169` | `RequestProcessor` | implements Runnable |
| `Class17` | `DisplayModeManagerContainer167` | root class; no distinctive extends/strings |
| `Class170` | `DisplayModeManagerContainer292` | root class; no distinctive extends/strings |
| `Class171` | `Component382` | root class; no distinctive extends/strings |
| `Class172` | `Component16` | root class; no distinctive extends/strings |
| `Class173` | `Component75` | root class; no distinctive extends/strings |
| `Class174` | `RadixParser` | (hierarchy-based rename; see docstring) |
| `Class175` | `Component143` | root class; no distinctive extends/strings |
| `Class176` | `Component218` | root class; no distinctive extends/strings |
| `Class177` | `DisplayModeManagerContainer249` | root class; no distinctive extends/strings |
| `Class178` | `DisplayModeManagerContainer109` | root class; no distinctive extends/strings |
| `Class179` | `BrowserDetector` | (hierarchy-based rename; see docstring) |
| `Class18` | `DisplayModeManagerContainer220` | root class; no distinctive extends/strings |
| `Class180` | `Component166` | root class; no distinctive extends/strings |
| `Class181` | `Component113` | root class; no distinctive extends/strings |
| `Class182` | `Component280` | root class; no distinctive extends/strings |
| `Class183` | `Component191` | root class; no distinctive extends/strings |
| `Class184` | `Component357` | root class; no distinctive extends/strings |
| `Class185` | `Component316` | root class; no distinctive extends/strings |
| `Class186` | `DisplayModeManagerContainer51` | root class; no distinctive extends/strings |
| `Class186_Sub1` | `Component134` | subclass of DisplayModeManagerContainer51 (hierarchy) |
| `Class187` | `Component38` | root class; no distinctive extends/strings |
| `Class188` | `ReliefShader` | (hierarchy-based rename; see docstring) |
| `Class189` | `Component291` | root class; no distinctive extends/strings |
| `Class19` | `Component248` | root class; no distinctive extends/strings |
| `Class190` | `DisplayModeManagerContainer56` | root class; no distinctive extends/strings |
| `Class191` | `ImageCache` | (hierarchy-based rename; see docstring) |
| `Class192` | `Component353` | root class; no distinctive extends/strings |
| `Class193` | `Component321` | root class; no distinctive extends/strings |
| `Class194` | `Component276` | root class; no distinctive extends/strings |
| `Class195` | `Component195` | root class; no distinctive extends/strings |
| `Class196` | `Component169` | root class; no distinctive extends/strings |
| `Class197` | `Component108` | root class; no distinctive extends/strings |
| `Class198` | `Component250` | root class; no distinctive extends/strings |
| `Class199` | `HelveticaFont` | (hierarchy-based rename; see docstring) |
| `Class2` | `Component245` | root class; no distinctive extends/strings |
| `Class20` | `Component120` | root class; no distinctive extends/strings |
| `Class200` | `Component33` | root class; no distinctive extends/strings |
| `Class201` | `CacheDirectory` | (hierarchy-based rename; see docstring) |
| `Class202` | `Connection` | implements Runnable |
| `Class203` | `DisplayModeManagerContainer351` | root class; no distinctive extends/strings |
| `Class204` | `Component198` | root class; no distinctive extends/strings |
| `Class205` | `Component275` | root class; no distinctive extends/strings |
| `Class206` | `DisplayModeManagerContainer105` | root class; no distinctive extends/strings |
| `Class207` | `Component170` | root class; no distinctive extends/strings |
| `Class208` | `Component222` | root class; no distinctive extends/strings |
| `Class209` | `Component246` | root class; no distinctive extends/strings |
| `Class21` | `Component158` | root class; no distinctive extends/strings |
| `Class210` | `Component116` | root class; no distinctive extends/strings |
| `Class211` | `DisplayModeManagerContainer159` | root class; no distinctive extends/strings |
| `Class212` | `Component187` | root class; no distinctive extends/strings |
| `Class213` | `NumberFormatter` | (hierarchy-based rename; see docstring) |
| `Class214` | `Component313` | root class; no distinctive extends/strings |
| `Class215` | `ScreenModeManager` | (hierarchy-based rename; see docstring) |
| `Class216` | `Component43` | root class; no distinctive extends/strings |
| `Class217` | `Component46` | root class; no distinctive extends/strings |
| `Class218` | `SceneManager` | (hierarchy-based rename; see docstring) |
| `Class219` | `Component386` | root class; no distinctive extends/strings |
| `Class22` | `Component231` | root class; no distinctive extends/strings |
| `Class220` | `Component7` | root class; no distinctive extends/strings |
| `Class221` | `Component85` | root class; no distinctive extends/strings |
| `Class222` | `Component303` | root class; no distinctive extends/strings |
| `Class223` | `DisplayModeManagerContainer369` | root class; no distinctive extends/strings |
| `Class224` | `AppletInvoker` | (hierarchy-based rename; see docstring) |
| `Class225` | `Component241` | root class; no distinctive extends/strings |
| `Class226` | `Component127` | root class; no distinctive extends/strings |
| `Class227` | `Component150` | root class; no distinctive extends/strings |
| `Class228` | `NativeLibraryLoader` | (hierarchy-based rename; see docstring) |
| `Class229` | `Component267` | root class; no distinctive extends/strings |
| `Class23` | `Component234` | root class; no distinctive extends/strings |
| `Class230` | `DisplayModeManagerContainer124` | root class; no distinctive extends/strings |
| `Class231` | `DisplayModeManagerContainer155` | root class; no distinctive extends/strings |
| `Class232` | `Component228` | root class; no distinctive extends/strings |
| `Class233` | `Component237` | root class; no distinctive extends/strings |
| `Class234` | `RandomAccessFileReader` | (hierarchy-based rename; see docstring) |
| `Class235` | `Component366` | root class; no distinctive extends/strings |
| `Class236` | `Component4` | root class; no distinctive extends/strings |
| `Class237` | `DisplayModeManagerContainer87` | root class; no distinctive extends/strings |
| `Class237_Sub1` | `Component251` | subclass of DisplayModeManagerContainer87 (hierarchy) |
| `Class238` | `SocketStream` | (hierarchy-based rename; see docstring) |
| `Class238_Sub1` | `TcpSocketStream` | extends SocketStream |
| `Class239` | `Component339` | root class; no distinctive extends/strings |
| `Class239_Sub1` | `Component175` | subclass of Component339 (hierarchy) |
| `Class239_Sub10` | `Component98` | subclass of Component339 (hierarchy) |
| `Class239_Sub11` | `LibraryCreditsText` | extends Component339 |
| `Class239_Sub12` | `Component205` | subclass of Component339 (hierarchy) |
| `Class239_Sub13` | `Component266` | subclass of Component339 (hierarchy) |
| `Class239_Sub14` | `Component329` | subclass of Component339 (hierarchy) |
| `Class239_Sub15` | `DisplayModeManagerContainer341` | subclass of Component339 (hierarchy) |
| `Class239_Sub16` | `Component25` | subclass of Component339 (hierarchy) |
| `Class239_Sub17` | `Component66` | subclass of Component339 (hierarchy) |
| `Class239_Sub18` | `Component302` | subclass of Component339 (hierarchy) |
| `Class239_Sub19` | `Component372` | subclass of Component339 (hierarchy) |
| `Class239_Sub2` | `DisplayModeManagerContainer199` | subclass of Component339 (hierarchy) |
| `Class239_Sub20` | `Component20` | subclass of Component339 (hierarchy) |
| `Class239_Sub21` | `Component71` | subclass of Component339 (hierarchy) |
| `Class239_Sub22` | `Component287` | subclass of Component339 (hierarchy) |
| `Class239_Sub23` | `Component388` | subclass of Component339 (hierarchy) |
| `Class239_Sub24` | `ColoredText` | extends Component339 |
| `Class239_Sub25` | `Component256` | subclass of Component339 (hierarchy) |
| `Class239_Sub26` | `RadixText` | extends Component339 |
| `Class239_Sub27` | `Component139` | subclass of Component339 (hierarchy) |
| `Class239_Sub28` | `Component188` | subclass of Component339 (hierarchy) |
| `Class239_Sub29` | `Component284` | subclass of Component339 (hierarchy) |
| `Class239_Sub3` | `Component272` | subclass of Component339 (hierarchy) |
| `Class239_Sub4` | `Component323` | subclass of Component339 (hierarchy) |
| `Class239_Sub5` | `DisplayModeManagerContainer348` | subclass of Component339 (hierarchy) |
| `Class239_Sub6` | `Component31` | subclass of Component339 (hierarchy) |
| `Class239_Sub7` | `Component60` | subclass of Component339 (hierarchy) |
| `Class239_Sub8` | `DisplayModeManagerContainer295` | subclass of Component339 (hierarchy) |
| `Class239_Sub9` | `Component379` | subclass of Component339 (hierarchy) |
| `Class24` | `DisplayModeManagerContainer310` | root class; no distinctive extends/strings |
| `Class240` | `Component49` | root class; no distinctive extends/strings |
| `Class241` | `Component41` | root class; no distinctive extends/strings |
| `Class241_Sub1` | `Component384` | subclass of Component41 (hierarchy) |
| `Class241_Sub2` | `Component18` | subclass of Component41 (hierarchy) |
| `Class241_Sub3` | `Component73` | subclass of Component41 (hierarchy) |
| `Class242` | `Component359` | root class; no distinctive extends/strings |
| `Class243` | `Component315` | root class; no distinctive extends/strings |
| `Class244` | `Component283` | root class; no distinctive extends/strings |
| `Class245` | `DebugOverlay` | (hierarchy-based rename; see docstring) |
| `Class246` | `Component163` | root class; no distinctive extends/strings |
| `Class246_Sub1` | `Component215` | subclass of Component163 (hierarchy) |
| `Class247` | `VideoAdDisplay` | (hierarchy-based rename; see docstring) |
| `Class248` | `Component253` | root class; no distinctive extends/strings |
| `Class249` | `Component217` | root class; no distinctive extends/strings |
| `Class25` | `Component361` | root class; no distinctive extends/strings |
| `Class250` | `AudioMixer` | implements Runnable, Runnable |
| `Class251` | `Component111` | root class; no distinctive extends/strings |
| `Class252` | `Component278` | root class; no distinctive extends/strings |
| `Class253` | `Component193` | root class; no distinctive extends/strings |
| `Class254` | `Component355` | root class; no distinctive extends/strings |
| `Class255` | `DisplayModeManagerContainer320` | root class; no distinctive extends/strings |
| `Class256` | `Component52` | root class; no distinctive extends/strings |
| `Class257` | `Component36` | root class; no distinctive extends/strings |
| `Class258` | `AbstractGlTexture` | implements Interface9, Interface9 |
| `Class258_Sub1` | `AbstractGlTextureSub1` | subclass of AbstractGlTexture (hierarchy) |
| `Class258_Sub2` | `GlFramebufferTexture` | extends renamed |
| `Class258_Sub3` | `GlTexture` | extends AbstractGlTexture |
| `Class258_Sub3_Sub1` | `GlRectangleTexture` | extends renamed |
| `Class258_Sub4` | `AbstractGlTextureSub4` | subclass of AbstractGlTexture (hierarchy) |
| `Class259` | `Component293` | root class; no distinctive extends/strings |
| `Class26` | `AssetCacheLoader` | root class; no distinctive extends/strings |
| `Class260` | `Component92` | root class; no distinctive extends/strings |
| `Class261` | `DisplayModeManagerContainer1` | root class; no distinctive extends/strings |
| `Class262` | `NodeList` | (hierarchy-based rename; see docstring) |
| `Class263` | `Component309` | root class; no distinctive extends/strings |
| `Class264` | `Component235` | root class; no distinctive extends/strings |
| `Class265` | `DisplayModeManagerContainer232` | root class; no distinctive extends/strings |
| `Class266` | `Component157` | root class; no distinctive extends/strings |
| `Class267` | `Component119` | root class; no distinctive extends/strings |
| `Class268` | `DisplayModeManagerContainer259` | root class; no distinctive extends/strings |
| `Class269` | `Component209` | root class; no distinctive extends/strings |
| `Class27` | `DisplayModeManagerContainer91` | root class; no distinctive extends/strings |
| `Class270` | `Component148` | root class; no distinctive extends/strings |
| `Class271` | `DisplayModeManagerContainer130` | root class; no distinctive extends/strings |
| `Class272` | `SocketConnector` | (hierarchy-based rename; see docstring) |
| `Class272_Sub1` | `DirectSocketConnector` | extends SocketConnector |
| `Class272_Sub2` | `ProxySocketConnector` | extends SocketConnector |
| `Class273` | `Component224` | root class; no distinctive extends/strings |
| `Class274` | `FriendsIgnoreList` | (hierarchy-based rename; see docstring) |
| `Class275` | `Component300` | root class; no distinctive extends/strings |
| `Class276` | `Component81` | root class; no distinctive extends/strings |
| `Class277` | `Component11` | root class; no distinctive extends/strings |
| `Class278` | `DisplayModeManagerContainer347` | root class; no distinctive extends/strings |
| `Class279` | `AudioLine` | (hierarchy-based rename; see docstring) |
| `Class279_Sub1` | `SourceAudioLine` | extends AudioLine |
| `Class279_Sub2` | `AudioLineSub2` | subclass of AudioLine (hierarchy) |
| `Class28` | `DisplayModeManagerContainer332` | root class; no distinctive extends/strings |
| `Class280` | `Component333` | root class; no distinctive extends/strings |
| `Class281` | `Component337` | root class; no distinctive extends/strings |
| `Class282` | `Component21` | root class; no distinctive extends/strings |
| `Class283` | `Component69` | root class; no distinctive extends/strings |
| `Class284` | `Component94` | root class; no distinctive extends/strings |
| `Class285` | `DebugPanic` | implements Interface5 |
| `Class285_Sub1` | `DebugPanicSub1` | subclass of DebugPanic (hierarchy) |
| `Class285_Sub2` | `DebugPanicSub2` | subclass of DebugPanic (hierarchy) |
| `Class286` | `ShaderProgram` | (hierarchy-based rename; see docstring) |
| `Class286_Sub1` | `ArbShaderProgram` | extends ShaderProgram |
| `Class286_Sub2` | `ShaderProgramSub2` | subclass of ShaderProgram (hierarchy) |
| `Class286_Sub3` | `ParticleShader` | extends renamed |
| `Class286_Sub4` | `WaterSurfaceShader` | extends ShaderProgram |
| `Class286_Sub5` | `SpriteAtlasShader` | extends ShaderProgram |
| `Class286_Sub6` | `WaterShaderProgram` | extends ShaderProgram |
| `Class286_Sub7` | `ShaderProgramSub7` | subclass of ShaderProgram (hierarchy) |
| `Class286_Sub8` | `CookieManager` | extends ShaderProgram |
| `Class286_Sub9` | `ShaderProgramSub9` | subclass of ShaderProgram (hierarchy) |
| `Class287` | `DisplayModeManagerContainer260` | root class; no distinctive extends/strings |
| `Class288` | `Component118` | root class; no distinctive extends/strings |
| `Class288_Sub1` | `Component383` | subclass of Component118 (hierarchy) |
| `Class289` | `Component156` | root class; no distinctive extends/strings |
| `Class29` | `Component336` | root class; no distinctive extends/strings |
| `Class290` | `Component200` | root class; no distinctive extends/strings |
| `Class291` | `ReferenceTable` | (hierarchy-based rename; see docstring) |
| `Class292` | `Component102` | root class; no distinctive extends/strings |
| `Class293` | `Component176` | root class; no distinctive extends/strings |
| `Class294` | `InterfaceRenderer` | implements Interface16, Interface16 |
| `Class295` | `DisplayModeManagerContainer61` | root class; no distinctive extends/strings |
| `Class296` | `HuffmanDecoder` | (hierarchy-based rename; see docstring) |
| `Class297` | `ReflectionInvoker` | implements Runnable |
| `Class298` | `Component10` | root class; no distinctive extends/strings |
| `Class299` | `Component80` | root class; no distinctive extends/strings |
| `Class299_Sub1` | `Component40` | subclass of Component80 (hierarchy) |
| `Class299_Sub1_Sub1` | `Component100` | subclass of Component40 (hierarchy) |
| `Class299_Sub1_Sub2` | `Component264` | subclass of Component40 (hierarchy) |
| `Class299_Sub2` | `DisplayModeManagerContainer356` | subclass of Component80 (hierarchy) |
| `Class299_Sub2_Sub1` | `Component201` | subclass of DisplayModeManagerContainer356 (hierarchy) |
| `Class3` | `Component223` | root class; no distinctive extends/strings |
| `Class30` | `ComponentDownloader` | (hierarchy-based rename; see docstring) |
| `Class300` | `Component189` | root class; no distinctive extends/strings |
| `Class301` | `Component281` | root class; no distinctive extends/strings |
| `Class302` | `Component114` | root class; no distinctive extends/strings |
| `Class303` | `DisplayModeManagerContainer165` | root class; no distinctive extends/strings |
| `Class304` | `DisplayModeManagerContainer42` | root class; no distinctive extends/strings |
| `Class305` | `DisplayModeManagerContainer50` | root class; no distinctive extends/strings |
| `Class306` | `Component314` | root class; no distinctive extends/strings |
| `Class307` | `Component358` | root class; no distinctive extends/strings |
| `Class308` | `Component17` | root class; no distinctive extends/strings |
| `Class309` | `DisplayModeManagerContainer74` | root class; no distinctive extends/strings |
| `Class31` | `Component82` | root class; no distinctive extends/strings |
| `Class310` | `DisplayModeManagerContainer318` | root class; no distinctive extends/strings |
| `Class310_Sub1` | `Component78` | subclass of DisplayModeManagerContainer318 (hierarchy) |
| `Class310_Sub2` | `Component294` | subclass of DisplayModeManagerContainer318 (hierarchy) |
| `Class310_Sub3` | `Component378` | subclass of DisplayModeManagerContainer318 (hierarchy) |
| `Class311` | `ResourceLoader` | implements Runnable, Runnable |
| `Class312` | `Component37` | root class; no distinctive extends/strings |
| `Class313` | `Component55` | root class; no distinctive extends/strings |
| `Class314` | `Component112` | root class; no distinctive extends/strings |
| `Class314_Sub1` | `Component219` | subclass of Component112 (hierarchy) |
| `Class315` | `Component168` | root class; no distinctive extends/strings |
| `Class316` | `Component192` | root class; no distinctive extends/strings |
| `Class317` | `Component277` | root class; no distinctive extends/strings |
| `Class318` | `Renderable` | (hierarchy-based rename; see docstring) |
| `Class318_Sub1` | `RenderableObject` | extends Renderable |
| `Class318_Sub10` | `RenderableSub10` | subclass of Renderable (hierarchy) |
| `Class318_Sub1_Sub1` | `DisplayModeManagerContainer28` | subclass of RenderableObject (hierarchy) |
| `Class318_Sub1_Sub1_Sub1` | `Component141` | subclass of DisplayModeManagerContainer28 (hierarchy) |
| `Class318_Sub1_Sub1_Sub2` | `Component212` | subclass of DisplayModeManagerContainer28 (hierarchy) |
| `Class318_Sub1_Sub2` | `DisplayModeManagerContainer343` | subclass of RenderableObject (hierarchy) |
| `Class318_Sub1_Sub2_Sub1` | `Component252` | subclass of DisplayModeManagerContainer343 (hierarchy) |
| `Class318_Sub1_Sub3` | `Component327` | subclass of RenderableObject (hierarchy) |
| `Class318_Sub1_Sub3_Sub1` | `Component349` | subclass of Component327 (hierarchy) |
| `Class318_Sub1_Sub3_Sub2` | `Component29` | subclass of Component327 (hierarchy) |
| `Class318_Sub1_Sub3_Sub3` | `DisplayModeManagerContainer58` | subclass of Component327 (hierarchy) |
| `Class318_Sub1_Sub3_Sub3_Sub1` | `Npc` | JODE header recorded original obfuscated name |
| `Class318_Sub1_Sub3_Sub3_Sub2` | `Player` | JODE header recorded original obfuscated name |
| `Class318_Sub1_Sub3_Sub4` | `DisplayModeManagerContainer104` | subclass of Component327 (hierarchy) |
| `Class318_Sub1_Sub3_Sub5` | `DisplayModeManagerContainer174` | subclass of Component327 (hierarchy) |
| `Class318_Sub1_Sub4` | `Component269` | subclass of RenderableObject (hierarchy) |
| `Class318_Sub1_Sub4_Sub1` | `Component197` | subclass of Component269 (hierarchy) |
| `Class318_Sub1_Sub4_Sub2` | `Component171` | subclass of Component269 (hierarchy) |
| `Class318_Sub1_Sub5` | `Component203` | subclass of RenderableObject (hierarchy) |
| `Class318_Sub1_Sub5_Sub1` | `Component289` | subclass of Component203 (hierarchy) |
| `Class318_Sub1_Sub5_Sub2` | `ShaderLinker` | extends Component203; implements Interface10 |
| `Class318_Sub2` | `RenderableSub2` | subclass of Renderable (hierarchy) |
| `Class318_Sub3` | `RenderableSub3` | subclass of Renderable (hierarchy) |
| `Class318_Sub4` | `RenderableSub4` | subclass of Renderable (hierarchy) |
| `Class318_Sub5` | `RenderableSub5` | subclass of Renderable (hierarchy) |
| `Class318_Sub6` | `RenderableSub6` | subclass of Renderable (hierarchy) |
| `Class318_Sub7` | `StaticElementRenderer` | extends Renderable |
| `Class318_Sub8` | `RenderableSub8` | subclass of Renderable (hierarchy) |
| `Class318_Sub9` | `RenderableSub9` | subclass of Renderable (hierarchy) |
| `Class318_Sub9_Sub1` | `RenderableSub9Sub1` | subclass of RenderableSub9 (hierarchy) |
| `Class318_Sub9_Sub2` | `RenderableSub9Sub2` | subclass of RenderableSub9 (hierarchy) |
| `Class318_Sub9_Sub2_Sub1` | `RenderableSub9Sub2Sub1` | subclass of RenderableSub9Sub2 (hierarchy) |
| `Class319` | `Component144` | root class; no distinctive extends/strings |
| `Class32` | `Component299` | root class; no distinctive extends/strings |
| `Class320` | `Component233` | root class; no distinctive extends/strings |
| `Class321` | `Component236` | root class; no distinctive extends/strings |
| `Class322` | `ImageCacheStore` | (hierarchy-based rename; see docstring) |
| `Class323` | `FontGlyphCache` | (hierarchy-based rename; see docstring) |
| `Class325` | `ColoredTextBuilder` | (hierarchy-based rename; see docstring) |
| `Class326` | `Component311` | root class; no distinctive extends/strings |
| `Class327` | `DisplayModeManagerContainer363` | root class; no distinctive extends/strings |
| `Class328` | `ShaderCompiler` | (hierarchy-based rename; see docstring) |
| `Class328_Sub1` | `ShaderCompilerSub1` | subclass of ShaderCompiler (hierarchy) |
| `Class328_Sub1_Sub1` | `ShaderCompilerSub1Sub1` | subclass of ShaderCompilerSub1 (hierarchy) |
| `Class328_Sub2` | `ShaderCompilerSub2` | subclass of ShaderCompiler (hierarchy) |
| `Class328_Sub2_Sub1` | `ShaderCompilerSub2Sub1` | subclass of ShaderCompilerSub2 (hierarchy) |
| `Class328_Sub3` | `ShaderCompilerSub3` | subclass of ShaderCompiler (hierarchy) |
| `Class329` | `Component68` | root class; no distinctive extends/strings |
| `Class33` | `Component373` | root class; no distinctive extends/strings |
| `Class330` | `Component301` | root class; no distinctive extends/strings |
| `Class331` | `Component374` | root class; no distinctive extends/strings |
| `Class332` | `Component9` | root class; no distinctive extends/strings |
| `Class333` | `Component79` | root class; no distinctive extends/strings |
| `Class334` | `NativeLibLoader` | (hierarchy-based rename; see docstring) |
| `Class335` | `DisplayModeManagerContainer147` | root class; no distinctive extends/strings |
| `Class336` | `Component226` | root class; no distinctive extends/strings |
| `Class337` | `Component244` | root class; no distinctive extends/strings |
| `Class338` | `Component103` | root class; no distinctive extends/strings |
| `Class339` | `NewsFetcher` | implements Runnable |
| `Class34` | `Component225` | root class; no distinctive extends/strings |
| `Class340` | `CacheFileStore` | (hierarchy-based rename; see docstring) |
| `Class341` | `DisplayModeManagerContainer196` | root class; no distinctive extends/strings |
| `Class342` | `DisplayModeManagerContainer173` | root class; no distinctive extends/strings |
| `Class343` | `Component107` | root class; no distinctive extends/strings |
| `Class344` | `AbstractBuffer` | (hierarchy-based rename; see docstring) |
| `Class344_Sub1` | `ByteBufferReader` | extends AbstractBuffer |
| `Class345` | `Component35` | root class; no distinctive extends/strings |
| `Class345_Sub1` | `Component340` | subclass of Component35 (hierarchy) |
| `Class346` | `InputHandler` | implements KeyListener |
| `Class346_Sub1` | `KeyFocusHandler` | extends InputHandler; implements KeyListener, FocusListener, KeyListener, FocusListener |
| `Class347` | `DisplayModeManagerContainer322` | root class; no distinctive extends/strings |
| `Class348` | `Node` | (hierarchy-based rename; see docstring) |
| `Class348_Sub1` | `NodeSub1` | subclass of Node (hierarchy) |
| `Class348_Sub10` | `NodeSub10` | subclass of Node (hierarchy) |
| `Class348_Sub11` | `NodeSub11` | subclass of Node (hierarchy) |
| `Class348_Sub12` | `NodeSub12` | subclass of Node (hierarchy) |
| `Class348_Sub13` | `NodeSub13` | subclass of Node (hierarchy) |
| `Class348_Sub14` | `NodeSub14` | subclass of Node (hierarchy) |
| `Class348_Sub15` | `ColorTagNode` | extends Node |
| `Class348_Sub16` | `NodeSub16` | subclass of Node (hierarchy) |
| `Class348_Sub16_Sub1` | `GpiLogger` | extends NodeSub16 |
| `Class348_Sub16_Sub2` | `NodeSub16Sub2` | subclass of NodeSub16 (hierarchy) |
| `Class348_Sub16_Sub3` | `BrowserUrlOpener` | extends NodeSub16 |
| `Class348_Sub16_Sub4` | `NodeSub16Sub4` | subclass of NodeSub16 (hierarchy) |
| `Class348_Sub16_Sub5` | `NodeSub16Sub5` | subclass of NodeSub16 (hierarchy) |
| `Class348_Sub17` | `NpcDefinition` | extends renamed |
| `Class348_Sub18` | `NodeSub18` | subclass of Node (hierarchy) |
| `Class348_Sub19` | `NodeSub19` | subclass of Node (hierarchy) |
| `Class348_Sub19_Sub1` | `NodeSub19Sub1` | subclass of NodeSub19 (hierarchy) |
| `Class348_Sub1_Sub1` | `NodeSub1Sub1` | subclass of NodeSub1 (hierarchy) |
| `Class348_Sub1_Sub2` | `NodeSub1Sub2` | subclass of NodeSub1 (hierarchy) |
| `Class348_Sub1_Sub3` | `NodeSub1Sub3` | subclass of NodeSub1 (hierarchy) |
| `Class348_Sub2` | `NodeSub2` | subclass of Node (hierarchy) |
| `Class348_Sub20` | `NodeSub20` | subclass of Node (hierarchy) |
| `Class348_Sub21` | `NodeSub21` | subclass of Node (hierarchy) |
| `Class348_Sub22` | `NodeSub22` | subclass of Node (hierarchy) |
| `Class348_Sub23` | `OggStream` | extends Node |
| `Class348_Sub23_Sub1` | `OggUrlStream` | extends OggStream |
| `Class348_Sub23_Sub2` | `DisplayModeManagerContainer64` | subclass of OggStream (hierarchy) |
| `Class348_Sub23_Sub3` | `OggStreamReader` | extends OggStream |
| `Class348_Sub23_Sub4` | `Component179` | subclass of OggStream (hierarchy) |
| `Class348_Sub24` | `ClientSystemInfo` | extends Node |
| `Class348_Sub25` | `NodeSub25` | subclass of Node (hierarchy) |
| `Class348_Sub26` | `Request` | extends Node |
| `Class348_Sub27` | `NodeSub27` | subclass of Node (hierarchy) |
| `Class348_Sub28` | `NodeSub28` | subclass of Node (hierarchy) |
| `Class348_Sub29` | `NodeSub29` | subclass of Node (hierarchy) |
| `Class348_Sub3` | `NodeSub3` | subclass of Node (hierarchy) |
| `Class348_Sub30` | `NodeSub30` | subclass of Node (hierarchy) |
| `Class348_Sub31` | `Sprite` | extends Node |
| `Class348_Sub31_Sub1` | `BufferedImageSprite` | extends Sprite |
| `Class348_Sub31_Sub2` | `ImageProducerSprite` | extends Sprite; implements java, ImageProducer |
| `Class348_Sub32` | `NodeSub32` | subclass of Node (hierarchy) |
| `Class348_Sub33` | `ObjectDeserializer` | extends Node |
| `Class348_Sub34` | `NodeSub34` | subclass of Node (hierarchy) |
| `Class348_Sub35` | `NodeSub35` | subclass of Node (hierarchy) |
| `Class348_Sub36` | `NodeSub36` | subclass of Node (hierarchy) |
| `Class348_Sub37` | `NodeSub37` | subclass of Node (hierarchy) |
| `Class348_Sub38` | `NodeSub38` | subclass of Node (hierarchy) |
| `Class348_Sub39` | `NodeSub39` | subclass of Node (hierarchy) |
| `Class348_Sub4` | `HardwareProbe` | extends renamed |
| `Class348_Sub40` | `Definition` | extends Node |
| `Class348_Sub40_Sub1` | `PrimitiveTypeDefinition` | distinctive string present; subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub10` | `DefinitionSub10` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub11` | `DefinitionSub11` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub12` | `GradientPreset` | extends Definition |
| `Class348_Sub40_Sub13` | `DefinitionSub13` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub14` | `CurvePreset` | extends Definition |
| `Class348_Sub40_Sub15` | `DefinitionSub15` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub16` | `ToolbarRefreshDefinition` | distinctive string present; subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub17` | `DefinitionSub17` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub17_Sub1` | `DefinitionSub17Sub1` | subclass of DefinitionSub17 (hierarchy) |
| `Class348_Sub40_Sub18` | `NsnDefinition` | distinctive string present; subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub19` | `DefinitionSub19` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub2` | `DefinitionSub2` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub20` | `DefinitionSub20` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub21` | `DefinitionSub21` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub22` | `DefinitionSub22` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub23` | `DefinitionSub23` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub24` | `DefinitionSub24` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub25` | `DefinitionSub25` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub26` | `DefinitionSub26` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub27` | `DefinitionSub27` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub28` | `DefinitionSub28` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub29` | `DefinitionSub29` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub3` | `ParametricDefinition` | root class; no distinctive extends/strings |
| `Class348_Sub40_Sub30` | `DefinitionSub30` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub31` | `DefinitionSub31` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub32` | `DefinitionSub32` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub33` | `DefinitionSub33` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub34` | `LoggedOutDefinition` | distinctive string present; subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub35` | `DefinitionSub35` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub36` | `DefinitionSub36` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub37` | `DefinitionSub37` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub38` | `DefinitionSub38` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub39` | `DefinitionSub39` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub4` | `DefinitionSub4` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub5` | `DefinitionSub5` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub6` | `DefinitionSub6` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub7` | `ImageDefinition` | extends renamed |
| `Class348_Sub40_Sub8` | `DefinitionSub8` | subclass of Definition (hierarchy) |
| `Class348_Sub40_Sub9` | `DefinitionSub9` | subclass of Definition (hierarchy) |
| `Class348_Sub41` | `NodeSub41` | subclass of Node (hierarchy) |
| `Class348_Sub42` | `HashNode` | extends Node |
| `Class348_Sub42_Sub1` | `HashNodeSub1` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub10` | `HashNodeSub10` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub11` | `StringDefinition` | extends renamed |
| `Class348_Sub42_Sub13` | `HashNodeSub13` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub14` | `HashNodeSub14` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub15` | `RSARequest` | extends HashNode |
| `Class348_Sub42_Sub16` | `HashNodeSub16` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub16_Sub1` | `HashNodeSub16Sub1` | subclass of HashNodeSub16 (hierarchy) |
| `Class348_Sub42_Sub16_Sub2` | `HashNodeSub16Sub2` | subclass of HashNodeSub16 (hierarchy) |
| `Class348_Sub42_Sub17` | `HashNodeSub17` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub18` | `HashNodeSub18` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub19` | `HashNodeSub19` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub2` | `HashNodeSub2` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub20` | `HashNodeSub20` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub3` | `HashNodeSub3` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub4` | `HashNodeSub4` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub5` | `DefinitionGroup` | extends HashNode |
| `Class348_Sub42_Sub6` | `PauseHandler` | extends HashNode; implements the |
| `Class348_Sub42_Sub7` | `HashNodeSub7` | subclass of HashNode (hierarchy) |
| `Class348_Sub42_Sub8` | `CacheNode` | extends HashNode |
| `Class348_Sub42_Sub8_Sub1` | `SoftReferenceCacheNode` | extends CacheNode |
| `Class348_Sub42_Sub8_Sub2` | `PacketReader` | extends CacheNode |
| `Class348_Sub42_Sub9` | `ReferenceHolder` | extends HashNode |
| `Class348_Sub42_Sub9_Sub1` | `Component140` | subclass of ReferenceHolder (hierarchy) |
| `Class348_Sub42_Sub9_Sub2` | `SoftReferenceHolder` | extends ReferenceHolder |
| `Class348_Sub43` | `PlayerState` | extends renamed |
| `Class348_Sub44` | `NodeSub44` | subclass of Node (hierarchy) |
| `Class348_Sub45` | `NodeSub45` | subclass of Node (hierarchy) |
| `Class348_Sub45_Sub1` | `NodeSub45Sub1` | subclass of NodeSub45 (hierarchy) |
| `Class348_Sub45_Sub2` | `NodeSub45Sub2` | subclass of NodeSub45 (hierarchy) |
| `Class348_Sub46` | `NodeSub46` | subclass of Node (hierarchy) |
| `Class348_Sub47` | `ParticleSystem` | extends Node |
| `Class348_Sub48` | `NodeSub48` | subclass of Node (hierarchy) |
| `Class348_Sub49` | `Buffer` | extends Node |
| `Class348_Sub49_Sub1` | `Component182` | subclass of Buffer (hierarchy) |
| `Class348_Sub49_Sub2` | `DisplayModeManagerContainer207` | subclass of Buffer (hierarchy) |
| `Class348_Sub5` | `NodeSub5` | subclass of Node (hierarchy) |
| `Class348_Sub50` | `NodeSub50` | subclass of Node (hierarchy) |
| `Class348_Sub51` | `NodeSub51` | subclass of Node (hierarchy) |
| `Class348_Sub5_Sub1` | `GlWaterShader` | extends NodeSub5 |
| `Class348_Sub6` | `NodederUtil` | subclass of Node (hierarchy) |
| `Class348_Sub7` | `NodeSub7` | subclass of Node (hierarchy) |
| `Class348_Sub8` | `NodeSub8` | subclass of Node (hierarchy) |
| `Class348_Sub9` | `SceneNode` | extends Node |
| `Class349` | `Component13` | root class; no distinctive extends/strings |
| `Class35` | `Component243` | root class; no distinctive extends/strings |
| `Class350` | `Component360` | root class; no distinctive extends/strings |
| `Class351` | `StringCache` | (hierarchy-based rename; see docstring) |
| `Class352` | `Component48` | root class; no distinctive extends/strings |
| `Class353` | `Component45` | root class; no distinctive extends/strings |
| `Class354` | `Component160` | root class; no distinctive extends/strings |
| `Class355` | `Component117` | root class; no distinctive extends/strings |
| `Class356` | `LruCache` | (hierarchy-based rename; see docstring) |
| `Class357` | `Component186` | root class; no distinctive extends/strings |
| `Class358` | `Component142` | root class; no distinctive extends/strings |
| `Class359` | `Component135` | root class; no distinctive extends/strings |
| `Class36` | `Component129` | root class; no distinctive extends/strings |
| `Class360` | `Component242` | root class; no distinctive extends/strings |
| `Class361` | `TeleportHandler` | (hierarchy-based rename; see docstring) |
| `Class362` | `Component149` | root class; no distinctive extends/strings |
| `Class363` | `Component126` | root class; no distinctive extends/strings |
| `Class364` | `NamedInteger` | (hierarchy-based rename; see docstring) |
| `Class365` | `Component6` | root class; no distinctive extends/strings |
| `Class366` | `Component371` | root class; no distinctive extends/strings |
| `Class367` | `AbstractShader` | (hierarchy-based rename; see docstring) |
| `Class367_Sub1` | `AbstractShaderSub1` | subclass of AbstractShader (hierarchy) |
| `Class367_Sub10` | `SoftwareFallbackShader` | extends AbstractShader |
| `Class367_Sub11` | `OpenGlShader` | extends AbstractShader |
| `Class367_Sub2` | `AbstractShaderSub2` | subclass of AbstractShader (hierarchy) |
| `Class367_Sub3` | `AbstractShaderSub3` | subclass of AbstractShader (hierarchy) |
| `Class367_Sub4` | `AbstractShaderSub4` | subclass of AbstractShader (hierarchy) |
| `Class367_Sub5` | `D3DShader` | extends AbstractShader |
| `Class367_Sub6` | `TransparentWaterShader` | extends AbstractShader |
| `Class367_Sub7` | `EnvironmentMappedWaterShader` | extends AbstractShader |
| `Class367_Sub8` | `WaterShaderSub8` | distinctive string present; subclass of AbstractShader (hierarchy) |
| `Class367_Sub9` | `WaterShader` | extends AbstractShader |
| `Class368` | `Component63` | root class; no distinctive extends/strings |
| `Class369` | `Component27` | root class; no distinctive extends/strings |
| `Class369_Sub1` | `Component381` | subclass of Component27 (hierarchy) |
| `Class369_Sub2` | `Component14` | subclass of Component27 (hierarchy) |
| `Class369_Sub3` | `Component76` | subclass of Component27 (hierarchy) |
| `Class369_Sub3_Sub1` | `DummyClass` | extends Component76 |
| `Class37` | `NameFormatter` | (hierarchy-based rename; see docstring) |
| `Class370` | `Component367` | root class; no distinctive extends/strings |
| `Class371` | `Component307` | root class; no distinctive extends/strings |
| `Class372` | `Component86` | root class; no distinctive extends/strings |
| `Class374` | `DisplayModeManagerContainer152` | root class; no distinctive extends/strings |
| `Class375` | `Component121` | root class; no distinctive extends/strings |
| `Class376` | `Component239` | root class; no distinctive extends/strings |
| `Class377` | `GlExtensionManager` | extends GlToolkitSub3 |
| `Class378` | `D3DToolkit` | extends GlToolkitSub3 |
| `Class38` | `PauseTimer` | (hierarchy-based rename; see docstring) |
| `Class39` | `DisplayModeManagerContainer271` | root class; no distinctive extends/strings |
| `Class4` | `Component376` | root class; no distinctive extends/strings |
| `Class40` | `DisplayModeManagerContainer172` | root class; no distinctive extends/strings |
| `Class41` | `Component106` | root class; no distinctive extends/strings |
| `Class42` | `Component274` | root class; no distinctive extends/strings |
| `Class43` | `CookieBuilder` | (hierarchy-based rename; see docstring) |
| `Class44` | `Component352` | root class; no distinctive extends/strings |
| `Class45` | `CacheStore` | (hierarchy-based rename; see docstring) |
| `Class46` | `DisplayModeManagerContainer57` | root class; no distinctive extends/strings |
| `Class47` | `DisplayModeManagerContainer32` | root class; no distinctive extends/strings |
| `Class47_Sub1` | `Component375` | subclass of DisplayModeManagerContainer32 (hierarchy) |
| `Class47_Sub2` | `Component12` | subclass of DisplayModeManagerContainer32 (hierarchy) |
| `Class48` | `Component377` | root class; no distinctive extends/strings |
| `Class49` | `Component297` | root class; no distinctive extends/strings |
| `Class5` | `Component298` | root class; no distinctive extends/strings |
| `Class50` | `Component47` | root class; no distinctive extends/strings |
| `Class50_Sub1` | `Cp1252Decoder` | extends Component47 |
| `Class50_Sub2` | `Component308` | subclass of Component47 (hierarchy) |
| `Class50_Sub3` | `DisplayModeManagerContainer368` | subclass of Component47 (hierarchy) |
| `Class50_Sub4` | `Component230` | subclass of Component47 (hierarchy) |
| `Class51` | `Component44` | root class; no distinctive extends/strings |
| `Class52` | `RSACipher` | implements Interface12, Interface12 |
| `Class53` | `Component312` | root class; no distinctive extends/strings |
| `Class54` | `Component285` | root class; no distinctive extends/strings |
| `Class55` | `JagTheoraDecoder` | (hierarchy-based rename; see docstring) |
| `Class55_Sub1` | `Component326` | subclass of JagTheoraDecoder (hierarchy) |
| `Class56` | `LoadingState` | (hierarchy-based rename; see docstring) |
| `Class57` | `DisplayModeManagerContainer115` | root class; no distinctive extends/strings |
| `Class58` | `Component255` | root class; no distinctive extends/strings |
| `Class59` | `DisplayModeManagerContainer213` | root class; no distinctive extends/strings |
| `Class59_Sub1` | `Component210` | subclass of DisplayModeManagerContainer213 (hierarchy) |
| `Class59_Sub1_Sub1` | `Component350` | subclass of Component210 (hierarchy) |
| `Class59_Sub1_Sub2` | `DisplayModeManagerContainer34` | subclass of Component210 (hierarchy) |
| `Class59_Sub2` | `Component185` | subclass of DisplayModeManagerContainer213 (hierarchy) |
| `Class59_Sub2_Sub1` | `Component54` | subclass of Component185 (hierarchy) |
| `Class59_Sub2_Sub2` | `Component317` | subclass of Component185 (hierarchy) |
| `Class5_Sub1` | `Component364` | subclass of Component298 (hierarchy) |
| `Class5_Sub1_Sub1` | `Component305` | subclass of Component364 (hierarchy) |
| `Class5_Sub2` | `Component2` | subclass of Component298 (hierarchy) |
| `Class5_Sub3` | `DisplayModeManagerContainer89` | subclass of Component298 (hierarchy) |
| `Class6` | `BuildInfo` | (hierarchy-based rename; see docstring) |
| `Class60` | `NodeCache` | (hierarchy-based rename; see docstring) |
| `Class61` | `Component128` | root class; no distinctive extends/strings |
| `Class62` | `Component240` | root class; no distinctive extends/strings |
| `Class63` | `Component227` | root class; no distinctive extends/strings |
| `Class64` | `DisplayModeManagerContainer370` | root class; no distinctive extends/strings |
| `Class64_Sub1` | `DisplayModeManagerContainer164` | subclass of DisplayModeManagerContainer370 (hierarchy) |
| `Class64_Sub2` | `DisplayModeManagerContainer190` | subclass of DisplayModeManagerContainer370 (hierarchy) |
| `Class64_Sub3` | `DisplayModeManagerContainer282` | subclass of DisplayModeManagerContainer370 (hierarchy) |
| `Class65` | `Component304` | root class; no distinctive extends/strings |
| `Class66` | `ClientScriptExecutor` | (hierarchy-based rename; see docstring) |
| `Class67` | `VideoAdPlayer` | implements Interface1 |
| `Class68` | `Component342` | root class; no distinctive extends/strings |
| `Class69` | `Component325` | root class; no distinctive extends/strings |
| `Class7` | `DisplayModeManager` | (hierarchy-based rename; see docstring) |
| `Class70` | `DisplayModeManagerContainer88` | root class; no distinctive extends/strings |
| `Class71` | `DisplayModeManagerContainer5` | root class; no distinctive extends/strings |
| `Class72` | `DisplayModeManagerContainer365` | root class; no distinctive extends/strings |
| `Class73` | `DisplayModeManagerContainer306` | root class; no distinctive extends/strings |
| `Class74` | `DisplayModeManagerContainer238` | root class; no distinctive extends/strings |
| `Class75` | `DisplayModeManagerContainer229` | root class; no distinctive extends/strings |
| `Class75_Sub1` | `DisplayModeManagerContainer216` | subclass of DisplayModeManagerContainer229 (hierarchy) |
| `Class76` | `DisplayModeManagerContainer154` | root class; no distinctive extends/strings |
| `Class77` | `DisplayModeManagerContainer123` | root class; no distinctive extends/strings |
| `Class78` | `SeekableFile` | (hierarchy-based rename; see docstring) |
| `Class79` | `DisplayModeManagerContainer206` | root class; no distinctive extends/strings |
| `Class8` | `DisplayModeManagerContainer345` | root class; no distinctive extends/strings |
| `Class80` | `Component202` | root class; no distinctive extends/strings |
| `Class81` | `Component268` | root class; no distinctive extends/strings |
| `Class82` | `CommandHandler` | (hierarchy-based rename; see docstring) |
| `Class83` | `Component178` | root class; no distinctive extends/strings |
| `Class84` | `DisplayModeManagerContainer26` | root class; no distinctive extends/strings |
| `Class85` | `LogicError` | (hierarchy-based rename; see docstring) |
| `Class86` | `Component328` | root class; no distinctive extends/strings |
| `Class87` | `Component344` | root class; no distinctive extends/strings |
| `Class88` | `TheoraVideoPlayer` | (hierarchy-based rename; see docstring) |
| `Class89` | `Component84` | root class; no distinctive extends/strings |
| `Class9` | `JaclibLoader` | (hierarchy-based rename; see docstring) |
| `Class90` | `Component330` | root class; no distinctive extends/strings |
| `Class91` | `GnpPositionLogger` | (hierarchy-based rename; see docstring) |
| `Class92` | `DisplayModeManagerContainer23` | root class; no distinctive extends/strings |
| `Class93` | `ToolkitFactory` | (hierarchy-based rename; see docstring) |
| `Class94` | `Component97` | root class; no distinctive extends/strings |
| `Class95` | `Component181` | root class; no distinctive extends/strings |
| `Class96` | `ToolkitLoader` | (hierarchy-based rename; see docstring) |
| `Class97` | `Component263` | root class; no distinctive extends/strings |
| `Class98` | `Component122` | root class; no distinctive extends/strings |
| `Class99` | `DisplayModeManagerContainer153` | root class; no distinctive extends/strings |
| `a` | `SoftwareToolkit` | implements Interface19 (toolkit iface); 15 native JNI methods; instantiates i (OpenGLSprite) |
| `aa` | `Shader` | abstract base class; references Shader; parent of aa_Sub1/2/3 |
| `aa_Sub1` | `ShaderSub1` | extends aa (Shader) |
| `aa_Sub2` | `ShaderSub2` | extends aa (Shader) |
| `aa_Sub3` | `ShaderSub3` | extends aa (Shader) |
| `ba` | `BufferedToolkit` | extends HashNodeImpl (HashNodeImpl); implements Interface19; 1 native method |
| `d` | `Drawable` | interface (no impl); used as type |
| `h` | `NativeFont` | extends BitmapFont; implements Interface19; 4 native font methods |
| `ha` | `GraphicsToolkit` | (hierarchy-based rename; see docstring) |
| `ha_Sub1` | `GlToolkitSub1` | extends GraphicsToolkit (ha) |
| `ha_Sub2` | `GlToolkitSub2` | extends GraphicsToolkit (ha) |
| `ha_Sub3` | `GlToolkitSub3` | extends GraphicsToolkit (ha) |
| `i` | `OpenGLSprite` | extends Class64 (Sprite base); implements Interface19; 37 native GL methods |
| `j` | `D3DSprite` | extends Class105 (Sprite/texture); implements Interface19; 19 native methods |
| `ja` | `OpenGLMatrix` | extends Class101 (Matrix); implements Interface19; 17 native methods |
| `n` | `BitmapFontImpl` | extends BitmapFont; implements Interface19; 4 native font methods |
| `na` | `ShaderImpl` | extends aa (Shader); implements Interface19; 2 native methods |
| `oa` | `OpenGLToolkit` | extends GraphicsToolkit; implements Interface19, Interface19 |
| `p` | `ToolkitNode` | extends Node; implements Interface19; 5 native methods |
| `r` | `HashNodeImpl` | abstract; extends HashNode |
| `r_Sub1` | `CacheNodeSub1` | extends r (HashNodeImpl) |
| `r_Sub2` | `CacheNodeSub2` | extends r (HashNodeImpl) |
| `s` | `BufferCache` | abstract base; Hash/Cache/Buffer usage |
| `s_Sub1` | `SSub1` | extends s (BufferCache) |
| `s_Sub2` | `SSub2` | extends s (BufferCache) |
| `s_Sub3` | `SSub3` | extends s (BufferCache) |
| `t` | `BufferToolkit` | extends s (BufferCache); implements Interface19; 10 native methods |
| `wa` | `Interface4Impl` | implements Interface4; 2 native methods |
| `xa` | `DualToolkit` | implements Interface13, Interface19; 2 native methods |
| `ya` | `NativeHandle` | root class; no distinctive extends/strings |
| `za` | `NodeBase` | abstract; extends Node |
| `za_Sub1` | `NodeBaseSub1` | extends za (NodeBase) |
| `za_Sub2` | `NodeBaseSub2` | extends za (NodeBase) |
