package mihau.eu.githubsearch.utils.manager;

import android.os.Parcel;

public class ParcelableUtils {

    //region String
    public static void writeStringHelper(Parcel out, String s) {
        out.writeByte((byte) (s != null ? 1 : 0));
        if (s != null) {
            out.writeString(s);
        }
    }

    public static String readStringHelper(Parcel in) {
        boolean isPresent = in.readByte() == 1;
        return isPresent ? in.readString() : null;
    }
    //endregion

    //region Long
    public static void writeLongHelper(Parcel out, Long s) {
        out.writeByte((byte) (s != null ? 1 : 0));
        if (s != null) {
            out.writeLong(s);
        }
    }

    public static Long readLongHelper(Parcel in) {
        boolean isPresent = in.readByte() == 1;
        return isPresent ? in.readLong() : null;
    }
    //endregion

    //region Double
    public static void writeDoubleHelper(Parcel out, Double s) {
        out.writeByte((byte) (s != null ? 1 : 0));
        if (s != null) {
            out.writeDouble(s);
        }
    }

    public static Double readDoubleHelper(Parcel in) {
        boolean isPresent = in.readByte() == 1;
        return isPresent ? in.readDouble() : null;
    }
    //endregion

    //region Float
    public static void writeFloatHelper(Parcel out, Float s) {
        out.writeByte((byte) (s != null ? 1 : 0));
        if (s != null) {
            out.writeFloat(s);
        }
    }

    public static Float readFloatHelper(Parcel in) {
        boolean isPresent = in.readByte() == 1;
        return isPresent ? in.readFloat() : null;
    }
    //endregion

    //region Boolean
    public static void writeBooleanHelper(Parcel out, boolean b) {
        if (b) {
            out.writeByte((byte) 1);
        } else {
            out.writeByte((byte) 0);
        }
    }

    public static boolean readBooleanHelper(Parcel in) {
        if (in.readByte() == 1) {
            return true;
        } else {
            return false;
        }
    }
    //endregion
}