package edu.ktu.alglab2;

import java.io.*;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {

    public double[][] R;
    private final double f1;
    private final double f2;
    private final int years;
    private final int investments;
    private final double startingMoney;

    public Main(int years, int investments, double startingMoney, double f1, double f2) {
        R = new double[years][investments];
        this.f1 = f1;
        this.f2 = f2;
        this.years = years;
        this.investments = investments;
        this.startingMoney = startingMoney;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("duom.txt");
        Scanner scanner = new Scanner(inputStream);
        double starting = scanner.nextDouble();
        double f1 = scanner.nextDouble();
        double f2 = scanner.nextDouble();
        int j = scanner.nextInt();
        int i = scanner.nextInt();
        Main main = new Main(j, i, starting, f1, f2);
        for(int ii = 0; ii < i; ii++){
            for(int jj = 0; jj < j; jj++){
                main.R[jj][ii] = scanner.nextDouble();
            }
        }
        System.out.printf("%.2f\n", main.getBiggestsProfits());

    }

    public double getBiggestsProfits(){
        double max = 0;
        for (int k = 0; k < investments; k++) {
            max = max(getBiggestsProfits(getNextYearMoney(startingMoney, 0, k, k, false), 0, k), max);
        }
        return max;
    }

    private double getBiggestsProfits(double money, int j, int i){
        System.out.println(j + ","+ i+","+ money+",");
        if(j >= years - 1){
            return money;
        }
        double max = 0;
        for (int k = 0; k < investments; k++) {
            max = max(getBiggestsProfits(getNextYearMoney(money, j + 1, i, k, true), j + 1, k), max);
        }
        return max;
    }

    private double getNextYearMoney(double money, int j, int i, int k, boolean fee) {
        return money * R[j][k] - ((fee)? ((k == i) ? f1 : f2) : 0);
    }

}
