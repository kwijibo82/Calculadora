/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aec3_mp_javier_mora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Javi
 */
public class Escuchador implements ActionListener  {

    private Calculadora c;

    public Escuchador(Calculadora c, ActionEvent event) {
        this.c = c;
        this.actionPerformed(event);
    }
    
    /**
     * Sobreescritura del método actionPerformed
     * @param event 
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        boolean borrar = false;
        boolean array = false;
        int resul = 0;
        ArrayList<Integer> arrayResuls = null;
        
        switch (event.getActionCommand())
        {
            case "Elevar al cubo":  
                resul = (int) Math.pow(c.leerEntrada1(), 3);
                break;
            case "MCM":
                resul = calcularMCM(c.leerEntrada1(), c.leerEntrada2());
                break;
            case "MCD":
                resul = calcularMCD(c.leerEntrada1(), c.leerEntrada2());
                break;    
            case "Descomponer":
                array = true;
                arrayResuls = hallarDivisores(c.leerEntrada1());
                break;
            case "Combinatorio":
                resul = calcularCombinatorio(c.leerEntrada1(),
                        c.leerEntrada2());
                break;
            case "Borrar":   
                borrar = true;
                c.borraDatos();
                break; 
            default:
                break;
        }
       
        if (resul != 0 && !borrar) {
            
            c.escribirSalida(resul);            
        }
        else if (array && !borrar)
        {
            c.escribirSalidaArray(arrayResuls);
        }
        
    }
    
    /**
     * Método que calcula el MCD de dos números pasados
     * como parámetro
     * @param num1
     * @param num2
     * @return 
     */
    public int calcularMCD(int num1, int num2) {
        int mcd = 0;
        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);
        do {
            mcd = b;
            b = a%b;
            a = mcd;
        } while(b!=0);
        return mcd;
    }
    
    /**
     * Método que calcula el MCM de dos números pasados 
     * como parámetro
     * @param num1
     * @param num2
     * @return 
     */
    public int calcularMCM(int num1, int num2)
    {
        int mcm = 0;
        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);
        mcm = (a/calcularMCD(a, b))*b;
       
        return mcm;
    }
    
    /**
     * Método que busca todos los posibles divisores de un 
     * número pasado como parámetrom, devuelve un ArrayList
     * de enteros con los divisores obtentenidos.
     * @param num
     * @return 
     */
    public ArrayList<Integer> hallarDivisores(int num)
    {
        ArrayList<Integer> arrayDivisores = new ArrayList<>();
        
        for (int i = 1 ; i <= num ; i++)
        {
            if (num % i == 0)
            {
                arrayDivisores.add(i);
            }
        }
        
        return arrayDivisores;
    }
    
    
    /**
     * Método que calcula el combinatorio de dos números
     * pasados como parámetro, devuelve un entero que es
     * dicho número (el combinatorio)
     * @param num1
     * @param num2
     * @return 
     */
    public int calcularCombinatorio(int num1, int num2)
    {
        int factorialnum1 = 1;
        int factorialnum2 = 1;
        int factorialnum1ynum2 = 1;
        int combinatoria = 0;
        
        if (num1 < num2) {
            System.out.println();
            
            JOptionPane.showMessageDialog(c, "El valor del primer campo debe"
                    + " ser mayor o igual al valor del segundo campo.");
        }
        else
        {
            //Obtiene el factorial del primer parámtero
            for (int i = num1; i > 0; i--) {
                if (num1 == 0 || num1 == 1) {
                    factorialnum1 = 1;
                    break;
                }
                factorialnum1 = i * factorialnum1;
            }

            //Obtiene el fatorial del segundo parámetro
            for (int i = num2; i > 0; i--) {
                if (num2 == 0 || num2 == 1) {
                    factorialnum2 = 1;
                    break;
                }
                factorialnum2 = i * factorialnum2;
            }

            //Obtiene el factorial de ambos parámetros
            for (int i = num1 - num2; i > 0; i--) {
                if (num1 - num2 == 0 || num1 - num2 == 1) {
                    factorialnum1ynum2 = 1;
                    break;
                }
                factorialnum1ynum2 = i * factorialnum1ynum2;
            }

            //Calacula el número combinatorio
            combinatoria = factorialnum1 / (factorialnum2 * factorialnum1ynum2);
        }       
        return combinatoria;
        
    }
    

}
