import java.util.Scanner;

public class ConversorDeMoedas {

    Scanner leitura = new Scanner(System.in);
    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 7){
            var menu = """
            *********************************************
            Seja bem-vindo/a ao Conversor de Moeda :)
            
            1 - Dolár -> Peso argentino
            2 - Peso argentino -> Dólar
            3 - Dólar -> Real brasileiro
            4 - Real brasileiro -> Dólar
            5 - Dólar -> Peso colombiano
            6 - Peso colombiano -> Dólar
            7 - Sair
            
            Escolha uma opção válida:
            """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    conversor("USD", "ARS");
                    break;
                case 2:
                    conversor("ARS", "USD");
                    break;
                case 3:
                    conversor("USD", "BRL");
                    break;
                case 4:
                    conversor("BRL", "USD");
                    break;
                case 5:
                    conversor("USD", "COP");
                    break;
                case 6:
                    conversor("COP", "USD");
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private void conversor(String deMoeda, String paraMoeda) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor em " + deMoeda + ": ");
        double valor = scanner.nextDouble();

        try {
            double taxa = ConsumoApi.getExchangeRate(deMoeda, paraMoeda);
            double valorConvertido = valor * taxa;
            System.out.println("Valor convertido: " + valorConvertido + " " + paraMoeda);
        } catch (Exception e) {
            System.out.println("Erro ao obter a taxa de câmbio: " + e.getMessage());
        }
    }
}


