# Decode — `android/app/src/main/java/jagdx` (28 classes)

> **Subsistema:** Gráficos — DirectX/JagDX

Total: 28

---

## D3DADAPTER_IDENTIFIER.java — Classe D3DADAPTER_IDENTIFIER

- **Arquivo:** `android/app/src/main/java/jagdx/D3DADAPTER_IDENTIFIER.java`
- **Declaração:** `class D3DADAPTER_IDENTIFIER`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 23 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `DeviceName` | `String` |  |
| `Description` | `String` |  |
| `Driver` | `String` |  |

### Métodos

_—_

### Relações

`jagdx`

---

## D3DCAPS.java — Classe D3DCAPS

- **Arquivo:** `android/app/src/main/java/jagdx/D3DCAPS.java`
- **Declaração:** `class D3DCAPS`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 139 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `VS20Caps` | `D3DVSHADERCAPS2_0` |  |
| `PS20Caps` | `D3DPSHADERCAPS2_0` |  |

### Métodos

_—_

### Relações

`jagdx`

---

## D3DDISPLAYMODE.java — Classe D3DDISPLAYMODE

- **Arquivo:** `android/app/src/main/java/jagdx/D3DDISPLAYMODE.java`
- **Declaração:** `class D3DDISPLAYMODE`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`jagdx`

---

## D3DLIGHT.java — Classe D3DLIGHT

- **Arquivo:** `android/app/src/main/java/jagdx/D3DLIGHT.java`
- **Declaração:** `class D3DLIGHT extends os`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 32 linhas

### Campos

_—_

### Métodos

- **D3DLIGHT(ti arg0)**
- **SetSpotParams(float arg0, float arg1, float arg2)**
- **SetPosition(float arg0, float arg1, float arg2)**
- **SetRange(float arg0)**
- **SetSpecular(float arg0, float arg1, float arg2, float arg3)**
- **Init()**
- **SetDiffuse(float arg0, float arg1, float arg2, float arg3)**
- **SetType(int arg0)**
- **SetAttenuation(float arg0, float arg1, float arg2)**
- **SetAmbient(float arg0, float arg1, float arg2, float arg3)**
- **SetDirection(float arg0, float arg1, float arg2)**

### Relações

`jaclib.peer.os`, `jaclib.peer.ti`, `jagdx`

---

## D3DPRESENT_PARAMETERS.java — Classe D3DPRESENT_PARAMETERS

- **Arquivo:** `android/app/src/main/java/jagdx/D3DPRESENT_PARAMETERS.java`
- **Declaração:** `class D3DPRESENT_PARAMETERS`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 39 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `DeviceWindow` | `Canvas` |  |

### Métodos

- **D3DPRESENT_PARAMETERS(Canvas arg0)**

### Relações

`jagdx`, `voidawt.Canvas`

---

## D3DPSHADERCAPS2_0.java — Classe D3DPSHADERCAPS2_0

- **Arquivo:** `android/app/src/main/java/jagdx/D3DPSHADERCAPS2_0.java`
- **Declaração:** `class D3DPSHADERCAPS2_0`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 15 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`jagdx`

---

## D3DVSHADERCAPS2_0.java — Classe D3DVSHADERCAPS2_0

- **Arquivo:** `android/app/src/main/java/jagdx/D3DVSHADERCAPS2_0.java`
- **Declaração:** `class D3DVSHADERCAPS2_0`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`jagdx`

---

## GeometryBuffer.java — Classe GeometryBuffer

- **Arquivo:** `android/app/src/main/java/jagdx/GeometryBuffer.java`
- **Declaração:** `class GeometryBuffer extends os implements Buffer`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 31 linhas

### Campos

_—_

### Métodos

- **GeometryBuffer(ti arg0)**
- **putub(byte[] arg0, int arg1, int arg2, int arg3)**
- **getub(byte[] arg0, int arg1, int arg2, int arg3)**
- **a(byte[] arg0, int arg1, int arg2, int arg3)**
- **getAddress()**
- **init()**
- **getSize()**

### Relações

