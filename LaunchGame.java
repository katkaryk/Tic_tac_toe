import java.util.*;
import java.util.Random;
class TicTacToe
{
   static char[][] board;       // if you want access something just by the class name and not by the object then just declare it as a static.
    public TicTacToe()
    {
       board = new char[3][3];
       initBoard();
    }
    void initBoard()
    {
        for (int i=0;i< board.length;i++)
        {
            for(int j=0;j<board[i].length ; j++)
            {
                board[i][j] = ' ';
            }
        }
    }

   static void displayBoard()
    {
        System.out.println("-------------");
        for (int i=0;i< board.length;i++)
        {
            System.out.print("| ");
            for(int j=0;j<board[i].length ; j++)
            {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void placeMark(int row, int column, char mark)
    {
       if(row >=0 && row <=2 && column >=0 && column <=2)
       {
           board[row][column] = mark;
       }
       else {
           System.out.println("invalid input");
       }
    }
    static boolean checkColWin()
    {
        for(int j=0; j<=2 ; j++)
        {
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;
    }
    static   boolean checkRowWin()
    {
        for(int i=0;i<=2;i++)
        {
            if(board[i][0] != ' ' &&board[i][0] == board[i][1] && board[i][1] == board[i][2])
            {
                return true;
            }
        }
        return false;
    }
    static  boolean chechDiagWin()
     {
        if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] != ' ' &&  board[0][2]==board[1][1] && board[1][1]==board[2][0])
        {
            return true;
        }
        return false;
    }
    static boolean checkDrop()
    {
        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++)
            {
                if(board[i][j]==' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}
abstract class player
{
    String name;
    char mark;
    abstract void makeMove();
    boolean isValidMove(int rows, int columns)
    {
        if(rows >= 0 && rows <= 2 && columns >=0 && columns <=2)
        {
            if(TicTacToe.board[rows][columns]==' ')
            {
                return true;
            }
        }
        return false;
    }

}

class HumanPlayer extends player
{
    String name;
    char mark;
    HumanPlayer(String name, char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner ob = new Scanner(System.in);
        int rows,columns;
       do {
           System.out.println("enter the row and columns");
           rows = ob.nextInt();
           columns= ob.nextInt();
       } while (!isValidMove(rows,columns));
       TicTacToe.placeMark(rows, columns , mark);
    }

   /* boolean isValidMove(int rows, int columns)
    {
        if(rows >= 0 && rows <= 2 && columns >=0 && columns <=2)
        {
            if(TicTacToe.board[rows][columns]==' ')
            {
                return true;
            }
        }
        return false;
    }*/
}
class AIPLayer extends player
{

    AIPLayer(String name, char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner ob = new Scanner(System.in);
        int rows,columns;
        do {
           Random r = new Random();
           rows= r.nextInt(3);
           columns=r.nextInt(3);
        } while (!isValidMove(rows , columns));
        TicTacToe.placeMark(rows, columns , mark);
    }

   /* boolean isValidMove(int rows, int columns)
    {
        if(rows >= 0 && rows <= 2 && columns >=0 && columns <=2)
        {
            if(TicTacToe.board[rows][columns]==' ')
            {
                return true;
            }
        }
        return false;
    }*/
}


public class LaunchGame
{
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
      //  t.displayBoard();
      /*  System.out.println(t.checkColWin());
        System.out.println(t.checkRowWin());
        System.out.println(t.chechDiagWin());
        t.displayBoard();*/
       HumanPlayer p1= new HumanPlayer("Yash",'X');
       AIPLayer p2 = new AIPLayer("AI",'O');
      // HumanPlayer p2 = new HumanPlayer("sahil",'O');
        t.displayBoard();
       player cp;
       cp = p1;
       while(true)
       {
           System.out.println(cp.name +" turn");
           cp.makeMove();
           t.displayBoard();
           if(TicTacToe.chechDiagWin() || TicTacToe.checkColWin() || TicTacToe.checkRowWin())
           {
               System.out.println(cp.name + " has won the game");
               break;
           } else if (TicTacToe.checkDrop())
           {
               System.out.println("game is a draw");
               break;
           } else
           {
               if(cp ==p1)
               {
                   cp=p2;
               }
               else
               {
                   cp=p1;
               }
           }
       }
    }
}