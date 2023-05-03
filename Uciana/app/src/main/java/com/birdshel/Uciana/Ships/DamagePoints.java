package com.birdshel.Uciana.Ships;

import com.birdshel.Uciana.Math.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
class DamagePoints {

    /* renamed from: a  reason: collision with root package name */
    static int[][] f1542a = {new int[]{0, 1, 2, 3, 4}, new int[]{1, 2, 0, 3, 4}, new int[]{2, 3, 1, 0, 4}, new int[]{3, 2, 1, 0, 4}};
    static Point[][] b = {new Point[]{new Point(75.0f, 100.0f), new Point(76.0f, 101.0f), new Point(77.0f, 103.0f)}, new Point[]{new Point(75.0f, 40.0f), new Point(76.0f, 41.0f), new Point(78.0f, 42.0f)}, new Point[]{new Point(90.0f, 135.0f), new Point(91.0f, 134.0f), new Point(92.0f, 133.0f)}, new Point[]{new Point(90.0f, 95.0f), new Point(91.0f, 94.0f), new Point(92.0f, 96.0f)}, new Point[]{new Point(70.0f, 80.0f), new Point(71.0f, 81.0f), new Point(72.0f, 79.0f)}};

    /* renamed from: c  reason: collision with root package name */
    static Point[][] f1543c = {new Point[]{new Point(30.0f, 40.0f), new Point(32.0f, 35.0f), new Point(34.0f, 42.0f)}, new Point[]{new Point(55.0f, 20.0f), new Point(48.0f, 25.0f), new Point(51.0f, 30.0f)}, new Point[]{new Point(80.0f, 40.0f), new Point(78.0f, 42.0f), new Point(82.0f, 38.0f)}, new Point[]{new Point(45.0f, 60.0f), new Point(42.0f, 63.0f), new Point(48.0f, 66.0f)}, new Point[]{new Point(70.0f, 20.0f), new Point(73.0f, 23.0f), new Point(76.0f, 26.0f)}};

    /* renamed from: d  reason: collision with root package name */
    static Point[][] f1544d = {new Point[0], new Point[0], new Point[0], new Point[0], new Point[0]};

    /* renamed from: e  reason: collision with root package name */
    static Point[][] f1545e = {new Point[0], new Point[0], new Point[0], new Point[0], new Point[0]};