`jaclib.memory.Buffer`, `jaclib.peer.os`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3D.java — Classe IDirect3D

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3D.java`
- **Declaração:** `class IDirect3D extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 74 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `b` | `ti` |  |
| `local4` | `IDirect3D` |  |
| `local5` | `IDirect3DDevice` |  |
| `local3` | `D3DCAPS` |  |
| `local3` | `D3DADAPTER_IDENTIFIER` |  |

### Métodos

- **_Direct3DCreate(int arg0, IDirect3D arg1)**
- **a(int arg0, ti arg1)**
- **IDirect3D(ti arg0)**
- **CheckDeviceFormat(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5)**
- **a(int arg0, int arg1, Canvas arg2, int arg3, D3DPRESENT_PARAMETERS arg4)**
- **a(int arg0, D3DDISPLAYMODE arg1)**
- **_GetAdapterDisplayMode(int arg0, D3DDISPLAYMODE arg1)**
- **_GetAdapterIdentifier(int arg0, int arg1, D3DADAPTER_IDENTIFIER arg2)**
- **CheckDepthStencilMatch(int arg0, int arg1, int arg2, int arg3, int arg4)**
- **_GetDeviceCaps(int arg0, int arg1, D3DCAPS arg2)**
- **b(int arg0, int arg1)**
- **CheckDeviceMultiSampleType(int arg0, int arg1, int arg2, boolean arg3, int arg4)**
- **_CreateDevice(int arg0, int arg1, Canvas arg2, int arg3, D3DPRESENT_PARAMETERS arg4, IDirect3DDevice arg5)**
- **a(int arg0, int arg1)**
- **CheckDeviceType(int arg0, int arg1, int arg2, int arg3, boolean arg4)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`, `voidawt.Canvas`

---

## IDirect3DBaseTexture.java — Classe IDirect3DBaseTexture

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DBaseTexture.java`
- **Declaração:** `class IDirect3DBaseTexture extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **IDirect3DBaseTexture(ti arg0)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DCubeTexture.java — Classe IDirect3DCubeTexture

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DCubeTexture.java`
- **Declaração:** `class IDirect3DCubeTexture extends IDirect3DBaseTexture`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 14 linhas

### Campos

_—_

### Métodos

- **IDirect3DCubeTexture(ti arg0)**
- **UnlockRect(int arg0, int arg1)**
- **LockRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, PixelBuffer arg7)**

### Relações

`jaclib.peer.ti`, `jagdx`

---

## IDirect3DDevice.java — Classe IDirect3DDevice

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DDevice.java`
- **Declaração:** `class IDirect3DDevice extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 266 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `c` | `float[]` |  |
| `b` | `ti` |  |
| `local10` | `IDirect3DPixelShader` |  |
| `local5` | `IDirect3DSurface` |  |
| `local5` | `IDirect3DTexture` |  |
| `local5` | `IDirect3DCubeTexture` |  |
| `local5` | `IDirect3DVolumeTexture` |  |
| `local5` | `IDirect3DEventQuery` |  |
| `local5` | `IDirect3DSurface` |  |
| `local11` | `IDirect3DVertexShader` |  |
| `local5` | `IDirect3DSwapChain` |  |
| `local5` | `IDirect3DSurface` |  |

### Métodos

