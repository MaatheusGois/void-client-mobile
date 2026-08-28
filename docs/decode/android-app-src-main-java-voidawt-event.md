# Decode — `android/app/src/main/java/voidawt/event` (14 classes)

> **Subsistema:** UI — AWT reimplementado (voidawt)

Total: 14

---

## AWTEvent.java — Classe AWTEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/AWTEvent.java`
- **Declaração:** `class AWTEvent extends EventObject`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 25 linhas

### Campos

_—_

### Métodos

- **AWTEvent(Object source, int id)**
- **getID()**
- **consume()**
- **isConsumed()**

### Relações

`voidawt.event`

---

## ActionEvent.java — Classe ActionEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/ActionEvent.java`
- **Declaração:** `class ActionEvent extends AWTEvent`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 9 linhas

### Campos

_—_

### Métodos

- **ActionEvent(Object source, int id, String command)**

### Relações

`voidawt.event`

---

## ActionListener.java — Classe ActionListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/ActionListener.java`
- **Declaração:** `interface ActionListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 5 linhas

### Campos

_—_

### Métodos

- **actionPerformed(ActionEvent e)**

### Relações

`voidawt.event`

---

## FocusEvent.java — Classe FocusEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/FocusEvent.java`
- **Declaração:** `class FocusEvent extends AWTEvent`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 12 linhas

### Campos

_—_

### Métodos

- **FocusEvent(Component source, int id)**

### Relações

`voidawt.Component`, `voidawt.event`

---

## FocusListener.java — Classe FocusListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/FocusListener.java`
- **Declaração:** `interface FocusListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 7 linhas

### Campos

_—_

### Métodos

- **focusGained(FocusEvent e)**
- **focusLost(FocusEvent e)**

### Relações

`voidawt.event`

---

## KeyEvent.java — Classe KeyEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/KeyEvent.java`
- **Declaração:** `class KeyEvent extends AWTEvent`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 40 linhas

### Campos

_—_

### Métodos

- **KeyEvent(Component source, int id, long when, int modifiers, int keyCode, char keyChar)**
- **getKeyCode()**
- **getKeyChar()**

### Relações

`voidawt.Component`, `voidawt.event`

---

## KeyListener.java — Classe KeyListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/KeyListener.java`
- **Declaração:** `interface KeyListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 9 linhas

### Campos

_—_

### Métodos

- **keyTyped(KeyEvent e)**
- **keyPressed(KeyEvent e)**
- **keyReleased(KeyEvent e)**

### Relações

`voidawt.event`

---

## MouseEvent.java — Classe MouseEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/MouseEvent.java`
- **Declaração:** `class MouseEvent extends AWTEvent`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 79 linhas

### Campos

_—_

### Métodos

- **MouseEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger)**
- **MouseEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger, int button)**
- **getX()**
- **getY()**
- **getClickCount()**
- **isPopupTrigger()**
- **getButton()**
- **getModifiers()**
- **isMetaDown()**
- **isShiftDown()**
- **isControlDown()**
- **isAltDown()**

### Relações

`voidawt.Component`, `voidawt.event`

---

## MouseListener.java — Classe MouseListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/MouseListener.java`
- **Declaração:** `interface MouseListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

- **mouseClicked(MouseEvent e)**
- **mousePressed(MouseEvent e)**
- **mouseReleased(MouseEvent e)**
- **mouseEntered(MouseEvent e)**
- **mouseExited(MouseEvent e)**

### Relações

`voidawt.event`

---

## MouseMotionListener.java — Classe MouseMotionListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/MouseMotionListener.java`
- **Declaração:** `interface MouseMotionListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 7 linhas

### Campos

_—_

### Métodos

- **mouseDragged(MouseEvent e)**
- **mouseMoved(MouseEvent e)**

### Relações

`voidawt.event`

---

## MouseWheelEvent.java — Classe MouseWheelEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/MouseWheelEvent.java`
- **Declaração:** `class MouseWheelEvent extends MouseEvent`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 16 linhas

### Campos

_—_

### Métodos

- **MouseWheelEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger, int wheelRotation)**
- **getWheelRotation()**

### Relações

`voidawt.Component`, `voidawt.event`

---

## MouseWheelListener.java — Classe MouseWheelListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/MouseWheelListener.java`
- **Declaração:** `interface MouseWheelListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 5 linhas

### Campos

_—_

### Métodos

- **mouseWheelMoved(MouseWheelEvent e)**

### Relações

`voidawt.event`

---

## WindowEvent.java — Classe WindowEvent

- **Arquivo:** `android/app/src/main/java/voidawt/event/WindowEvent.java`
- **Declaração:** `class WindowEvent extends AWTEvent`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 17 linhas

### Campos

_—_

### Métodos

- **WindowEvent(Window source, int id)**

### Relações

`voidawt.Window`, `voidawt.event`

---

## WindowListener.java — Classe WindowListener

- **Arquivo:** `android/app/src/main/java/voidawt/event/WindowListener.java`
- **Declaração:** `interface WindowListener`
- **Package:** `voidawt.event`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 17 linhas

### Campos

_—_

### Métodos

- **windowOpened(WindowEvent e)**
- **windowClosing(WindowEvent e)**
- **windowClosed(WindowEvent e)**
- **windowIconified(WindowEvent e)**
- **windowDeiconified(WindowEvent e)**
- **windowActivated(WindowEvent e)**
- **windowDeactivated(WindowEvent e)**

### Relações

`voidawt.event`

---

