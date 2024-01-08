package org.example;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileService {

    public ArrayList<Movie> readFile() {
        ArrayList<Movie> list = new ArrayList<>();
        try {
            File file = new File("data.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(filereader);

            int i = 0;
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "/");
                String name = st.nextToken().trim();
                String genre = st.nextToken().trim();
                int runningTime = Integer.parseInt(st.nextToken().trim());
                int viewor = Integer.parseInt(st.nextToken().trim());
                String regDate = st.nextToken().trim();
                double rating_Point = Double.parseDouble(st.nextToken().trim());
                list.add(new Movie(i, name, genre, runningTime, viewor, regDate, rating_Point));
                i++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("data.txt 파일이 존재하지 않습니다.");
        } catch (IOException e) {
            System.out.println(e);
        }
        return list;
    }

    //파일 저장 시스탬
    public void saveFile(ArrayList<Movie> list) {
        try {
            File file = new File("data.txt");
            FileUtils.write(file, "", false);
            for (Movie m : list) {
                FileUtils.write(file, m.getName() + "/" + m.getGenre() + "/" + m.getRunningTime() + "/" + m.getViewor() + "/" + m.getRegDate() + "/" + m.getRating_Point()+"\n",true);
            }
        } catch (FileNotFoundException e) {
            System.out.println("data.txt 파일 없음");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
