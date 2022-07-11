package com.oyk.exam.board;

import java.util.Scanner;

public class Container {
    static Scanner sc;
    static UsrArticleController usrArticleController;
    static UsrMemberController usrmemberController;

    static {
        sc = new Scanner(System.in);
        usrArticleController = new UsrArticleController();
        usrmemberController = new UsrMemberController();
    }
}
