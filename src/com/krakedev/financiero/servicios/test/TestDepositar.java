
package com.krakedev.financiero.servicios.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestDepositar {

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

}
