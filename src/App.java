import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

import com.empresa.modelo.Funcionario;
import com.empresa.metodo.Contratacao;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Nome: Nascimento: Salario: Funcao:");
        List<Funcionario> funcionarios = new ArrayList<>();
        Contratacao contratacao = new Contratacao(); 
        funcionarios.add(contratacao.contratado("Maria", "18/10/2000", new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(contratacao.contratado("João", "12/05/1990", new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(contratacao.contratado("Caio", "02/05/1961", new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(contratacao.contratado("Miguel", "14/10/1988", new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(contratacao.contratado("Alice", "05/01/1995", new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(contratacao.contratado("Heitor", "19/11/1999", new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(contratacao.contratado("Arthur", "31/03/1993", new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(contratacao.contratado("Laura", "08/07/1994", new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(contratacao.contratado("Heloisa", "24/05/2003", new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(contratacao.contratado("Helena", "02/10/1996", new BigDecimal("2799.93"), "Gerente"));
        DecimalFormat formatter = new DecimalFormat("#,##0.00");

        for (Funcionario funcionario : funcionarios) {

            String formataSalario = formatter.format(funcionario.getSalario().doubleValue());
            System.out.println(funcionario.getNome() + " " + funcionario.getNascimento() + " " + formataSalario + " " + funcionario.getFuncao());
    }
    
    //remove o João
    funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("    Funcionários após remover João:");
        for (Funcionario funcionario : funcionarios) {
            String formataSalario = formatter.format(funcionario.getSalario().doubleValue());
                System.out.println(funcionario.getNome() + " " + funcionario.getNascimento() + " " + formataSalario + " " + funcionario.getFuncao());
    }
    //aumentos de 10%
    System.out.println("    Bonus de 10% ! os salarios agora são:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("1.1"));
                funcionario.setSalario(aumento);
                     String formataSalario = formatter.format(funcionario.getSalario().doubleValue());
        System.out.println(funcionario.getNome() + " " + funcionario.getNascimento() + " " + formataSalario + " " + funcionario.getFuncao());
    }
    //Ordem por funcao do funcionario
    Map<String, List<Funcionario>> FuncaoFuncionario = new HashMap<>();
    for (Funcionario funcionario : funcionarios) {
        String funcao = funcionario.getFuncao();
        if (FuncaoFuncionario.containsKey(funcao)) {
            FuncaoFuncionario.get(funcao).add(funcionario);
        } else {
            List<Funcionario> listaFuncionarios = new ArrayList<>();
            listaFuncionarios.add(funcionario);
            FuncaoFuncionario.put(funcao, listaFuncionarios);
        }
    }
    System.out.println("    Funcionários por Função:");
        for (Map.Entry<String, List<Funcionario>> entry : FuncaoFuncionario.entrySet()) {
            System.out.println("Função: " + entry.getKey());
                for (Funcionario funcionario : entry.getValue()) {
                    System.out.println("  - " + funcionario.getNome());
                }
            }
        
     //Nascidos entre out e dez
     List<Funcionario> funcionarioFiltrados = funcionarios.stream()
        .filter(f -> {
            int mesNascimento = f.getNascimento().getMonthValue();
            return mesNascimento >= 10 && mesNascimento <= 13;
        })
        .collect(Collectors.toList());
            System.out.println("    Funcionários nascidos entre Out e Dez:");
            for (Funcionario funcionario : funcionarioFiltrados) {
                System.out.println(funcionario.getNome() + "    aniversario:    " + funcionario.getNascimentoFormatado());
            }
    //Funcionario mais velho
    //optional para melhor implementação de .stream .min e .compareTo
    Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream()
        .min( (f1, f2) -> f1.getNascimento()
        .compareTo(f2.getNascimento()));
    System.out.println("    Funcionario mais velho: ");
        if(funcionarioMaisVelho.isPresent()) {
            Funcionario funcionario = funcionarioMaisVelho.get();
            System.out.println(funcionario.getNome() + "    Nasceu em: " + funcionario.getNascimentoFormatado());
        }
    //Ordem Alfabetica
    funcionarios.sort(Comparator.comparing(Funcionario :: getNome));
    System.out.println("    Ordem alfabetica:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }
    //Total de funcionarios
    for(int i = 0; i < funcionarios.size(); i++);
    System.out.println("    Total de funcionarios: " + funcionarios.size());
    
    //Comparar salario com salario Minimo (1212.00)System.out.println("    Quantos salarios minimon?:");
    BigDecimal salarioMinimo = new BigDecimal("1212.00");
    for(Funcionario funcionario : funcionarios) {
        DecimalFormat df = new DecimalFormat("#,##0.0");
        BigDecimal quantosSalariosMin = funcionario.getSalario().divide(salarioMinimo,  BigDecimal.ROUND_HALF_EVEN);
        String formattedQuantosSalariosMin = df.format(quantosSalariosMin);
        System.out.println("O salario de " + funcionario.getNome() + " é " + formattedQuantosSalariosMin + " vezes maior que o valor de um salario minimo");
        }
    }
}
