package vaishnavim;

import java.util.Scanner;
public class MainApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DataUsageDAO dao = new DataUsageDAOImpl();         DataUsageService service = new DataUsageService();
        while (true) {             System.out.println("\n1.Add Data Usage");
            System.out.println("2.View Usage");
            System.out.println("3.Pay Bill");             System.out.println("4.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:                     DataUsageDTO dto = new DataUsageDTO();
                    System.out.print("Mobile Number: ");                     dto.setMobileNo(sc.next());
                    System.out.print("Data Used (GB): ");                     dto.setDataUsed(sc.nextDouble());
                    service.calculateCharge(dto);                     dao.addUsage(dto);                     break;
                case 2:                     dao.viewUsage();                     break;
                case 3:                     System.out.print("Enter ID: ");                     int id = sc.nextInt();                     dao.payBill(id);                     break;
                case 4:                     System.exit(0);
            }
        }
    }
}
