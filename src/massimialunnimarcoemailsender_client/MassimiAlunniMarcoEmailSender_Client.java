/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package massimialunnimarcoemailsender_client;

import java.util.Scanner;

/**
 *
 * @author massimialunni.marco
 */
public class MassimiAlunniMarcoEmailSender_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Inserisci il server SMTP (es. 127.0.0.1): ");
            String server = input.nextLine();

            SMTPClient client = new SMTPClient(server);

            System.out.print("Inserisci il nome del server locale (es. localhost): ");
            String serverName = input.nextLine();
            client.helo(serverName);

            System.out.print("Mittente: ");
            String from = input.nextLine();
            client.from(from);

            System.out.print("Destinatario: ");
            String to = input.nextLine();
            client.to(to);

            System.out.print("Oggetto: ");
            String subject = input.nextLine();

            System.out.println("Messaggio (termina con una riga vuota):");
            String message = "";
            String line;
            while (!(line = input.nextLine()).isEmpty()) {
                message += line + "\n";
            }

            client.data(subject, message);
            client.quit();

            System.out.println("\nMessaggio inviato correttamente!");

        } catch (Exception e) {
            System.out.println("Errore durante l'invio del messaggio: " + e.getMessage());
        }
        input.close();
    }
}
