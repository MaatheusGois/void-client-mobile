package voidsound.sampled;

/**
 * Marker for an output (playback) line. {@code Class279_Sub1} requests this
 * class via {@code DataLine.Info}; {@link AudioSystem#getLine} returns
 * {@link PcmSourceDataLine}.
 */
public interface SourceDataLine extends DataLine {
}
