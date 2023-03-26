package bullscows;

public class Grader {

    private char[] secretCode;
    private char[] code;
    private int bulls = 0;
    private int cows = 0;

     public Grader(char[] secretCode, String code){
        this.secretCode = secretCode;
        this.code = code.toCharArray();
    }

    public void grade() {
         boolean isCow = false;
         for (int i = 0; i < secretCode.length; i++) {
             for (int j = 0; j < code.length; j++) {
                 if(secretCode[i] == code[j]) {
                     isCow = true;
                     if(i == j){
                         this.bulls++;
                         isCow = false;
                         break;
                     }
                 }
             }
             if (isCow) {
                 this.cows++;
                 isCow = false;
             }
         }
    }

    public void getGrade() {
         if (bulls != 0 && cows != 0) {
             System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s). ");
         } else if (bulls != 0) {
             System.out.println("Grade: " + bulls + " bull(s). ");
         } else if (cows != 0) {
             System.out.println("Grade: " + cows + " cow(s).");
         } else {
             System.out.println("None.");
         }
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }
}
