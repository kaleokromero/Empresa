package com.empresa.metodo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.empresa.modelo.Funcionario;

public class Contratacao {
    public Funcionario contratado(String nome, String nascimento, BigDecimal salario, String funcao) {
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern("#,##0.00");  // Use # for optional digits, 0 for required digits
        String salarioFormatado = df.format(salario);
       
        return new Funcionario(nome, nascimento, salario, funcao);
    }
}
