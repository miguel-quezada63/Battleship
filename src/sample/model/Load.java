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
            String tempShip = sc.next();
            if (tempShip.equals("null")) {
                ship = null;
            }
            else if(tempShip.equals("CARRIER")){
                ship = new Ship(ShipType.CARRIER);
            }
            else if(tempShip.equals("CRUISE")){
                ship = new Ship(ShipType.CRUISER);
            }
            else if(tempShip.equals("SUBMARINE")){
                ship = new Ship(ShipType.SUBMARINE);
            }
            else if(tempShip.equals("DESTROYER")){
                ship = new Ship(ShipType.DESTROYER);
            }

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
            String tempShip = sc.next();
            if (tempShip.equals("null")) {
                ship = null;
            }
            else if(tempShip.equals("CARRIER")){
                ship = new Ship(ShipType.CARRIER);
            }
            else if(tempShip.equals("CRUISE")){
                ship = new Ship(ShipType.CRUISER);
            }
            else if(tempShip.equals("SUBMARINE")){
                ship = new Ship(ShipType.SUBMARINE);
            }
            else if(tempShip.equals("DESTROYER")){
                ship = new Ship(ShipType.DESTROYER);
            }

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