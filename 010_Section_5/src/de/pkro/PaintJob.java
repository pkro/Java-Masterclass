package de.pkro;

public class PaintJob {
    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }
        double areaToCover = width * height;
        double remainingArea = areaToCover - extraBuckets * areaPerBucket;
        return  (int) Math.ceil(remainingArea / areaPerBucket);
    }

    public static int getBucketCount(double width, double height, double areaPerBucket) {
        return getBucketCount(width, height, areaPerBucket, 0);
    }

    public static int getBucketCount(double area, double areaPerBucket) {
        if(area < 0) {
            return -1;
        }
        double side = Math.sqrt(area);
        return getBucketCount(side, side, areaPerBucket);
    }


}
