package project;

import project.dao.BusinessCardManagerDao;
import project.dto.BusinessCard;

import java.util.List;
import java.util.Scanner;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] inpContext = {"이름을 입력하세요. : ", "전화번호를 입력하세요 : ", "회사 이름을 입력하세요 : "};

        BusinessCardManagerDao dao = new BusinessCardManagerDao();
        while (true) {
            System.out.println("------------------");
            System.out.println("1. 명함 입력");
            System.out.println("2. 명함 검색");
            System.out.println("3. 종료");
            System.out.println("------------------");
            try {
                System.out.print("메뉴를 입력하세요 : ");
                int menu = sc.nextInt();
                sc.nextLine();
                if (menu == 1) {
                    String[] inp = new String[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print(inpContext[i]);
                        inp[i] = sc.nextLine();
                    }
                    BusinessCard card = new BusinessCard(inp[0], inp[1], inp[2]);
                    System.out.println(dao.addBusinessCard(card));
                } else if (menu == 2) {
                    System.out.print(inpContext[0]);
                    String name = sc.nextLine();
                    List<BusinessCard> cards = dao.searchBusinessCard(name);
                    for (BusinessCard value : cards) {
                        System.out.println(value.toString());
                    }
                } else {
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
