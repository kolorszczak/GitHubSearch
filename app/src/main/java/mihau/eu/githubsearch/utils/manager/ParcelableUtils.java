package mihau.eu.githubsearch.utils.manager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ParcelableUtils {


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

    public static void writeIntegerHelper(Parcel out, Integer s) {
        out.writeByte((byte) (s != null ? 1 : 0));
        if (s != null) {
            out.writeInt(s);
        }
    }

    public static Integer readIntegerHelper(Parcel in) {
        boolean isPresent = in.readByte() == 1;
        return isPresent ? in.readInt() : null;
    }

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

    public static void writeBooleanHelper(Parcel out, boolean b) {
        if (b) {
            out.writeByte((byte) 1);
        } else {
            out.writeByte((byte) 0);
        }
    }

    public static boolean readBooleanHelper(Parcel in) {
        return in.readByte() == 1;
    }

    public static <T extends Parcelable> void writeParcelableHelper(Parcel out, T parcel, int flags) {
        out.writeByte((byte) (parcel != null ? 1 : 0));
        if (parcel != null) {
            out.writeParcelable(parcel, flags);
        }
    }

    public static <T extends Parcelable> T readParcelableHelper(Parcel in, Class c) {
        boolean isPresent = in.readByte() == 1;
        return isPresent ? in.readParcelable(c.getClassLoader()) : null;
    }

    public static <T extends Parcelable> void writeParcelableListHelper(Parcel out, List<T> list) {
        if (list == null || list.size() == 0)
            out.writeInt(0);
        else {
            out.writeInt(list.size());
            final Class<?> objectsType = list.get(0).getClass();
            out.writeSerializable(objectsType);
            out.writeList(list);
        }
    }

    public static <T extends Parcelable> List<T> readParcelableListHelper(Parcel in) {
        int size = in.readInt();
        if (size == 0) {
            return new ArrayList<>();
        } else {
            Class<?> type = (Class<?>) in.readSerializable();
            List<T> list = new ArrayList<>(size);
            in.readList(list, type.getClassLoader());
            return list;
        }
    }
}