- **IDirect3DDevice(ti arg0)**
- **SetLight(int arg0, D3DLIGHT arg1)**
- **SetPixelShaderConstantF(int arg0, float[] arg1, int arg2)**
- **_GetDepthStencilSurface(IDirect3DSurface arg0)**
- **a(byte[] arg0)**
- **SetTextureStageState(int arg0, int arg1, int arg2)**
- **_CreateEventQuery(IDirect3DEventQuery arg0)**
- **_CreateDepthStencilSurface(int arg0, int arg1, int arg2, int arg3, int arg4, boolean arg5, IDirect3DSurface arg6)**
- **a(int arg0, int arg1, int arg2, int arg3, IDirect3DVertexBuffer arg4)**
- **_GetSwapChain(int arg0, IDirect3DSwapChain arg1)**
- **a(int arg0, float arg1)**
- **c()**
- **SetRenderState(int arg0, int arg1)**
- **a(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5)**
- **LightEnable(int arg0, boolean arg1)**
- **SetTransform(int arg0, float[] arg1)**
- **_GetBackBuffer(int arg0, int arg1, int arg2, IDirect3DSurface arg3)**
- **a(int arg0, int arg1, int arg2, int arg3, int arg4)**
- **_CreateIndexBuffer(int arg0, int arg1, int arg2, int arg3, IDirect3DIndexBuffer arg4)**
- **SetRenderStatef(int arg0, float arg1)**
- **DrawIndexedPrimitive(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5)**
- **_CreateTexture(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, IDirect3DTexture arg6)**
- **a(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6)**
- **SetSamplerState(int arg0, int arg1, int arg2)**
- **a(int arg0, float arg1, float arg2, float arg3, float arg4)**
- **SetRenderStateb(int arg0, boolean arg1)**
- **_CreatePixelShader(byte[] arg0, IDirect3DPixelShader arg1)**
- **SetVertexShaderConstantF(int arg0, float[] arg1, int arg2)**
- **Reset(D3DPRESENT_PARAMETERS arg0)**
- **SetStreamSource(int arg0, IDirect3DVertexBuffer arg1, int arg2, int arg3)**
- **b()**
- **BeginScene()**
- **SetVertexShader(IDirect3DVertexShader arg0)**
- **TestCooperativeLevel()**
- **b(int arg0, float arg1, float arg2, float arg3, float arg4)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DEventQuery.java — Classe IDirect3DEventQuery

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DEventQuery.java`
- **Declaração:** `class IDirect3DEventQuery extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 15 linhas

### Campos

_—_

### Métodos

- **IDirect3DEventQuery(ti arg0)**
- **Issue()**
- **IsSignaled()**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DIndexBuffer.java — Classe IDirect3DIndexBuffer

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DIndexBuffer.java`
- **Declaração:** `class IDirect3DIndexBuffer extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 22 linhas

### Campos

_—_

### Métodos

- **IDirect3DIndexBuffer(ti arg0)**
- **a()**
- **_Update(long arg0, int arg1, int arg2)**
- **Lock(int arg0, int arg1, int arg2, GeometryBuffer arg3)**
- **Unlock()**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DPixelShader.java — Classe IDirect3DPixelShader

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DPixelShader.java`
- **Declaração:** `class IDirect3DPixelShader extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **IDirect3DPixelShader(ti arg0)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DSurface.java — Classe IDirect3DSurface

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DSurface.java`
- **Declaração:** `class IDirect3DSurface extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 15 linhas

### Campos

_—_

### Métodos

- **IDirect3DSurface(ti arg0)**
- **LockRect(int arg0, int arg1, int arg2, int arg3, int arg4, PixelBuffer arg5)**
- **UnlockRect()**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DSwapChain.java — Classe IDirect3DSwapChain

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DSwapChain.java`
- **Declaração:** `class IDirect3DSwapChain extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 27 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `b` | `ti` |  |
| `local5` | `IDirect3DSurface` |  |

### Métodos

