# Decode — `android/app/src/main/java/jaggl` (2 classes)

> **Subsistema:** Gráficos — OpenGL (jaggl)

Total: 2

---

## MapBuffer.java — Classe MapBuffer

- **Arquivo:** `android/app/src/main/java/jaggl/MapBuffer.java`
- **Declaração:** `class MapBuffer extends NativeBuffer`
- **Package:** `jaggl`
- **Subsistema:** Gráficos — OpenGL (jaggl)
- **Tamanho:** 44 linhas

### Campos

_—_

### Métodos

- **a(int arg0, int arg1, int arg2)**
- **a()**
- **a(byte[] arg0, int arg1, int arg2, int arg3)**
- **b()**

### Relações

`jaclib.memory.NativeBuffer`, `jaggl`

---

## OpenGL.java — Classe OpenGL

- **Arquivo:** `android/app/src/main/java/jaggl/OpenGL.java`
- **Declaração:** `class OpenGL`
- **Package:** `jaggl`
- **Subsistema:** Gráficos — OpenGL (jaggl)
- **Tamanho:** 702 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `a` | `Hashtable` |  |
| `c` | `Hashtable` |  |
| `b` | `Thread` |  |
| `local10` | `String` |  |
| `local47` | `String` |  |
| `local26` | `String` |  |
| `local1` | `Thread` |  |
| `local10` | `OpenGL` |  |

### Métodos

- **glPixelZoom(float xfactor, float yfactor)**
- **glUniform1iARB(int location, int v0)**
- **glColor3f(float red, float green, float blue)**
- **glBufferDataARBa(int arg0, int arg1, long arg2, int arg3)**
- **glMultiTexCoord2i(int target, int s, int t)**
- **glTexCoord3f(float s, float t, float r)**
- **glReadPixelsi(int x, int y, int width, int height, int format, int type, int[] pixels, int pixelsOffset)**
- **glRasterPos2i(int x, int y)**
- **glNormal3f(float nx, float ny, float nz)**
- **glGetTexImageub(int target, int level, int format, int type, byte[] pixels, int pixelsOffset)**
- **glCreateProgramObjectARB()**
- **glVertexPointer(int size, int type, int stride, long pointer)**
- **glShaderSourceARB(long shaderObj, String string)**
- **glProgramStringARB(int target, int format, String string)**
- **glGetProgramivARB(int target, int pname, int[] params, int paramsOffset)**
- **glGenFramebuffersEXT(int n, int[] framebuffers, int framebuffersOffset)**
- **glTexParameteri(int target, int pname, int param)**
- **glDeleteBuffersARB(int n, int[] buffers, int buffersOffset)**
- **glDisable(int cap)**
- **glTexSubImage2Di(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int[] pixels, int pixelsOffset)**
- **glGenBuffersARB(int n, int[] buffers, int buffersOffset)**
- **glTexImage2Dub(int target, int level, int internalformat, int width, int height, int border, int format, int type, byte[] pixels, int pixelsOffset)**
- **glGetInfoLogARB(long obj, int maxLength, int[] length, int lengthOffset, byte[] infoLog, int infoLogOffset)**
- **glColor4ub(byte red, byte green, byte blue, byte alpha)**
- **glUniform1fARB(int location, float v0)**
- **glVertex3f(float x, float y, float z)**
- **glTexCoord2f(float s, float t)**
- **glDeleteObjectARB(long obj)**
- **glDetachObjectARB(long containerObj, long attachedObj)**
- **glColor4f(float red, float green, float blue, float alpha)**
- **glTexImage3Dub(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, byte[] pixels, int pixelsOffset)**
- **glMapBufferARB(int target, int access)**
- **glEnable(int cap)**
- **glLinkProgramARB(long programObj)**
- **glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer)**

### Relações

`jaggl`, `voidawt.Canvas`

---

