import java.util.Scanner;

public class PwGen {

  public static int genRand(int lim) {
    return (int) (Math.random() * lim);
  }

  public static int setRule(boolean[] rule) {
    Scanner st = new Scanner(System.in);
    int allowedCharsets = 0;
    System.out.println("Enter 0 / 1 in given order to allow charsets\n1 : Capital\n2 : Small\n3 : Symbol\n4 : Digits");
    for (int i = 0; i < rule.length; i++) {
      rule[i] = st.nextInt() != 0 ? true : false;
      allowedCharsets += rule[i] ? 1 : 0;
    }
    return allowedCharsets;
  }

  public static String randomizeString(String str) {
    String randStr = "";
    int i;
    while (!str.equals("")) {
      i = genRand(str.length());
      randStr += str.charAt(i);
      str = str.substring(0, i) + str.substring(i + 1);
    }
    return randStr;
  }

  public static String filler(String str, char[][] charset, int lim, boolean[] rule) {
    String filledStr = "";
    int i;
    while (lim-- > 0) {
      i = genRand(4);
      while (rule[i] != true) {
        i = genRand(4);
      }
      int j = genRand(charset[i].length);
      filledStr += charset[i][j];
    }

    return filledStr;
  }

  public static void printInBox(String str){
    System.out.println("\n");
    for (int j = 0; j < str.length()+4; j++) {
        System.out.print("*");        
      }
      System.out.println("\n* "+str+" *");
      for (int j = 0; j < str.length()+4; j++) {
        System.out.print("*");        
      }
      System.out.println("\n");
  }

  public static void main(String[] args) {
    char ch ='y';
    do{
      Scanner sc = new Scanner(System.in);
      int i;
      char[][] charset = {
          { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
              'V',
              'W', 'X', 'Y', 'Z' },
          { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
              'v',
              'w', 'x', 'y', 'z' },
          { ',', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '{', '}', ':', '"', '<', '>', '?' },
          { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' }
      };
      boolean[] rule = new boolean[4];
      int allowedCharsets = 0;
      int pwLen;
      String pw = "";


      System.out.println("Enter Length");
      pwLen = sc.nextInt();
      
      allowedCharsets = setRule(rule);
      while(allowedCharsets==0){
        System.out.println("Select at least one character set to choose from");
        allowedCharsets = setRule(rule);
      }
      
      for (i = 0; i < 4; i++) {
        if (rule[i] == true) {
          pw += charset[i][genRand(10)];
        }
      }


      pw = randomizeString(pw);
      
      if (allowedCharsets > pwLen) {
        pw = pw.substring(0, pwLen);
      }

      pw += filler(pw, charset, pwLen - allowedCharsets, rule);
      
      printInBox(pw);

      System.out.print("Do you want to generate another password??(y/n) : ");
      ch=sc.next().charAt(0);

    }while(ch=='y' || ch=='Y');

    System.out.println("Feel free to come back and Generate another password for security");
  }
}