- **IDirect3DSwapChain(ti arg0)**
- **Present(int arg0)**
- **_GetBackBuffer(int arg0, int arg1, IDirect3DSurface arg2)**
- **a(int arg0, int arg1)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DTexture.java — Classe IDirect3DTexture

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DTexture.java`
- **Declaração:** `class IDirect3DTexture extends IDirect3DBaseTexture`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 14 linhas

### Campos

_—_

### Métodos

- **IDirect3DTexture(ti arg0)**
- **UnlockRect(int arg0)**
- **LockRect(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, PixelBuffer arg6)**

### Relações

`jaclib.peer.ti`, `jagdx`

---

## IDirect3DVertexBuffer.java — Classe IDirect3DVertexBuffer

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DVertexBuffer.java`
- **Declaração:** `class IDirect3DVertexBuffer extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 36 linhas

### Campos

_—_

### Métodos

- **IDirect3DVertexBuffer(ti arg0)**
- **a(Source arg0, int arg1, int arg2, int arg3, int arg4)**
- **_Update(long arg0, int arg1, int arg2, int arg3)**
- **a()**
- **Lock(int arg0, int arg1, int arg2, GeometryBuffer arg3)**
- **Unlock()**

### Relações

`jaclib.memory.Source`, `jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DVertexDeclaration.java — Classe IDirect3DVertexDeclaration

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DVertexDeclaration.java`
- **Declaração:** `class IDirect3DVertexDeclaration extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **IDirect3DVertexDeclaration(ti arg0)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DVertexShader.java — Classe IDirect3DVertexShader

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DVertexShader.java`
- **Declaração:** `class IDirect3DVertexShader extends IUnknown`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **IDirect3DVertexShader(ti arg0)**

### Relações

`jaclib.peer.IUnknown`, `jaclib.peer.ti`, `jagdx`

---

## IDirect3DVolumeTexture.java — Classe IDirect3DVolumeTexture

- **Arquivo:** `android/app/src/main/java/jagdx/IDirect3DVolumeTexture.java`
- **Declaração:** `class IDirect3DVolumeTexture extends IDirect3DBaseTexture`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 14 linhas

### Campos

_—_

### Métodos

- **IDirect3DVolumeTexture(ti arg0)**
- **LockBox(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, PixelBuffer arg8)**
- **UnlockBox(int arg0)**

### Relações

`jaclib.peer.ti`, `jagdx`

---

## PixelBuffer.java — Classe PixelBuffer

- **Arquivo:** `android/app/src/main/java/jagdx/PixelBuffer.java`
- **Declaração:** `class PixelBuffer extends os implements Buffer`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 56 linhas

### Campos

_—_

### Métodos

- **PixelBuffer(ti arg0)**
- **b(int[] arg0, int arg1, int arg2, int arg3)**
- **getub(byte[] arg0, int arg1, int arg2, int arg3)**
- **getSlicePitch()**
- **geti(int[] arg0, int arg1, int arg2, int arg3)**
- **a(int[] arg0, int arg1, int arg2, int arg3)**
- **putub(byte[] arg0, int arg1, int arg2, int arg3)**
- **getAddress()**
- **puti(int[] arg0, int arg1, int arg2, int arg3)**
- **a(byte[] arg0, int arg1, int arg2, int arg3)**
- **getRowPitch()**
- **init()**
- **getSize()**

### Relações

`jaclib.memory.Buffer`, `jaclib.peer.os`, `jaclib.peer.ti`, `jagdx`

---

## VertexElementCollection.java — Classe VertexElementCollection

- **Arquivo:** `android/app/src/main/java/jagdx/VertexElementCollection.java`
- **Declaração:** `class VertexElementCollection extends os`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 20 linhas

### Campos

_—_

### Métodos

- **VertexElementCollection(ti arg0)**
- **addElement(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5)**
- **init()**
- **reset()**
- **finish()**

### Relações

`jaclib.peer.os`, `jaclib.peer.ti`, `jagdx`

---

## fda.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jagdx/fda.java`
- **Declaração:** `class fda extends RuntimeException`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 6 linhas

### Campos

_—_

### Métodos

- **fda()**
- **fda(String message)**

### Relações

`jagdx`

---

## lh.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jagdx/lh.java`
- **Declaração:** `class lh`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

- **a(byte arg0, int arg1)**
- **a(int arg0, boolean arg1)**

### Relações

`jagdx`

---

## sja.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jagdx/sja.java`
- **Declaração:** `class sja extends RuntimeException`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 12 linhas

### Campos

_—_

### Métodos

- **sja(String arg0)**
- **sja()**

### Relações

`jagdx`

---

## ue.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jagdx/ue.java`
- **Declaração:** `class ue`
- **Package:** `jagdx`
- **Subsistema:** Gráficos — DirectX/JagDX
- **Tamanho:** 6 linhas

### Campos

_—_

### Métodos

- **a(byte arg0, int arg1)**
- **a(int arg0, boolean arg1)**

### Relações

`jagdx`

---

