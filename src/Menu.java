import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements MenuOpcoes{
    private ArrayList<Produtos> mostruario ;
    private ArrayList<Produtos> carinho ;
    private boolean sair = true;
    public Menu(){
        this.carinho = new ArrayList<Produtos>();
        this.mostruario = new ArrayList<Produtos>();
    }
    public boolean statusSair() {
        return this.sair;
    }
    public void addMostruario(Produtos produto) {
        this.mostruario.add(produto);
    }
    public void addCarinho(Produtos produto){
        this.carinho.add(produto);
    }

    @Override
    public void Sair() {
        this.sair = false;
    }

    public ArrayList<Produtos> getCarinho() {
        return carinho;
    }

    @Override
    public void fazerPedido(String nome) {
        Scanner input = new Scanner(System.in);
        Cliente cliente = new Cliente(nome);
        while (true) {
            int count = 1;
            System.out.println("Bom dia " + cliente.getNome() + " digite o numero do que deseja comprar: ");

            for (Produtos produto : mostruario) {
                System.out.println(count + ". " + produto.getNome() + ", Preço: " + produto.getValor() + " reais");
                count++;
                }
            System.out.println("0. Finalizar Pedido");
            try {
                int adicionar = input.nextInt();
                if (adicionar > count){
                    throw new ExceptionMenu("Esse item nao existe");
                } else if (adicionar == 0){
                    break;
                } else {

                    addCarinho(mostruario.get(adicionar - 1));
                }
            }
            catch (ExceptionMenu e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public double Total(){
        double total = 0;
        for (Produtos produto : carinho) {
            total += produto.getValor();
        }
        return total;
    }
}
