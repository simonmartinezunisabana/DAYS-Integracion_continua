package edu.unisabana.dyas.notas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ConceptoTest {

    private final CalculadoraNotas calculadora = new CalculadoraNotas();

    // Incluye los valores límite de cada rango (4.5, 4.0 y 3.0) y el valor justo por debajo de cada uno.
    @ParameterizedTest(name = "definitiva {0} -> {1}")
    @CsvSource({
            "5.0, Excelente",
            "4.5, Excelente",
            "4.4, Sobresaliente",
            "4.0, Sobresaliente",
            "3.9, Aprobado",
            "3.0, Aprobado",
            "2.9, Reprobado",
            "0.0, Reprobado"
    })
    void clasificaLaDefinitivaSegunSuRango(double definitiva, String esperado) {
        assertEquals(esperado, calculadora.concepto(definitiva));
    }

    @Test
    void rechazaDefinitivasFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.concepto(5.1));
        assertThrows(IllegalArgumentException.class, () -> calculadora.concepto(-0.1));
    }
}
