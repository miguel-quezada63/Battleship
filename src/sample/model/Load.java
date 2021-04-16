package sample.model;

import sample.utility.Player;
import sample.utility.ShipType;

import java.io.*;
import java.util.Scanner;


public class Load {
    public static void loadGame(File game) {

        int turnNum = 0;
        int row = 0;
        int col = 0;
        boolean isHit = false;
        Ship ship = null;


        File file = new File(String.valueOf(game));
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        sc.next();
        sc.next();
        turnNum = sc.nextInt();

        sc.next();
        sc.next();
        Player playerTurn = Player.valueOf(sc.next());

        Game.loadTurn(turnNum, playerTurn);

        sc.nextLine();
        sc.nextLine();
        sc.nextLine();
        for(int i = 0; i<100; i++)
        {
            sc.next();
            sc.next();
            row = sc.nextInt();
            sc.next();
            col = sc.nextInt();
            sc.next();
            isHit = sc.nextBoolean();
            sc.next();
            String val = sc.next();
            //if (!(val.equals("null"))) {
            //    ship = sc.next();
           // }

            if(playerTurn == Player.P1)
            {
                Game.getP1Board().loadGrid(row, col, isHit, ship);
            }
            else
            {
                Game.getP2Board().loadGrid(row,col,isHit,ship);
            }
        }
        sc.nextLine();
        sc.nextLine();
        System.out.println("Opponent Board: \n");
        for(int i = 0; i<100; i++)
        {
            sc.next();
            sc.next();
            row = sc.nextInt();
            sc.next();
            col = sc.nextInt();
            sc.next();
            isHit = sc.nextBoolean();
            sc.next();
            String val = sc.next();
            //if (!(val.equals("null"))) {
             //   ship = ShipType.valueOf(sc.next());
            //}
            if(playerTurn == Player.P1)
            {
                Game.getP2Board().loadGrid(row, col, isHit, ship);
            }
            else
            {
                Game.getP1Board().loadGrid(row,col,isHit,ship);
            }
        }



    }
}







