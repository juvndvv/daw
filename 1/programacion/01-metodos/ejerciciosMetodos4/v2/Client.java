import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Client {

    private String name;
    private String contractNumber;
    private int previousKWConsumed;
    private int actualKWConsumed;
    private Date previousDate;
    private Date actualDate;
    private float hiredPotency;

    final private static List<Float> POTENCIAS = Arrays.asList(2.3f, 3.45f, 4.6f, 5.75f, 6.9f, 8.05f);
    final private static List<Float> PRECIO = Arrays.asList(0.24f, 0.36f, 0.49f, 0.61f, 0.72f, 0.85f);

    // Codigos de error
    final public static List<String> ERROR = Arrays.asList("",
        "El numero de contrato no es valido",
        "La fecha de la lectura anterior no es correcta",
        "Los kW de la anterior lectura no son correctos",
        "Los kW de la lectura actual no son correctos o es menor a los de la anterior lectura",
        "La fecha de la lectura actual no es correcta o es anterior a la de la lectura previa",
        "La potencia contratada no es valida"
    );

    public Client (String name, String contractNumber, int previousKWConsumed, int actualKWConsumed, Date previousDate, Date actualDate, float hiredPotency) {
        this.name = name;
        this.contractNumber = contractNumber;
        this.previousKWConsumed = previousKWConsumed;
        this.actualKWConsumed = actualKWConsumed;
        this.previousDate = previousDate;
        this.actualDate = actualDate;
        this.hiredPotency = hiredPotency;
    }

    public int getKWConsumed() {
        return this.actualKWConsumed - this.previousKWConsumed; 
    }

    public float getPrice() {
        float fixedPrice = this.getFixedPrice();
        return getVariablePrice(fixedPrice) + fixedPrice;
    }

    public float getFixedPrice() {
        int daysBetween = previousDate.daysBetween(actualDate);
        return daysBetween * this.getPrizePerDay();
    }

    public float getVariablePrice(float fixedPrice) {
        int kWConsumed = this.getKWConsumed();
        if ((kWConsumed) < 150)
            return 0;
        else if (kWConsumed < 300)
            return fixedPrice *.05f;
        else if (kWConsumed < 400)
            return fixedPrice * .08f;
        else
            return fixedPrice * .12f;
        }

    public String toString() {
        return  String.format(
            "-------------------------------------------%n" +
            "%s Nº %s%n" +
            "-------------------------------------------%n" +
            "Total consumed: %d kWh%n" +
            "Hired potency: %.2f kW%n" +
            "Variable price: %.2f $%n" +
            "Total price: $%.2f $%n",
            this.name, this.contractNumber, this.getKWConsumed(), this.hiredPotency, 
            this.getVariablePrice(this.getFixedPrice()), this.getFixedPrice());
    }

    public static int processCustomer() {
        Scanner sc = new Scanner(System.in);
        // Nombre del cliente
        System.out.print("Introduce el nombre del cliente: ");
        String name = sc.nextLine();
        
        System.out.print("Introduce el numero del contrato: ");
        String contractNumber = sc.nextLine();
        if (!isValidContractNumber(contractNumber))
            return 1;

        // Fecha de la anterior lectura
        System.out.print("Introduce la fecha de la anterior lectura: ");
        Date previousDate;
        try {
            previousDate = Date.readDate();
        } catch (Exception e) {
            return 2;
        }

        // kW de la anterior lectura
        System.out.print("Introduce los kW de la anterior lectura: ");
        String previousKWConsumedStr = sc.nextLine();
        if (!isValidKWConsumed(previousKWConsumedStr))
            return 3;
        int previousKWConsumed = Integer.parseInt(previousKWConsumedStr);
        
        // Fecha de la actual lectura
        System.out.print("Introduce la fecha de la lectura actual: ");
        Date actualDate;
        try {
            actualDate = Date.readDate();
        } catch (Exception e) {
            System.out.println("1Aqui");
            return 4;
        }

        if (actualDate.compareTo(previousDate) >= 0){
            return 4;
        }

        sc.nextLine();

        // kW de la lectura actual
        System.out.print("Introduce los kW de la lectura actual: ");
        String actualKWConsumedStr = sc.nextLine();
        if (!isValidKWConsumed(actualKWConsumedStr, previousKWConsumed))
            return 5;
        int actualKWConsumed = Integer.parseInt(actualKWConsumedStr);

        // Potencia contratada
        System.out.print("Introduce la potencia contratada: ");
        String hiredPotencyStr = sc.nextLine();
        if (!isValidHiredPotency(hiredPotencyStr))
            return 6;
        float hiredPotency = Float.parseFloat(hiredPotencyStr);

        Client c = new Client(name, contractNumber, previousKWConsumed, actualKWConsumed, previousDate, actualDate, hiredPotency);
        System.out.println(c);

        return 0;
    }

    public static boolean isValidContractNumber(String number) {
        return number.matches("\\d{3}-\\d{4}");
    }

    public static boolean isValidHiredPotency(String potencyStr) {
        float potency;

        try {
            potency = Float.parseFloat(potencyStr);
        } catch(Exception e) {
            return false;
        }

        if (!POTENCIAS.contains(potency))
            return false;
        
        return true;
    }

    public static boolean isValidKWConsumed(String kWConsumedStr) {
        int kWConsumed;
        try {
            kWConsumed = Integer.parseInt(kWConsumedStr);
        } catch (Exception e) {
            return false;
        }

        if (kWConsumed < 0)
            return false;
        
        return true;
    }

    public static boolean isValidKWConsumed(String kWConsumedStr, int previous) {
        int kWConsumed;
        try {
            kWConsumed = Integer.parseInt(kWConsumedStr);
        } catch (Exception e) {
            return false;
        }

        if (kWConsumed < previous)
            return false;
        
        return true;
    }

    private float getPrizePerDay() {
        return PRECIO.get(POTENCIAS.indexOf(this.hiredPotency));
    }
}
