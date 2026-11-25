
package com.krakedev.financiero.servicios.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestBanco {

    @Test
    public void testCrearPrimeraCuenta() {

        // Crear un banco y un cliente.
        Banco banco = new Banco();
        Cliente cliente = new Cliente("001", "Carlos", "Pérez");

        // Crear la primera cuenta.
        Cuenta cuenta = banco.crearCuenta(cliente);

        // Verificar que la cuenta existe.
        assertNotNull(cuenta);

        // Verificar que su ID inicial sea 1000.
        assertEquals("1000", cuenta.getId());

    }

    @Test
    public void testCodigosConsecutivos() {

        // Crear un banco y un cliente.
        Banco banco = new Banco();
        Cliente cliente = new Cliente("001", "Carlos", "Pérez");

        // Crear tres cuentas utilizando el mismo banco.
        Cuenta cuenta1 = banco.crearCuenta(cliente);
        Cuenta cuenta2 = banco.crearCuenta(cliente);
        Cuenta cuenta3 = banco.crearCuenta(cliente);

        // Verificar que los códigos sean consecutivos.
        assertEquals("1000", cuenta1.getId());
        assertEquals("1001", cuenta2.getId());
        assertEquals("1002", cuenta3.getId());

        // Verificar que sean objetos diferentes.
        assertNotSame(cuenta1, cuenta2);
        assertNotSame(cuenta1, cuenta3);
        assertNotSame(cuenta2, cuenta3);

    }

    @Test
    public void testIncrementoUltimoCodigo() {

        // Crear un banco y un cliente.
        Banco banco = new Banco();
        Cliente cliente = new Cliente("001", "Carlos", "Pérez");

        // Crear una cuenta.
        banco.crearCuenta(cliente);

        // Verificar que el siguiente código sea 1001.
        assertEquals(1001, banco.getUltimoCodigo());

    }

    @Test
    public void testSaldoInicial() {

        // Crear un banco y un cliente.
        Banco banco = new Banco();
        Cliente cliente = new Cliente("002", "María", "López");

        // Crear una cuenta.
        Cuenta cuenta = banco.crearCuenta(cliente);

        // Verificar que el saldo inicial sea cero.
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);

    }

    @Test
    public void testTipoCuentaAhorros() {

        // Crear un banco y un cliente.
        Banco banco = new Banco();
        Cliente cliente = new Cliente("002", "María", "López");

        // Crear una cuenta.
        Cuenta cuenta = banco.crearCuenta(cliente);

        // Verificar que sea una cuenta de ahorros.
        assertEquals("A", cuenta.getTipo());

    }

    @Test
    public void testPropietarioAsignado() {

        // Crear un banco y un cliente.
        Banco banco = new Banco();
        Cliente cliente = new Cliente("003", "Pedro", "García");

        // Crear una cuenta para el cliente.
        Cuenta cuenta = banco.crearCuenta(cliente);

        // Verificar que sea el mismo objeto Cliente.
        assertSame(cliente, cuenta.getPropietario());

        // Verificar los datos del propietario.
        assertEquals("003", cuenta.getPropietario().getCedula());
        assertEquals("Pedro", cuenta.getPropietario().getNombre());
        assertEquals("García", cuenta.getPropietario().getApellido());

    }

    @Test
    public void testCuentasConDiferentesPropietarios() {

        // Crear un banco y dos clientes diferentes.
        Banco banco = new Banco();

        Cliente cliente1 = new Cliente("001", "Carlos", "Pérez");
        Cliente cliente2 = new Cliente("002", "María", "López");

        // Crear una cuenta para cada cliente.
        Cuenta cuenta1 = banco.crearCuenta(cliente1);
        Cuenta cuenta2 = banco.crearCuenta(cliente2);

        // Verificar que cada cuenta conserve su propietario.
        assertSame(cliente1, cuenta1.getPropietario());
        assertSame(cliente2, cuenta2.getPropietario());

        // Verificar que los propietarios sean diferentes.
        assertNotSame(cuenta1.getPropietario(),
                      cuenta2.getPropietario());

        // Verificar que los códigos sean consecutivos.
        assertEquals("1000", cuenta1.getId());
        assertEquals("1001", cuenta2.getId());

    }
    
    @Test
    public void testDepositarCuentaVacia() {

        // Crear un banco y una cuenta con saldo inicial de 0.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Realizar un depósito de 50.
        boolean resultado = banco.depositar(50, cuenta);

        // Verificar que el depósito fue exitoso.
        assertTrue(resultado);

        // Verificar que el saldo actual sea 50.
        assertEquals(50.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testDepositarConSaldoExistente() {

        // Crear un banco y una cuenta.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Establecer un saldo inicial de 100.
        cuenta.setSaldoActual(100);

        // Realizar un depósito de 50.
        boolean resultado = banco.depositar(50, cuenta);

        // Verificar que el depósito fue exitoso.
        assertTrue(resultado);

        // Verificar que el nuevo saldo sea 150.
        assertEquals(150.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testDepositarMontoCero() {

        // Crear un banco y una cuenta.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Establecer un saldo inicial de 100.
        cuenta.setSaldoActual(100);

        // Intentar realizar un depósito de 0.
        boolean resultado = banco.depositar(0, cuenta);

        // Verificar que el depósito fue rechazado.
        assertFalse(resultado);

        // Verificar que el saldo no fue modificado.
        assertEquals(100.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testDepositarMontoNegativo() {

        // Crear un banco y una cuenta.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Establecer un saldo inicial de 100.
        cuenta.setSaldoActual(100);

        // Intentar realizar un depósito negativo.
        boolean resultado = banco.depositar(-50, cuenta);

        // Verificar que el depósito fue rechazado.
        assertFalse(resultado);

        // Verificar que el saldo permanece igual.
        assertEquals(100.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testDepositarMontoDecimal() {

        // Crear un banco y una cuenta.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Establecer un saldo inicial de 100.
        cuenta.setSaldoActual(100);

        // Realizar un depósito con decimales.
        boolean resultado = banco.depositar(25.75, cuenta);

        // Verificar que el depósito fue exitoso.
        assertTrue(resultado);

        // Verificar que el nuevo saldo sea 125.75.
        assertEquals(125.75, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testDepositosConsecutivos() {

        // Crear un banco y una cuenta.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Establecer un saldo inicial de 100.
        cuenta.setSaldoActual(100);

        // Realizar el primer depósito.
        boolean resultado1 = banco.depositar(50, cuenta);

        // Verificar el primer depósito.
        assertTrue(resultado1);
        assertEquals(150.0, cuenta.getSaldoActual(), 0.0001);

        // Realizar el segundo depósito.
        boolean resultado2 = banco.depositar(25, cuenta);

        // Verificar el segundo depósito.
        assertTrue(resultado2);
        assertEquals(175.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarMontoValido() {

        // Preparar un banco y una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Realizar un retiro de 30.
        boolean resultado = banco.retirar(30, cuenta);

        // Verificar que el retiro fue exitoso.
        assertTrue(resultado);

        // Verificar el saldo restante.
        assertEquals(70.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarSaldoCompleto() {

        // Preparar una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Retirar todo el dinero disponible.
        boolean resultado = banco.retirar(100, cuenta);

        // Verificar que el retiro fue exitoso.
        assertTrue(resultado);

        // Verificar que el saldo quede en cero.
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarSaldoInsuficiente() {

        // Preparar una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Intentar retirar un monto superior al saldo.
        boolean resultado = banco.retirar(150, cuenta);

        // Verificar que el retiro sea rechazado.
        assertFalse(resultado);

        // Verificar que el saldo no sea modificado.
        assertEquals(100.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarMontoCero() {

        // Preparar una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Intentar retirar cero.
        boolean resultado = banco.retirar(0, cuenta);

        // Verificar que la operación sea rechazada.
        assertFalse(resultado);

        // Verificar que el saldo permanezca igual.
        assertEquals(100.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarMontoNegativo() {

        // Preparar una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Intentar retirar un monto negativo.
        boolean resultado = banco.retirar(-20, cuenta);

        // Verificar que el retiro sea rechazado.
        assertFalse(resultado);

        // Verificar que el saldo no aumente ni disminuya.
        assertEquals(100.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarCuentaVacia() {

        // Crear una cuenta con saldo inicial de cero.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");

        // Intentar retirar 50.
        boolean resultado = banco.retirar(50, cuenta);

        // Verificar que el retiro sea rechazado.
        assertFalse(resultado);

        // Verificar que el saldo permanezca en cero.
        assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirarMontoDecimal() {

        // Preparar una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Retirar un monto con decimales.
        boolean resultado = banco.retirar(25.75, cuenta);

        // Verificar que el retiro sea exitoso.
        assertTrue(resultado);

        // Verificar el saldo restante con tolerancia.
        assertEquals(74.25, cuenta.getSaldoActual(), 0.0001);

    }
    
    @Test
    public void testRetirosConsecutivos() {

        // Preparar una cuenta con saldo de 100.
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1000");
        cuenta.setSaldoActual(100);

        // Realizar el primer retiro.
        boolean resultado1 = banco.retirar(30, cuenta);

        // Verificar el primer retiro.
        assertTrue(resultado1);
        assertEquals(70.0, cuenta.getSaldoActual(), 0.0001);

        // Realizar el segundo retiro.
        boolean resultado2 = banco.retirar(20, cuenta);

        // Verificar el segundo retiro.
        assertTrue(resultado2);
        assertEquals(50.0, cuenta.getSaldoActual(), 0.0001);

    }

}

