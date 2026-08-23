package voidawt.image;

public class DirectColorModel extends ColorModel {
    public DirectColorModel(int bits, int rmask, int gmask, int bmask) {
        super(bits);
    }

    public DirectColorModel(int bits, int rmask, int gmask, int bmask, int amask) {
        super(bits);
    }

    public SampleModel createCompatibleSampleModel(int w, int h) {
        return new SampleModel(w, h);
    }
}
