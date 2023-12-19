import java.util.Scanner;

public class CobraApp {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String next = "si";

        while (next.equals("si")) {
            int errorCode = Client.processCustomer();

            if (errorCode != 0)
                System.out.println(Client.ERROR.get(errorCode));
            
            System.out.print("Desea continuar?[si/no (default=no)]: ");
            next = sc.nextLine();
        }

        System.out.println("FIN DEL PROGRAMA");

    }
}
