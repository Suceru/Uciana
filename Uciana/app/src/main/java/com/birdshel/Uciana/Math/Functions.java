package com.birdshel.Uciana.Math;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.SparseArray;

import com.birdshel.Uciana.GameData;
import com.birdshel.Uciana.R;
import com.birdshel.Uciana.Uciana;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class Functions {
    public static int intersectionSide;
    public static Random random = new Random();

    public static boolean CopyPdfToExternalStorage(Uciana uciana, String str) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/uciana/manuals");
            file.mkdirs();
            File file2 = new File(file.getAbsolutePath() + File.separator + str);
            if (file2.exists()) {
                file2.delete();
            }
            AssetManager assets = uciana.getAssets();
            InputStream open = assets.open("manuals/" + str);
            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/uciana/manuals/" + str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }

    public static <C> List<C> asList(SparseArray<C> sparseArray) {
        ArrayList arrayList = new ArrayList(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }
        return arrayList;
    }

    private static int checkLineSide(int i, Point point, Point point2, Point point3, Point point4, int i2) {
        int min;
        if (!doesLineIntersectLine(point, point2, point3, point4) || (min = Math.min(point.getDistance(point3), point.getDistance(point3))) >= i2) {
            return i2;
        }
        intersectionSide = i;
        return min;
    }

    public static <T> boolean contains(T[] tArr, T t) {
        for (Object obj : tArr) {
            if (obj == t) {
                return true;
            }
            if (t != null && t.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private static int convertOffsetToCubeCoordinates(int i, int i2) {
        return (-i) - (i2 - ((i - (i & 1)) / 2));
    }

    public static String convertSecondsIntoHMS(long j, boolean z) {
        String str;
        int i = (int) (j / 3600);
        int i2 = (int) (j % 3600);
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        String str2 = "";
        if (i > 0) {
            String str3 = "" + Integer.toString(i);
            if (i == 1) {
                str2 = str3 + " " + GameData.activity.getString(R.string.time_hour) + " ";
            } else {
                str2 = str3 + " " + GameData.activity.getString(R.string.time_hours) + " ";
            }
        }
        if (i3 == 1) {
            str = str2 + i3 + " " + GameData.activity.getString(R.string.time_min) + " ";
        } else {
            str = str2 + i3 + " " + GameData.activity.getString(R.string.time_mins) + " ";
        }
        if (z) {
            return str + i4 + " " + GameData.activity.getString(R.string.time_secs) + " ";
        }
        return str;
    }

    public static boolean doesLineInterceptSquare(Point point, Point point2, Point point3, int i) {
        intersectionSide = 0;
        float f2 = i;
        checkLineSide(3, point, point2, point3, new Point(point3.getX(), point3.getY() + f2), checkLineSide(3, point, point2, new Point(point3.getX(), point3.getY() + f2), new Point(point3.getX() + f2, point3.getY() + f2), checkLineSide(2, point, point2, new Point(point3.getX() + f2, point3.getY()), new Point(point3.getX() + f2, point3.getY() + f2), checkLineSide(1, point, point2, point3, new Point(point3.getX() + f2, point3.getY()), 9999))));
        return intersectionSide != 0;
    }

    private static boolean doesLineIntersectLine(Point point, Point point2, Point point3, Point point4) {
        float x = point2.getX() - point.getX();
        float y = point2.getY() - point.getY();
        float x2 = point4.getX() - point3.getX();
        float y2 = point4.getY() - point3.getY();
        float f2 = ((-x2) * y) + (x * y2);
        if (f2 == 0.0f) {
            return false;
        }
        float x3 = (((-y) * (point.getX() - point3.getX())) + (x * (point.getY() - point3.getY()))) / f2;
        float y3 = ((x2 * (point.getY() - point3.getY())) - (y2 * (point.getX() - point3.getX()))) / f2;
        return x3 >= 0.0f && x3 <= 1.0f && y3 >= 0.0f && y3 <= 1.0f;
    }

    public static float getAngle(Point point, Point point2) {
        float degrees = (float) Math.toDegrees(Math.atan2(point2.getY() - point.getY(), point2.getX() - point.getX()));
        return degrees < 0.0f ? degrees + 360.0f : degrees;
    }

    public static int getHexDistance(Point point, Point point2) {
        return getHexDistance((int) point.getX(), (int) point.getY(), (int) point2.getX(), (int) point2.getY());
    }

    public static Point getIntersectionPoint(Point point, Point point2, Point point3, int i, int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                float f2 = i;
                return getIntersectionPoint(point, point2, new Point(point3.getX() + f2, point3.getY()), new Point(point3.getX() + f2, point3.getY() + f2));
            } else if (i2 == 3) {
                float f3 = i;
                return getIntersectionPoint(point, point2, new Point(point3.getX(), point3.getY() + f3), new Point(point3.getX() + f3, point3.getY() + f3));
            } else if (i2 == 4) {
                return getIntersectionPoint(point, point2, point3, new Point(point3.getX(), point3.getY() + i));
            } else {
                throw new AssertionError("Invalid side");
            }
        }
        return getIntersectionPoint(point, point2, point3, new Point(point3.getX() + i, point3.getY()));
    }

    public static Object getItemByPercent(Map<Object, Integer> map) {
        int nextInt = random.nextInt(100) + 1;
        Object value = map.entrySet().iterator().next().getValue();
        int i = 0;
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Integer value2 = entry.getValue();
            if (nextInt > i && nextInt <= value2.intValue() + i) {
                value = entry.getKey();
            }
            i += value2.intValue();
        }
        if (i == 100) {
            return value;
        }
        throw new AssertionError("getItemByPercent did not total up to 100. First item in list is: " + map.entrySet().iterator().next().getKey().toString());
    }

    public static int getRandom(int i, int i2) {
        return random.nextInt(i2 - i) + i;
    }

    public static int getResId(String str, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            return declaredField.getInt(declaredField);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int hashKeyFromPair(short s, short s2) {
        long j = s + s2;
        return ((int) ((j * (1 + j)) / 2)) + s;
    }

    public static boolean percent(int i) {
        if (i == 100) {
            return true;
        }
        int nextInt = random.nextInt(100) + 1;
        int i2 = i / 2;
        return nextInt > 50 - i2 && nextInt < i2 + 50;
    }

    public static Point randomPointInsideCircle(double d2) {
        double nextDouble = random.nextDouble() * 3.141592653589793d * 2.0d;
        double sqrt = Math.sqrt(random.nextDouble()) * (d2 / 2.0d);
        return new Point((float) (Math.cos(nextDouble) * sqrt), (float) (sqrt * Math.sin(nextDouble)));
    }

    public static Point rotate(float f2, float f3, float f4, float f5, float f6) {
        double radians = Math.toRadians(f6);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d2 = f4 - f2;
        Double.isNaN(d2);
        double d3 = f5 - f3;
        Double.isNaN(d3);
        double d4 = (cos * d2) - (sin * d3);
        double d5 = f2;
        Double.isNaN(d5);
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d6 = f3;
        Double.isNaN(d6);
        return new Point((float) (d4 + d5), (float) ((sin * d2) + (cos * d3) + d6));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> Map<K, V> sortMapByValueDesc(Map<K, V> map) {
        LinkedList<Map.Entry> linkedList = new LinkedList(map.entrySet());
        Collections.sort(linkedList, new Comparator<Object>() { // from class: com.birdshel.Uciana.Math.Functions.1
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                return ((Comparable) ((Map.Entry) obj2).getValue()).compareTo(((Map.Entry) obj).getValue());
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : linkedList) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    public static float getRandom(float f2, float f3) {
        return (random.nextFloat() * (f3 - f2)) + f2;
    }

    public static int getHexDistance(int i, int i2, int i3, int i4) {
        int convertOffsetToCubeCoordinates = convertOffsetToCubeCoordinates(i, i2);
        int convertOffsetToCubeCoordinates2 = convertOffsetToCubeCoordinates(i3, i4);
        return Math.max(Math.abs(i - i3), Math.max(Math.abs(convertOffsetToCubeCoordinates - convertOffsetToCubeCoordinates2), Math.abs(((-i) - convertOffsetToCubeCoordinates) - ((-i3) - convertOffsetToCubeCoordinates2))));
    }

    private static Point getIntersectionPoint(Point point, Point point2, Point point3, Point point4) {
        float x = point.getX();
        float y = point.getY();
        float x2 = point2.getX();
        float y2 = point2.getY();
        float x3 = point3.getX();
        float y3 = point3.getY();
        float x4 = point4.getX();
        float y4 = point4.getY();
        float f2 = x - x2;
        float f3 = y3 - y4;
        float f4 = y - y2;
        float f5 = x3 - x4;
        float f6 = (f2 * f3) - (f4 * f5);
        float f7 = (x * y2) - (y * x2);
        float f8 = (x3 * y4) - (y3 * x4);
        return new Point(((f5 * f7) - (f2 * f8)) / f6, ((f3 * f7) - (f4 * f8)) / f6);
    }
}
