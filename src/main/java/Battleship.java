import java.util.Scanner;

public class Battleship {
    static String player1;
    static String player2;
    static Scanner scanner = new Scanner(System.in);
    static  int [][] battlefield1 = new int[15][15];
    static  int [][] battlefield2 = new int[15][15];
    static  int [][] monitor1 = new int[15][15];
    static  int [][] monitor2 = new int[15][15];

    public static void main(String[] args) {
        System.out.println("Player1 - ваше имя : ");
        player1 = scanner.nextLine();
        System.out.println("Player2 - ваше имя : ");
        player2 = scanner.nextLine();
        System.out.println("------------------------");
        System.out.println("Player1 name :");
        System.out.println(player1);
        System.out.println("Player2 name :");
        System.out.println(player2);
        System.out.println();
        placeShips(player1, battlefield1);
        placeShips(player2, battlefield2);
        while (true){
            makeTurn(player1, monitor1, battlefield2);
            if (isWinCondition()){
                break;
            }
            makeTurn(player2, monitor2, battlefield1);
        if (isWinCondition()){
            break;}
        }
    }
    public static void placeShips(String player,int[][] battlefield) {
        int deck = 6;
        while (deck>=1) {
        System.out.println();
        draw(battlefield);
        System.out.println(player + " Укажите координаты для размещения "+ deck +"-deck корабля : ");
            System.out.println(" Укажите координаты по оси Х: ");
            int x = scanner.nextInt();
            System.out.println(" Укажите координаты по оси Y: ");
            int y = scanner.nextInt();
            System.out.println(" выберите направление :");
            System.out.println(" 1 - Вертикальный ");
            System.out.println(" 2 - Горизонтальный ");
            int rotation = scanner.nextInt();
            if (!isAvailable(x,y,deck,rotation,battlefield)){
                System.out.println(" Выбери другое место");
                continue;}
            for (int i = 0; i < deck ; i++) {
                if (rotation ==1){
                    battlefield[x][y + i] = 1;

                }else {
                    battlefield[x+ i][y] = 1;

                }

            }
            deck--;
        }


        }
        public static void draw(int[][] battlefield){
            System.out.println("  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ");
            for (int i = 0; i < battlefield.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < battlefield[1].length; j++) {
                    if (battlefield[i][j]== 0){
                        System.out.print("- ");
                    }else {
                        System.out.print("X ");
                    }

                }
                System.out.println();
            }
        }
        public static void makeTurn(String player,int [][] monitor,int[][] battlefield){
        while (true) {
            System.out.println(player + ", Cделай свой ход ");
            System.out.println("  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ");
            for (int i = 0; i < monitor.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < monitor[1].length; j++) {
                    if (monitor[i][j] == 0) {
                        System.out.print("- ");
                    } else if (monitor[i][j] == 1) {
                        System.out.print(". ");
                    } else {
                        System.out.print("X ");

                    }


                }
                System.out.println();
            }
            System.out.println(" Укажите координаты по оси Х: ");
            int x = scanner.nextInt();
            System.out.println(" Укажите координаты по оси Y: ");
            int y = scanner.nextInt();
            if (battlefield[x][y] == 1) {
                monitor[x][y]=2;
                System.out.println(" Попал!");

            } else {
                System.out.println(" Промах!");
                monitor[x][y]=1;
                break;}
        }

        }

        public static boolean isWinCondition(){
        int counter1= 0;
            for (int i = 0; i < monitor1.length; i++) {
                for (int j = 0; j < monitor1[i].length; j++) {
                    if (monitor1[i][j]==2){
                        counter1++;
                    }
                }
            }
            int counter2= 0;
            for (int i = 0; i < monitor2.length; i++) {
                for (int j = 0; j < monitor2[i].length; j++) {
                    if (monitor2[i][j]==2){
                        counter2++;
                    }
                }
            }
            if (counter1>=21){
                System.out.println(player1 + " Вы выйграли! ");
                return true;
            }
            if (counter2>=21){
                System.out.println(player1 + " Вы выйграли! ");
                return true;
            }
            return false;
        }

        public static boolean isAvailable(int x, int y,int deck,int rotation,int [][] battlefield) {
            if (rotation == 1) {
                if (y + deck > battlefield.length) {
                    return false;
                }
                if (rotation == 2) {
                    if (x + deck > battlefield[0].length) {
                        return false;
                    }
                }
                while (deck != 0) {
                    for (int i = 0; i < deck; i++) {
                        int xi = 0;
                        int yi = 0;
                        if (rotation == 1) {
                            yi = i;
                        } else {
                            xi = i;
                        }
                        if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0) {
                            if (battlefield[x + 1 + xi][y + yi] != 0) {
                                return false;
                            }
                        }
                        if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0) {
                            if (battlefield[x - 1 + xi][y + yi] != 0) {
                                return false;
                            }
                        }
                        if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0) {
                            if (battlefield[x + xi][y + 1 + yi] != 0) {
                                return false;
                            }
                        }
                        if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0) {
                            if (battlefield[x + xi][y - 1 + yi] != 0) {
                                return false;
                            }
                        }
                    }

                }
                deck--;

            }
            return true;
    }

//

}
