package sd.fomin.gerbera.types;

import sd.fomin.gerbera.util.HexUtils;

public class UInt {

    private byte[] litEndBytes;

    private UInt(int value) {
        this.litEndBytes = new byte[] {
                (byte) value,
                (byte) (value >> 8),
                (byte) (value >> 16),
                (byte) (value >> 24),
        };
    }

    public static UInt of(int value) {
        return new UInt(value);
    }

    public byte[] asLitEndBytes() {
        return litEndBytes;
    }

    public byte asByte() {
        if (litEndBytes[1] != 0 || litEndBytes[2] != 0 || litEndBytes[3] != 0) {
            throw new IllegalStateException("The satoshi is more than 255 and can't be represented as one byte");
        }
        return litEndBytes[0];
    }

    @Override
    public String toString() {
        return HexUtils.asString(litEndBytes);
    }
}
