package com.oyk.exam.board;

import java.util.*;

public class Main {

    static int articlesLastId;
    static List<Article> articles;

    static {
        articlesLastId = 0;
        articles = new ArrayList<>();
    }
    public static void main(String[] args) {
        Scanner sc = Container.sc;

        System.out.println("== 게시판 v 0.1 ==");
        System.out.println("== 프로그램 시작 ==");

        makeTestData();

        if(articles.size() > 0) {
            articlesLastId = articles.get(articles.size() - 1).id;
        }

        while(true) {

            System.out.print("명령)");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);
            Map<String, String> params = rq.getParams();


            if( rq.getUrlPath().equals("exit")) {
                break;
            }
            else if(rq.getUrlPath().equals("/usr/article/detail")) {
                actionUsrArticleDetail(rq);
            }
            else if(rq.getUrlPath().equals("/usr/article/list")) {
                actionUsrArticleList(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/write")) {
                actionUsrArticleWrite(rq);
            }
            else {
                System.out.printf("입력된 명령어 : %s\n",cmd);
            }
        }

        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }

    static void makeTestData() {
        for(int i = 0; i < 100; i++) {
            int id = i + 1;
            articles.add(new Article(id,"제목" + id,"내용" + id));
        }
    }

    private static void actionUsrArticleWrite(Rq rq) {
        System.out.println("- 게시물 등록 -");
        System.out.printf("제목 : ");
        String title = Container.sc.nextLine();
        System.out.printf("내용 : ");
        String body = Container.sc.nextLine();
        int id = articlesLastId + 1;
        articlesLastId = id;

        Article article = new Article(id,title,body);

        articles.add(article);

        System.out.println("생성된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 입력되었습니다.\n", article.id);
    }

    private static void actionUsrArticleDetail(Rq rq) {
        Map<String, String> params = rq.getParams();

        if(params.containsKey("id") == false) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = 0;

        try{
            id = Integer.parseInt(params.get("id"));
        }
        catch (NumberFormatException e) {
            System.out.println("id를 정수형태로 입력해주세요.");
            return;
        }

        if(id > articles.size()) {
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }

        Article article = articles.get(id - 1);

        System.out.println(" 게시물 상세내용 - ");

        System.out.printf("번호 : %s\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.body);
    }

    private static void actionUsrArticleList(Rq rq) {
        System.out.println(" 게시물 리스트 - ");
        System.out.println("--------------------");
        System.out.println("번호 / 제목");
        System.out.println("--------------------");

        Map<String, String> params = rq.getParams();
        //검색시작
        List<Article> filteredArticles = articles;

        if( params.containsKey("searchKeyword")) {
            String searchKeyword = params.get("searchKeyword");

            filteredArticles = new ArrayList<>();

            for( Article article : articles) {
                boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);
                if( matched ) {
                    filteredArticles.add(article);
                }
            }
        }
        //검색끝끝

        List<Article> sortedArticles = filteredArticles;

        boolean orderByIdDesc = true;

        if( params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
            orderByIdDesc = false;
        }

        if( orderByIdDesc ) {
            sortedArticles = Util.reverseList(sortedArticles);
        }

        for(Article article : sortedArticles) {
            System.out.printf("%d / %s\n", article.id, article.title);
        }
    }
}
