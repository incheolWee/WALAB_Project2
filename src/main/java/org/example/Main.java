package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

    public static void main(String [] args) {
        ArrayList <Movie> list;
        CRUD m = new CRUD();
        FileService fileService = new FileService();
        list = fileService.readFile();
        System.out.println("총 "+list.size()+"개의 데이터를 파일에서 읽어왔습니다");

        System.out.println("--------------프로그램을 시작합니다--------------");

        while(true) {
            try{
                String choose = m.printMenu();
                    switch (choose) {
                        case "1":
                           list.add( m.addData(list));
                            System.out.println("추가 완료.");
                            break;
                        case "2":
                            m.readData(list);
                            m.editData(list);
                            System.out.println("수정 완료.");
                            break;
                        case "3":
                            m.readData(list);
                            m.deleteData(list);
                            System.out.println("삭제 완료.");
                            break;
                        case "4":
                            m.readData(list);
                            break;
                        case "5":
                            m.searchData(list);
                            break;
                        case "6":
                            m.topRanking(list);
                            break;
                        case "7":
                            fileService.saveFile(list);
                            System.out.println("파일저장 완료");
                            break;
                        case "0":
                            System.out.println("프로그램을 종료");
                            return ;
                        default:
                            System.out.println("잘못된 입력");
                    }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public static boolean valid(ArrayList<Movie> list , int num) {
        // 배열 범위
        if (list.size() <= num || num == -1) {
            System.out.println("없는 번호 입니다.");
            return false;
        }
        return true;
    }
}
