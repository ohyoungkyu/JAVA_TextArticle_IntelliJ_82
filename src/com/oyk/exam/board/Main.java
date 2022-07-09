package com.oyk.exam.board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 게시판 v 0.1 ==");
        System.out.println("== 프로그램 시작 ==");

        int articlesLastId = 0;
        Article lastArticle = null;

        while(true) {

            System.out.print("명령)");
            String cmd = sc.nextLine();

            if( cmd.equals("exit")) {
                break;
            }

            else if( cmd.equals("/usr/article/write")) {
                System.out.println("- 게시물 등록 -");
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String body = sc.nextLine();
                int id = articlesLastId + 1;
                articlesLastId = id;

                Article article = new Article(id,title,body);
                lastArticle = article;

                System.out.printf("%d번 게시물이 입력되었습니다.\n", article.id);
            }

            else if(cmd.equals("/usr/article/detail")) {

                if(lastArticle == null) {
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }

                Article article = lastArticle;

                System.out.println(" 게시물 상세내용 - ");

                System.out.printf("번호 : %s\n", article.id);
                System.out.printf("제목 : %s\n", article.title);
                System.out.printf("내용 : %s\n", article.body);

            }
            else {
                System.out.printf("입력된 명령어 : %s\n",cmd);
            }
        }

        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }
}

class Article {
    int id;
    String title;
    String body;

    Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
