
package com.krakedev.financiero.servicios.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestRetirar {

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
