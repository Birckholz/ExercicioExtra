import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

public class Main {
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error clearing console: " + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        Produtos product1 = new Produtos("Camisa", 120);
        Produtos product2 = new Produtos("Calca", 110);
        Produtos product3 = new Produtos("Meia", 100);
        Produtos product4 = new Produtos("Camiseta", 10);
        Produtos product5 = new Produtos("Alca", 12);
        Menu menu1 = new Menu();
        DecimalFormat df = new DecimalFormat("#.##");
        menu1.addMostruario(product1);
        menu1.addMostruario(product2);
        menu1.addMostruario(product3);
        menu1.addMostruario(product4);
        menu1.addMostruario(product5);
        Scanner input = new Scanner(System.in);
        boolean sair = true;
        while (sair) {
            try {
                System.out.println("Digite o que deseja fazer: \n1.Fazer Pedido\n2.Sair");
                int escolha = input.nextInt();
                if (escolha != 1 && escolha != 2) {
                    throw new ExceptionMenu("Input tem que ser 1 ou 2");
                }
                else if (escolha == 2){
                    sair = false;
                    continue;
                }

            } catch (ExceptionMenu e) {
                input.next();
                clearConsole();
                continue;
            }
            System.out.println("Informe Seu Nome Cliente");
            String nome = input.next();
            menu1.fazerPedido(nome);
            int count = 1;
            System.out.println(nome + " voce possui no seu carrinho:");
            for (Produtos produto: menu1.getCarinho()) {
                System.out.println(count + ". "+ produto.getNome() + ": " + produto.getValor() + " reais");
                count ++;
            }
            double total = menu1.Total();
            double servico =(total * 0.10);
            String formatted = String.format("%.2f", servico);
            formatted = formatted.replace(",", ".");
            double formatado = Double.parseDouble(formatted);
            total += formatado;
            System.out.println("O valor da taxa de servico: " + formatado + " reais");
            System.out.println("O valor total da compra: " + total + " reais");
            try {
                System.out.println("Quanto de dinheiro o senhor deseja dar: ");
                int quantoPago = input.nextInt();
                if (total > quantoPago) {
                    throw new ExceptionMenu("Voce nao pagou toda a conta");
                } else {

                    double troco = quantoPago - total;
                    String fort = String.format("%.2f", troco);
                    fort = fort.replace(",", ".");
                    double formatadoTroco = Double.parseDouble(fort);
                    System.out.println("O Senhor receberá " + formatadoTroco +  " reais de Troco");

                }
            } catch (ExceptionMenu e) {
                input.next();
                continue;
            }

        }
    }

}