    /* renamed from: f  reason: collision with root package name */
    static Point[][][][] f1546f = {new Point[][][]{new Point[][]{new Point[]{new Point(20.0f, 55.0f), new Point(23.0f, 52.0f), new Point(26.0f, 56.0f)}, new Point[]{new Point(60.0f, 27.0f), new Point(63.0f, 25.0f), new Point(60.0f, 25.0f)}, new Point[]{new Point(0.0f, 80.0f), new Point(3.0f, 82.0f), new Point(6.0f, 76.0f)}, new Point[]{new Point(70.0f, 60.0f), new Point(73.0f, 62.0f), new Point(70.0f, 62.0f)}, new Point[]{new Point(0.0f, 40.0f), new Point(3.0f, 43.0f), new Point(3.0f, 40.0f)}}, new Point[][]{new Point[]{new Point(40.0f, 60.0f), new Point(42.0f, 62.0f), new Point(44.0f, 64.0f)}, new Point[]{new Point(70.0f, 30.0f), new Point(72.0f, 32.0f), new Point(74.0f, 34.0f)}, new Point[]{new Point(25.0f, 10.0f), new Point(22.0f, 12.0f), new Point(28.0f, 14.0f)}, new Point[]{new Point(15.0f, 55.0f), new Point(12.0f, 52.0f), new Point(18.0f, 58.0f)}, new Point[]{new Point(30.0f, 85.0f), new Point(32.0f, 92.0f), new Point(28.0f, 88.0f)}}, new Point[][]{new Point[]{new Point(10.0f, 80.0f), new Point(12.0f, 82.0f), new Point(14.0f, 78.0f)}, new Point[]{new Point(30.0f, 50.0f), new Point(32.0f, 52.0f), new Point(34.0f, 48.0f)}, new Point[]{new Point(75.0f, 50.0f), new Point(72.0f, 52.0f), new Point(78.0f, 48.0f)}, new Point[]{new Point(25.0f, 10.0f), new Point(28.0f, 15.0f), new Point(23.0f, 12.0f)}, new Point[]{new Point(0.0f, 0.0f), new Point(8.0f, 5.0f), new Point(3.0f, 2.0f)}}, new Point[][]{new Point[]{new Point(60.0f, 25.0f), new Point(63.0f, 28.0f), new Point(66.0f, 22.0f)}, new Point[]{new Point(30.0f, 45.0f), new Point(33.0f, 48.0f), new Point(36.0f, 42.0f)}, new Point[]{new Point(0.0f, 85.0f), new Point(3.0f, 88.0f), new Point(6.0f, 82.0f)}, new Point[]{new Point(10.0f, 46.0f), new Point(13.0f, 40.0f), new Point(16.0f, 43.0f)}, new Point[]{new Point(95.0f, 46.0f), new Point(93.0f, 49.0f), new Point(97.0f, 43.0f)}}, new Point[][]{new Point[]{new Point(20.0f, 55.0f), new Point(23.0f, 52.0f), new Point(26.0f, 56.0f)}, new Point[]{new Point(60.0f, 27.0f), new Point(63.0f, 25.0f), new Point(60.0f, 25.0f)}, new Point[]{new Point(20.0f, 80.0f), new Point(23.0f, 82.0f), new Point(26.0f, 76.0f)}, new Point[]{new Point(70.0f, 60.0f), new Point(73.0f, 62.0f), new Point(70.0f, 62.0f)}, new Point[]{new Point(0.0f, 40.0f), new Point(3.0f, 43.0f), new Point(3.0f, 40.0f)}}}, new Point[][][]{new Point[][]{new Point[]{new Point(25.0f, 62.0f), new Point(28.0f, 66.0f)}, new Point[]{new Point(45.0f, 25.0f), new Point(40.0f, 29.0f)}, new Point[]{new Point(49.0f, 90.0f), new Point(47.0f, 88.0f), new Point(52.0f, 92.0f)}, new Point[]{new Point(0.0f, 14.0f), new Point(4.0f, 16.0f), new Point(2.0f, 20.0f)}, new Point[]{new Point(18.0f, 95.0f), new Point(16.0f, 100.0f), new Point(20.0f, 97.0f)}}, new Point[][]{new Point[]{new Point(0.0f, 0.0f), new Point(5.0f, 5.0f), new Point(5.0f, 0.0f)}, new Point[]{new Point(18.0f, 60.0f), new Point(20.0f, 58.0f), new Point(16.0f, 55.0f)}, new Point[]{new Point(52.0f, 30.0f), new Point(60.0f, 25.0f), new Point(50.0f, 35.0f)}, new Point[]{new Point(80.0f, 45.0f), new Point(75.0f, 40.0f), new Point(70.0f, 50.0f)}, new Point[]{new Point(20.0f, 68.0f), new Point(25.0f, 60.0f), new Point(20.0f, 55.0f)}}, new Point[][]{new Point[]{new Point(60.0f, 75.0f), new Point(65.0f, 70.0f), new Point(62.0f, 80.0f)}, new Point[]{new Point(60.0f, 15.0f), new Point(65.0f, 12.0f), new Point(62.0f, 10.0f)}, new Point[]{new Point(10.0f, 17.0f), new Point(15.0f, 19.0f), new Point(12.0f, 20.0f)}, new Point[]{new Point(20.0f, 32.0f), new Point(25.0f, 30.0f), new Point(22.0f, 35.0f)}, new Point[]{new Point(40.0f, 52.0f), new Point(45.0f, 50.0f), new Point(48.0f, 55.0f)}}, new Point[][]{new Point[]{new Point(25.0f, 0.0f), new Point(28.0f, 5.0f), new Point(31.0f, 10.0f)}, new Point[]{new Point(55.0f, 20.0f), new Point(58.0f, 25.0f), new Point(51.0f, 30.0f)}, new Point[]{new Point(5.0f, 90.0f), new Point(8.0f, 95.0f), new Point(11.0f, 90.0f)}, new Point[]{new Point(65.0f, 80.0f), new Point(68.0f, 85.0f), new Point(61.0f, 80.0f)}, new Point[]{new Point(30.0f, 40.0f), new Point(35.0f, 45.0f), new Point(40.0f, 40.0f)}}, new Point[][]{new Point[]{new Point(0.0f, 1.0f), new Point(5.0f, 5.0f), new Point(5.0f, 0.0f)}, new Point[]{new Point(18.0f, 60.0f), new Point(20.0f, 58.0f), new Point(16.0f, 55.0f)}, new Point[]{new Point(52.0f, 30.0f), new Point(60.0f, 25.0f), new Point(50.0f, 35.0f)}, new Point[]{new Point(80.0f, 45.0f), new Point(75.0f, 40.0f), new Point(70.0f, 50.0f)}, new Point[]{new Point(20.0f, 68.0f), new Point(25.0f, 60.0f), new Point(20.0f, 55.0f)}}}, new Point[][][]{new Point[][]{new Point[]{new Point(30.0f, 55.0f), new Point(34.0f, 52.0f), new Point(32.0f, 57.0f)}, new Point[]{new Point(75.0f, 50.0f), new Point(73.0f, 52.0f), new Point(77.0f, 48.0f)}, new Point[]{new Point(30.0f, 77.0f), new Point(28.0f, 79.0f), new Point(32.0f, 75.0f)}, new Point[]{new Point(30.0f, 23.0f), new Point(28.0f, 19.0f), new Point(32.0f, 25.0f)}, new Point[]{new Point(55.0f, 45.0f), new Point(57.0f, 43.0f), new Point(53.0f, 47.0f)}}, new Point[][]{new Point[]{new Point(8.0f, 80.0f), new Point(10.0f, 78.0f), new Point(6.0f, 82.0f)}, new Point[]{new Point(75.0f, 45.0f), new Point(78.0f, 48.0f), new Point(73.0f, 43.0f)}, new Point[]{new Point(20.0f, 25.0f), new Point(18.0f, 28.0f), new Point(23.0f, 23.0f)}, new Point[]{new Point(10.0f, 5.0f), new Point(8.0f, 8.0f), new Point(13.0f, 3.0f)}, new Point[]{new Point(30.0f, 70.0f), new Point(28.0f, 68.0f), new Point(33.0f, 73.0f)}}, new Point[][]{new Point[]{new Point(45.0f, 55.0f), new Point(42.0f, 52.0f), new Point(47.0f, 57.0f)}, new Point[]{new Point(86.0f, 38.0f), new Point(84.0f, 40.0f), new Point(88.0f, 36.0f)}, new Point[]{new Point(-10.0f, 100.0f), new Point(-12.0f, 98.0f), new Point(-8.0f, 96.0f)}, new Point[]{new Point(20.0f, 25.0f), new Point(18.0f, 27.0f), new Point(22.0f, 23.0f)}, new Point[]{new Point(7.0f, -15.0f), new Point(5.0f, -15.0f), new Point(9.0f, -15.0f)}}, new Point[][]{new Point[]{new Point(80.0f, 63.0f), new Point(83.0f, 65.0f), new Point(77.0f, 68.0f)}, new Point[]{new Point(40.0f, 103.0f), new Point(43.0f, 98.0f), new Point(37.0f, 100.0f)}, new Point[]{new Point(30.0f, 43.0f), new Point(33.0f, 45.0f), new Point(27.0f, 48.0f)}, new Point[]{new Point(38.0f, 32.0f), new Point(40.0f, 28.0f), new Point(42.0f, 30.0f)}, new Point[]{new Point(80.0f, 43.0f), new Point(83.0f, 45.0f), new Point(77.0f, 48.0f)}}, new Point[][]{new Point[]{new Point(80.0f, 63.0f), new Point(83.0f, 65.0f), new Point(77.0f, 68.0f)}, new Point[]{new Point(25.0f, 98.0f), new Point(28.0f, 92.0f), new Point(22.0f, 95.0f)}, new Point[]{new Point(30.0f, 43.0f), new Point(33.0f, 45.0f), new Point(27.0f, 48.0f)}, new Point[]{new Point(38.0f, 32.0f), new Point(40.0f, 28.0f), new Point(42.0f, 30.0f)}, new Point[]{new Point(80.0f, 43.0f), new Point(83.0f, 45.0f), new Point(77.0f, 48.0f)}}}, new Point[][][]{new Point[][]{new Point[]{new Point(20.0f, 12.0f), new Point(21.0f, 13.0f), new Point(21.0f, 13.0f)}, new Point[]{new Point(42.0f, 55.0f), new Point(41.0f, 58.0f), new Point(41.0f, 58.0f)}, new Point[]{new Point(80.0f, 50.0f), new Point(79.0f, 52.0f), new Point(82.0f, 48.0f)}, new Point[]{new Point(2.0f, 83.0f), new Point(0.0f, 85.0f), new Point(4.0f, 80.0f)}, new Point[]{new Point(50.0f, 95.0f), new Point(52.0f, 98.0f), new Point(48.0f, 93.0f)}}, new Point[][]{new Point[]{new Point(48.0f, 68.0f), new Point(44.0f, 70.0f), new Point(46.0f, 66.0f)}, new Point[]{new Point(73.0f, 35.0f), new Point(76.0f, 34.0f), new Point(79.0f, 39.0f)}, new Point[]{new Point(13.0f, 88.0f), new Point(16.0f, 92.0f), new Point(10.0f, 95.0f)}, new Point[]{new Point(0.0f, 30.0f), new Point(2.0f, 32.0f), new Point(4.0f, 36.0f)}, new Point[]{new Point(20.0f, 0.0f), new Point(23.0f, 2.0f), new Point(20.0f, 6.0f)}}, new Point[][]{new Point[]{new Point(78.0f, 55.0f), new Point(73.0f, 50.0f), new Point(69.0f, 60.0f)}, new Point[]{new Point(55.0f, 25.0f), new Point(60.0f, 30.0f), new Point(65.0f, 27.0f)}, new Point[]{new Point(40.0f, 85.0f), new Point(45.0f, 80.0f), new Point(50.0f, 82.0f)}, new Point[]{new Point(25.0f, 45.0f), new Point(18.0f, 50.0f), new Point(12.0f, 55.0f)}, new Point[]{new Point(20.0f, 15.0f), new Point(25.0f, 10.0f), new Point(30.0f, 20.0f)}}, new Point[][]{new Point[]{new Point(35.0f, 10.0f), new Point(30.0f, 12.0f), new Point(45.0f, 8.0f)}, new Point[]{new Point(12.0f, 38.0f), new Point(16.0f, 35.0f), new Point(24.0f, 41.0f)}, new Point[]{new Point(72.0f, 58.0f), new Point(76.0f, 55.0f), new Point(84.0f, 61.0f)}, new Point[]{new Point(20.0f, 85.0f), new Point(26.0f, 83.0f), new Point(23.0f, 81.0f)}, new Point[]{new Point(86.0f, 45.0f), new Point(88.0f, 41.0f), new Point(84.0f, 42.0f)}}, new Point[][]{new Point[]{new Point(78.0f, 54.0f), new Point(73.0f, 50.0f), new Point(69.0f, 60.0f)}, new Point[]{new Point(55.0f, 25.0f), new Point(60.0f, 30.0f), new Point(65.0f, 27.0f)}, new Point[]{new Point(40.0f, 85.0f), new Point(45.0f, 80.0f), new Point(50.0f, 82.0f)}, new Point[]{new Point(25.0f, 45.0f), new Point(18.0f, 50.0f), new Point(12.0f, 55.0f)}, new Point[]{new Point(20.0f, 15.0f), new Point(25.0f, 10.0f), new Point(30.0f, 20.0f)}}}, new Point[][][]{new Point[][]{new Point[]{new Point(17.0f, 10.0f), new Point(15.0f, 12.0f), new Point(13.0f, 8.0f)}, new Point[]{new Point(77.0f, 47.0f), new Point(75.0f, 43.0f), new Point(73.0f, 45.0f)}, new Point[]{new Point(40.0f, 60.0f), new Point(43.0f, 58.0f), new Point(38.0f, 62.0f)}, new Point[]{new Point(10.0f, 85.0f), new Point(8.0f, 83.0f), new Point(12.0f, 87.0f)}, new Point[]{new Point(32.0f, 35.0f), new Point(30.0f, 33.0f), new Point(34.0f, 37.0f)}}, new Point[][]{new Point[]{new Point(10.0f, 25.0f), new Point(8.0f, 22.0f), new Point(13.0f, 28.0f)}, new Point[]{new Point(55.0f, 50.0f), new Point(52.0f, 52.0f), new Point(50.0f, 55.0f)}, new Point[]{new Point(20.0f, 65.0f), new Point(22.0f, 62.0f), new Point(18.0f, 68.0f)}, new Point[]{new Point(20.0f, -5.0f), new Point(22.0f, -2.0f), new Point(18.0f, 1.0f)}, new Point[]{new Point(60.0f, 90.0f), new Point(62.0f, 87.0f), new Point(58.0f, 85.0f)}}, new Point[][]{new Point[]{new Point(22.0f, 28.0f), new Point(25.0f, 30.0f), new Point(19.0f, 32.0f)}, new Point[]{new Point(2.0f, 28.0f), new Point(5.0f, 30.0f), new Point(7.0f, 32.0f)}, new Point[]{new Point(2.0f, -2.0f), new Point(5.0f, -4.0f), new Point(7.0f, 0.0f)}, new Point[]{new Point(92.0f, 38.0f), new Point(90.0f, 40.0f), new Point(88.0f, 42.0f)}, new Point[]{new Point(62.0f, 92.0f), new Point(60.0f, 90.0f), new Point(58.0f, 88.0f)}}, new Point[][]{new Point[]{new Point(5.0f, 88.0f), new Point(7.0f, 90.0f), new Point(9.0f, 82.0f)}, new Point[]{new Point(50.0f, 28.0f), new Point(53.0f, 30.0f), new Point(55.0f, 32.0f)}, new Point[]{new Point(-7.0f, 15.0f), new Point(-3.0f, 17.0f), new Point(-5.0f, 12.0f)}, new Point[]{new Point(70.0f, 38.0f), new Point(73.0f, 35.0f), new Point(75.0f, 32.0f)}, new Point[]{new Point(0.0f, 38.0f), new Point(3.0f, 40.0f), new Point(5.0f, 42.0f)}}, new Point[][]{new Point[]{new Point(5.0f, 87.0f), new Point(7.0f, 90.0f), new Point(9.0f, 82.0f)}, new Point[]{new Point(50.0f, 28.0f), new Point(53.0f, 30.0f), new Point(55.0f, 32.0f)}, new Point[]{new Point(-7.0f, 15.0f), new Point(-3.0f, 17.0f), new Point(-5.0f, 12.0f)}, new Point[]{new Point(70.0f, 38.0f), new Point(73.0f, 35.0f), new Point(75.0f, 32.0f)}, new Point[]{new Point(0.0f, 38.0f), new Point(3.0f, 40.0f), new Point(5.0f, 42.0f)}}}, new Point[][][]{new Point[][]{new Point[]{new Point(45.0f, 31.0f), new Point(43.0f, 33.0f), new Point(41.0f, 35.0f)}, new Point[]{new Point(-5.0f, 60.0f), new Point(-3.0f, 63.0f), new Point(-1.0f, 65.0f)}, new Point[]{new Point(50.0f, 80.0f), new Point(53.0f, 83.0f), new Point(51.0f, 85.0f)}, new Point[]{new Point(25.0f, 20.0f), new Point(23.0f, 23.0f), new Point(27.0f, 25.0f)}, new Point[]{new Point(60.0f, 10.0f), new Point(63.0f, 13.0f), new Point(57.0f, 15.0f)}}, new Point[][]{new Point[]{new Point(90.0f, 35.0f), new Point(92.0f, 37.0f), new Point(88.0f, 36.0f)}, new Point[]{new Point(40.0f, 45.0f), new Point(42.0f, 47.0f), new Point(38.0f, 46.0f)}, new Point[]{new Point(-10.0f, 45.0f), new Point(-12.0f, 47.0f), new Point(-8.0f, 46.0f)}, new Point[]{new Point(25.0f, 20.0f), new Point(22.0f, 17.0f), new Point(28.0f, 22.0f)}, new Point[]{new Point(50.0f, 75.0f), new Point(52.0f, 77.0f), new Point(48.0f, 76.0f)}}, new Point[][]{new Point[]{new Point(20.0f, 55.0f), new Point(22.0f, 57.0f), new Point(24.0f, 53.0f)}, new Point[]{new Point(15.0f, 37.0f), new Point(17.0f, 35.0f), new Point(13.0f, 39.0f)}, new Point[]{new Point(55.0f, 37.0f), new Point(57.0f, 35.0f), new Point(53.0f, 39.0f)}, new Point[]{new Point(20.0f, 0.0f), new Point(17.0f, 5.0f), new Point(23.0f, -3.0f)}, new Point[]{new Point(40.0f, 20.0f), new Point(37.0f, 22.0f), new Point(43.0f, 24.0f)}}, new Point[][]{new Point[]{new Point(20.0f, 40.0f), new Point(22.0f, 42.0f), new Point(24.0f, 44.0f)}, new Point[]{new Point(50.0f, 15.0f), new Point(52.0f, 17.0f), new Point(54.0f, 13.0f)}, new Point[]{new Point(90.0f, 20.0f), new Point(92.0f, 22.0f), new Point(94.0f, 18.0f)}, new Point[]{new Point(30.0f, 65.0f), new Point(32.0f, 62.0f), new Point(34.0f, 67.0f)}, new Point[]{new Point(70.0f, 65.0f), new Point(72.0f, 62.0f), new Point(74.0f, 67.0f)}}, new Point[][]{new Point[]{new Point(90.0f, 36.0f), new Point(92.0f, 37.0f), new Point(88.0f, 36.0f)}, new Point[]{new Point(40.0f, 45.0f), new Point(42.0f, 47.0f), new Point(38.0f, 46.0f)}, new Point[]{new Point(-10.0f, 45.0f), new Point(-12.0f, 47.0f), new Point(-8.0f, 46.0f)}, new Point[]{new Point(25.0f, 20.0f), new Point(22.0f, 17.0f), new Point(28.0f, 22.0f)}, new Point[]{new Point(50.0f, 75.0f), new Point(52.0f, 77.0f), new Point(48.0f, 76.0f)}}}, new Point[][][]{new Point[][]{new Point[]{new Point(25.0f, 62.0f), new Point(28.0f, 66.0f)}, new Point[]{new Point(45.0f, 25.0f), new Point(40.0f, 29.0f)}, new Point[]{new Point(49.0f, 90.0f), new Point(47.0f, 88.0f), new Point(52.0f, 92.0f)}, new Point[]{new Point(0.0f, 14.0f), new Point(4.0f, 16.0f), new Point(2.0f, 20.0f)}, new Point[]{new Point(18.0f, 95.0f), new Point(16.0f, 100.0f), new Point(20.0f, 97.0f)}}, new Point[][]{new Point[]{new Point(20.0f, 55.0f), new Point(23.0f, 52.0f), new Point(26.0f, 56.0f)}, new Point[]{new Point(60.0f, 27.0f), new Point(63.0f, 25.0f), new Point(60.0f, 25.0f)}, new Point[]{new Point(0.0f, 80.0f), new Point(3.0f, 82.0f), new Point(6.0f, 76.0f)}, new Point[]{new Point(70.0f, 60.0f), new Point(73.0f, 62.0f), new Point(70.0f, 62.0f)}, new Point[]{new Point(0.0f, 40.0f), new Point(3.0f, 43.0f), new Point(3.0f, 40.0f)}}, new Point[][]{new Point[]{new Point(40.0f, 60.0f), new Point(42.0f, 62.0f), new Point(44.0f, 64.0f)}, new Point[]{new Point(70.0f, 30.0f), new Point(72.0f, 32.0f), new Point(74.0f, 34.0f)}, new Point[]{new Point(25.0f, 10.0f), new Point(22.0f, 12.0f), new Point(28.0f, 14.0f)}, new Point[]{new Point(15.0f, 55.0f), new Point(12.0f, 52.0f), new Point(18.0f, 58.0f)}, new Point[]{new Point(30.0f, 85.0f), new Point(32.0f, 92.0f), new Point(28.0f, 88.0f)}}, new Point[][]{new Point[]{new Point(80.0f, 63.0f), new Point(83.0f, 65.0f), new Point(77.0f, 68.0f)}, new Point[]{new Point(40.0f, 103.0f), new Point(43.0f, 98.0f), new Point(37.0f, 100.0f)}, new Point[]{new Point(30.0f, 43.0f), new Point(33.0f, 45.0f), new Point(27.0f, 48.0f)}, new Point[]{new Point(38.0f, 32.0f), new Point(40.0f, 28.0f), new Point(42.0f, 30.0f)}, new Point[]{new Point(80.0f, 43.0f), new Point(83.0f, 45.0f), new Point(77.0f, 48.0f)}}, new Point[][]{new Point[]{new Point(78.0f, 55.0f), new Point(73.0f, 50.0f), new Point(69.0f, 60.0f)}, new Point[]{new Point(55.0f, 25.0f), new Point(60.0f, 30.0f), new Point(65.0f, 27.0f)}, new Point[]{new Point(40.0f, 85.0f), new Point(45.0f, 80.0f), new Point(50.0f, 82.0f)}, new Point[]{new Point(25.0f, 45.0f), new Point(18.0f, 50.0f), new Point(12.0f, 55.0f)}, new Point[]{new Point(20.0f, 15.0f), new Point(25.0f, 10.0f), new Point(30.0f, 20.0f)}}}};

    DamagePoints() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<List<Point>> a(int i, ShipType shipType, int i2) {
        Point[][] pointArr = new Point[0];
        if (i < 7) {
            if (shipType == ShipType.TORPEDO_TURRET) {
                pointArr = b;
            } else if (shipType == ShipType.STAR_BASE) {
                pointArr = f1543c;
            } else if (shipType.isCombatShip()) {
                pointArr = f1546f[i][f1542a[shipType.id - 4][i2]];
            } else {
                new ArrayList();
            }
        } else if (i == 8) {
            pointArr = f1544d;
        } else if (i == 9) {
            pointArr = f1545e;
        }
        ArrayList arrayList = new ArrayList();
        for (Point[] pointArr2 : pointArr) {
            arrayList.add(Arrays.asList(pointArr2));
        }
        return arrayList;
    }
}
