package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CRUD {
BufferedReader br;
private List<Movie> list;
    public CRUD(){
        this.list = new ArrayList<>();
    }
    public String printMenu() throws IOException {
        System.out.println("=============[ 보고 싶은 영화 ] ==========");
        System.out.println("1. 영화 추가");
        System.out.println("2. 영화 수정");
        System.out.println("3. 영화 삭제");
        System.out.println("4. 영화 리스트 보기");
        System.out.println("5. 영화 검색");
        System.out.println("6. 영화 별점 순위");
        System.out.println("7. 파일 저장");
        System.out.println("0. 프로그램 종료");
        System.out.println("=========================================");
        br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }


    public Movie addData(ArrayList<Movie> list) throws IOException {
        int num;
        String name; //영화 이름
        String genre;// 장르
        int runningTime; //상영 시간
        int viewor;// 누적 관객수
        String regDate;// 기록 날짜
        double rating_Point;//영화 평점

        num = list.size();

            System.out.println("영화 제목을 입력하세요");
            br = new BufferedReader(new InputStreamReader(System.in));
            name = br.readLine();
            System.out.println("영화 장르를 입력하세요 ex)드라마, 공포, 액션, 코미디");
            genre=br.readLine();
            System.out.println("영화 상영 시간을 입력하세요 ex)분으로만 입력");
            runningTime =Integer.parseInt(br.readLine());
            System.out.println("누적 관객 수를 입력하세요 *단위는 만 입니다.*  M");
            viewor=Integer.parseInt(br.readLine());
            System.out.println("영화의 평점을 입력하세요 (10점 만점)");

            rating_Point=Double.parseDouble(br.readLine());
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            regDate = date.format(formatter);

            Movie m = new Movie(num,name,genre,runningTime,viewor, regDate, rating_Point);

             return m;

    }

   public void editData(ArrayList<Movie> list) {
        // Placeholder implementation
       if(list.isEmpty()){
           System.out.print("데이터가 존재하지 않습니다");
           return;
       }
       try{
           System.out.println("수정할 번호 입력");
           br = new BufferedReader(new InputStreamReader(System.in));
           int num = Integer.parseInt(br.readLine()) -1;
           if(Main.valid(list , num)){
               System.out.println("영화 제목을 입력하세요");
               br = new BufferedReader(new InputStreamReader(System.in));
               list.get(num).setName(br.readLine());
               System.out.println("영화 장르를 입력하세요 ex)드라마, 공포, 액션, 코미디");
               list.get(num).setGenre(br.readLine());
               System.out.println("영화 상영 시간을 입력하세요 ex)분으로만 입력");
               list.get(num).setRunningTime(Integer.parseInt(br.readLine()));
               System.out.println("누적 관객 수를 입력하세요 *단위는 만 입니다.*  M");
               list.get(num).setViewor(Integer.parseInt(br.readLine()));
               System.out.println("영화의 평점을 입력하세요 (10점 만점)");
               list.get(num).setRating_Point(Float.parseFloat(br.readLine()));
               LocalDate date = LocalDate.now();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               String regDate = date.format(formatter);
               list.get(num).setRegDate(regDate);
               System.out.println("수정 되었습니다!");
           }else{
               editData(list);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public void deleteData(ArrayList<Movie> list) {
        if(list.isEmpty()){
            System.out.println("데이터가 존재하지 않았습니다!");
            return;
        }
       try{

           System.out.println("삭제할 영화 번호를 입력하세요");
           br = new BufferedReader(new InputStreamReader(System.in));
           int num = Integer.parseInt(br.readLine())-1;
           if(Main.valid(list,num)) {
               this.list.remove(num);
               for (int i = 0; i < list.size(); i++) {
                   this.list.get(i).setNum(i);
               }
               System.out.println("삭제되었습니다.");
           }else{
               deleteData(list);
           }
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    public void readData(ArrayList<Movie> list) {

        if (list.isEmpty()) {
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }
        System.out.println("NO  영화제목        장르      상영시간    누적관객수      기록날자     영화평점");
        System.out.println("=========================================================================");
        for (Movie m : list) {
            System.out.println(m.toString());
        }
    }

    public void searchData(ArrayList<Movie> list) {
        if (list.isEmpty()) {
            System.out.println("데이터가 존재하지 않습니다");
            return;
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        String type = null;
        try {
            System.out.println("1) 영화 이름 검색  2) 영화 장르 검색");
            type = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (type.equals("1")) {
            System.out.println("검색할 영화 제목을 입력하세요");

            try {
                String name = br.readLine();
                boolean check = false;
                for (Movie m : list) {
                    if (m.getName().contains(name)) {
                        if (!check) {
                            System.out.println("NO  영화제목        장르      상영시간    누적관객수      기록날자     영화평점");
                            System.out.println("=========================================================================");
                            check = true;
                        }
                        System.out.println(m.toString());
                    }
                }
                if (!check) {
                    System.out.println("검색 결과가 없습니다.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("2")){
                System.out.println("검색할 영화 장르를 입력하세요");

                try {
                    String genre = br.readLine();
                    boolean check = false;
                    for (Movie m : list) {
                        if (m.getGenre().contains(genre)) {
                            if (!check) {
                                System.out.println("NO  영화제목        장르      상영시간    누적관객수      기록날자     영화평점");
                                System.out.println("=========================================================================");
                                check = true;
                            }
                            System.out.println(m.toString());
                        }
                    }
                    if (!check) {
                        System.out.println("검색 결과가 없습니다.");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


   public void topRanking(ArrayList<Movie> list) {
        if (list.isEmpty()) {
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }
      //평점으로 내림차순 정렬
        Collections.sort(list, Comparator.comparing(Movie::getRating_Point).reversed());

        System.out.println("NO  영화제목        장르      상영시간    누적관객수      기록날자     영화평점");
        System.out.println("=========================================================================");

        for (Movie m : list) {
            System.out.println(m.toString());
        }
        // num 으로 오름차순으로 정렬
        Collections.sort(list, Comparator.comparing(Movie::getNum));
    }


}
