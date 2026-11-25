
package com.krakedev.financiero.servicios.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestTransferir {

	@Test
	public void testTransferirMontoValido() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(200);
		destino.setSaldoActual(50);

		// Realizar una transferencia de 75.
		boolean resultado = banco.transferir(75, origen, destino);

		// Verificar que la transferencia fue exitosa.
		assertTrue(resultado);

		// Verificar el saldo de ambas cuentas.
		assertEquals(125.0, origen.getSaldoActual(), 0.0001);
		assertEquals(125.0, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferirSaldoCompleto() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		// Transferir la totalidad del saldo de origen.
		boolean resultado = banco.transferir(100, origen, destino);

		// Verificar que la transferencia fue exitosa.
		assertTrue(resultado);

		// Verificar que la cuenta origen quede en cero.
		assertEquals(0.0, origen.getSaldoActual(), 0.0001);

		// Verificar el nuevo saldo de destino.
		assertEquals(150.0, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferirSaldoInsuficiente() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		// Intentar transferir más dinero del disponible.
		boolean resultado = banco.transferir(150, origen, destino);

		// Verificar que la transferencia fue rechazada.
		assertFalse(resultado);

		// Verificar que ninguno de los saldos cambie.
		assertEquals(100.0, origen.getSaldoActual(), 0.0001);
		assertEquals(50.0, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferirMontoCero() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		// Intentar transferir un monto de cero.
		boolean resultado = banco.transferir(0, origen, destino);

		// Verificar que la transferencia fue rechazada.
		assertFalse(resultado);

		// Verificar que los saldos permanezcan iguales.
		assertEquals(100.0, origen.getSaldoActual(), 0.0001);
		assertEquals(50.0, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferirMontoNegativo() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		// Intentar transferir un monto negativo.
		boolean resultado = banco.transferir(-20, origen, destino);

		// Verificar que la transferencia fue rechazada.
		assertFalse(resultado);

		// Verificar que los saldos no sean modificados.
		assertEquals(100.0, origen.getSaldoActual(), 0.0001);
		assertEquals(50.0, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferirCuentaOrigenVacia() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// La cuenta origen tiene saldo inicial de cero.
		destino.setSaldoActual(50);

		// Intentar transferir 25 desde la cuenta vacía.
		boolean resultado = banco.transferir(25, origen, destino);

		// Verificar que la transferencia fue rechazada.
		assertFalse(resultado);

		// Verificar que ninguna cuenta cambie su saldo.
		assertEquals(0.0, origen.getSaldoActual(), 0.0001);
		assertEquals(50.0, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferirMontoDecimal() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		// Realizar una transferencia con decimales.
		boolean resultado = banco.transferir(25.75, origen, destino);

		// Verificar que la transferencia fue exitosa.
		assertTrue(resultado);

		// Verificar los saldos utilizando una tolerancia.
		assertEquals(74.25, origen.getSaldoActual(), 0.0001);
		assertEquals(75.75, destino.getSaldoActual(), 0.0001);

	}

	@Test
	public void testTransferenciasConsecutivas() {

		// Crear un banco y dos cuentas.
		Banco banco = new Banco();
		Cuenta origen = new Cuenta("1000");
		Cuenta destino = new Cuenta("1001");

		// Establecer los saldos iniciales.
		origen.setSaldoActual(200);
		destino.setSaldoActual(50);

		// Realizar la primera transferencia.
		boolean resultado1 = banco.transferir(50, origen, destino);

		// Verificar la primera operación.
		assertTrue(resultado1);
		assertEquals(150.0, origen.getSaldoActual(), 0.0001);
		assertEquals(100.0, destino.getSaldoActual(), 0.0001);

		// Realizar la segunda transferencia.
		boolean resultado2 = banco.transferir(25, origen, destino);

		// Verificar la segunda operación.
		assertTrue(resultado2);
		assertEquals(125.0, origen.getSaldoActual(), 0.0001);
		assertEquals(125.0, destino.getSaldoActual(), 0.0001);

	}

}
