package sample.model;

import sample.utility.Orientation;
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
        ShipType shipType = null;
        int carrierCounter = 0;
        int destroyerCounter = 0;
        int subCounter = 0;
        int cruiserCounter = 0;
        int battleshipCounter = 0;
        Orientation orientation1 = null;


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

        sc.nextLine(); //p1 board
        sc.nextLine();
        sc.nextLine();

        for (int i = 0; i < 100; i++) {
            sc.next(); // Cell: Row:
            sc.next();
            row = sc.nextInt();
            sc.next();
            col = sc.nextInt();
            sc.next();
            isHit = sc.nextBoolean();
            sc.next();
            String tempShip = sc.next();
            sc.next();
            String orientation = sc.next();
            if (orientation.equals("HORIZONTAL")) {
                orientation1 = Orientation.HORIZONTAL;
            } else if (orientation.equals("VERTICAL")) {
                orientation1 = Orientation.VERTICAL;
            }

            if (tempShip.equals("null")) {
                shipType = null;
            } else if (tempShip.equals("CARRIER")) {
                if (carrierCounter == 0) {
                    shipType = ShipType.CARRIER;
                    if (playerTurn == Player.P1) {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1, isHit);
                    } else {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1, isHit);
                    }
                    carrierCounter++;
                } else if (carrierCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("CRUISER")) {
                if (cruiserCounter == 0) {
                    shipType = ShipType.CRUISER;
                    cruiserCounter++;
                    if (playerTurn == Player.P1) {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1, isHit);
                    } else {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1, isHit);
                    }
                }
                else if ( cruiserCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("SUBMARINE")) {
                if (subCounter == 0) {
                    shipType = ShipType.SUBMARINE;
                    subCounter++;
                    if (playerTurn == Player.P1) {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1, isHit);
                    } else {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1, isHit);
                    }
                }
                else if ( subCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("DESTROYER")) {
                if (destroyerCounter == 0) {
                    shipType = ShipType.DESTROYER;
                    destroyerCounter++;
                    if (playerTurn == Player.P1) {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1, isHit);
                    } else {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1, isHit);
                    }
                }
                else if ( destroyerCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("BATTLESHIP")) {
                if (battleshipCounter == 0) {
                    shipType = ShipType.BATTLESHIP;
                    battleshipCounter++;
                    if (playerTurn == Player.P1) {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1, isHit);
                    } else {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1, isHit);
                    }
                }
                else if ( battleshipCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            }
            if (isHit == true) {
                if (playerTurn == Player.P1) {
                    Game.getP1Board().getCellByCoord(row, col);
                } else {
                    Game.getP2Board().getCellByCoord(row, col);
                }
            }
        }

        for(int i = 0; i<100; i++)
        {
            destroyerCounter = 0;
            subCounter = 0;
            carrierCounter = 0;
            cruiserCounter =0;
            battleshipCounter = 0;

                sc.next(); // Cell: Row:
                sc.next();
                row = sc.nextInt();
                sc.next();
                col = sc.nextInt();
                sc.next();
                isHit = sc.nextBoolean();
                sc.next();
                String tempShip = sc.next();
                sc.next();
                String orientation = sc.next();
                if(orientation.equals("HORIZONTAL"))
                {
                    orientation1 = Orientation.HORIZONTAL;
                }
              else if(orientation.equals("VERTICAL"))
               {
                    orientation1 = Orientation.VERTICAL;
                }
            if (tempShip.equals("null")) {
                shipType = null;
            } else if (tempShip.equals("CARRIER")) {
                if(carrierCounter == 0)
                {
                    shipType = ShipType.CARRIER;
                    carrierCounter ++;
                    if (playerTurn == Player.P1) {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1,isHit);
                    } else {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1,isHit);
                    }
                }
                else if ( carrierCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("CRUISER")) {
                if(cruiserCounter == 0)
                {
                    shipType = ShipType.CRUISER;
                    cruiserCounter ++;
                    if (playerTurn == Player.P1) {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1,isHit);
                    } else {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1,isHit);
                    }
                }
                else if (cruiserCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("SUBMARINE")) {
                if(subCounter == 0)
                {
                    shipType = ShipType.SUBMARINE;
                    subCounter ++;
                    if (playerTurn == Player.P1) {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1,isHit);
                    } else {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1,isHit);
                    }
                }
                else if ( subCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            } else if (tempShip.equals("DESTROYER")) {
                if(destroyerCounter == 0)
                {
                    shipType = ShipType.DESTROYER;
                    destroyerCounter ++;
                    if (playerTurn == Player.P1) {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1,isHit);
                    } else {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1,isHit);
                    }
                }
                else if (destroyerCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            }else if (tempShip.equals("BATTLESHIP")) {
                if(battleshipCounter == 0)
                {
                    shipType = ShipType.BATTLESHIP;
                    battleshipCounter ++;
                    if (playerTurn == Player.P1) {
                        Game.getP2Board().placeShip(row, col, shipType, orientation1,isHit);
                    } else {
                        Game.getP1Board().placeShip(row, col, shipType, orientation1,isHit);
                    }
                }
                else if ( battleshipCounter == 1) {
                    if (isHit == true) {
                        if (playerTurn == Player.P1) {
                            Game.getP1Board().getCellByCoord(row, col);
                        } else {
                            Game.getP2Board().getCellByCoord(row, col);
                        }
                    }
                }
            }
            if (isHit == true) {
                if (playerTurn == Player.P1) {
                    Game.getP1Board().getCellByCoord(row, col);
                } else {
                    Game.getP2Board().getCellByCoord(row, col);
                }
            }


    }

    }

}
