package voidawt;

import voidawt.event.AWTEvent;

import java.util.LinkedList;

public class EventQueue {
    private final LinkedList<AWTEvent> events = new LinkedList<AWTEvent>();

    public synchronized AWTEvent peekEvent() {
        return events.peekFirst();
    }

    public synchronized AWTEvent getNextEvent() throws InterruptedException {
        while (events.isEmpty()) {
            wait();
        }
        return events.removeFirst();
    }

    public synchronized void postEvent(AWTEvent event) {
        events.addLast(event);
        notifyAll();
    }
}
