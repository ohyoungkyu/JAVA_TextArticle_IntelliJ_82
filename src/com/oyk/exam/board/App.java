package com.oyk.exam.board;

import java.util.Scanner;

public class App {

    void main() {
        Scanner sc = Container.sc;

        System.out.println("== 게시판 v 0.1 ==");
        System.out.println("== 프로그램 시작 ==");

        while(true) {

            System.out.print("명령)");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if( rq.getUrlPath().equals("exit")) {
                break;
            }
            else if(rq.getUrlPath().equals("/usr/article/detail")) {
                Container.usrArticleController.actionDetail(rq);
            }
            else if(rq.getUrlPath().equals("/usr/article/list")) {
                Container.usrArticleController.actionList(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/write")) {
                Container.usrArticleController.actionWrite(rq);
            }
            else if( rq.getUrlPath().equals("/usr/member/join")) {
                Container.usrmemberController.actionjoin(rq);
            }
            else {
                System.out.printf("입력된 명령어 : %s\n",cmd);
            }
        }

        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }
}
