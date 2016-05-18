package edu.ktu.alglab2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {

    public static final int YEARS = 10;

    public double[][] R;
    private final double f1;
    private final double f2;
    private final int n;
    private final double startingMoney;

    public Main(int n, double startingMoney, double f1, double f2) {
        R = new double[YEARS][n];
        this.f1 = f1;
        this.f2 = f2;
        this.n = n;
        this.startingMoney = startingMoney;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("duom.txt");
        Scanner scanner = new Scanner(inputStream);
        double starting = scanner.nextDouble();
        double f1 = scanner.nextDouble();
        double f2 = scanner.nextDouble();
        int i = scanner.nextInt();
        System.out.println(i);
        Main main = new Main(i, starting, f1, f2);
        Random random = new Random();
        for(int ii = 0; ii < i; ii++){
            for(int jj = 0; jj < YEARS; jj++){
                main.R[jj][ii] = (random.nextDouble() / 3.0) + 1.0;
            }
        }
        long start = System.nanoTime();
        double biggestsProfits = main.getBiggestsProfits();
        long end = System.nanoTime();
        System.out.printf("%.2f\n", biggestsProfits);
        System.out.println((end - start) / 1000000.0 + "ms" );

    }

    public double getBiggestsProfits(){
        double max = startingMoney;
        int current = 0;
        for (int j = 0; j < YEARS; j++) {
            double saved = max;
            double temp;
            for (int i = 0; i < n; i++) {
                if(j == 0){
                    temp = getNextYearMoney(saved, j, i, i, false);
                    if ( temp > max){
                        max = temp;
                        current = i;
                    }
                } else {
                    temp = getNextYearMoney(saved, j, current, i, true);
                    if ( temp > max){
                        max = temp;
                        current = i;
                    }
                }
            }
        }
        return max;
    }

    private double getNextYearMoney(double money, int j, int i, int k, boolean fee) {
        return money * R[j][k] - ((fee)? ((k == i) ? f1 : f2) : 0);
    }

}
