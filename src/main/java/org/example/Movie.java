package org.example;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Movie {
    private int num; //순서
    private String name; //영화 이름
    private String genre;// 장르
    private int runningTime; //상영 시간
    private int viewor;// 누적 관객수
    private String regDate;// 기록 날짜
    private double rating_Point;//영화 평점

    public Movie() {
    }

    public Movie(int num, String name, String genre, int runningTime, int viewor, String regDate, double rating_Point) {
        this.num = num;
        this.name = name;
        this.genre = genre;
        this.runningTime = runningTime;
        this.viewor = viewor;
        this.rating_Point = rating_Point;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return String.format("%2d", (num + 1)) + "\t"
                + String.format("%-7s", name) + "\t" +
                String.format("%5s", genre) + "\t\t" +
                String.format("%4d", runningTime) + "m" + "\t\t" +
                String.format("%4d", viewor) + "M" + "\t\t" +
                regDate + "\t\t" +
                String.format("%.1f", rating_Point);
    }

}
