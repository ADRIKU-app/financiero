
package com.krakedev.financiero.servicios.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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

}

