package com.utfpr.cp.projetotravissonar.projetotravissonar;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author andreend
 */
public class AlunoTest {

    @Test
    public void testNotaAtividadesTresNotas() {
        Aluno aluno = new Aluno("RA", "Joao Da Silva");
        aluno.addNotaAtividadePratica(10.0);
        aluno.addNotaAtividadePratica(5.0);
        aluno.addNotaAtividadePratica(10.0);
        assertEquals((10.0 + 5.0 + 10.0) / 3.0, aluno.getNAP(), 0.01);
    }

    @Test
    public void testNotaAtividadesSemNota() {
        Aluno aluno = new Aluno("RA", "Joao Da Silva");
        assertEquals(0.0, aluno.getNAP(), 0.01);
    }

    @Test
    public void testNotaFinal() {
        Aluno aluno = new Aluno("RA", "Joao Da Silva");

        Aluno alunoSpy = spy(aluno);
        when(alunoSpy.getNAP()).thenReturn(7.0);

        alunoSpy.setNotaProjeto(7.0);
        assertEquals((7.0 + 7.0) / 2.0, alunoSpy.getNF(), 0.01);
    }

    @Test
    public void testNotaFinalIntegrandoComAtividadesPraticas() {
        Aluno aluno = new Aluno("RA", "Joao Da Silva");

        aluno.addNotaAtividadePratica(5.0);
        aluno.addNotaAtividadePratica(10.0);
        aluno.addNotaAtividadePratica(7.0);
        aluno.addNotaAtividadePratica(0.0);
        aluno.addNotaAtividadePratica(1.0);

        aluno.setNotaProjeto(7.0);

        assertEquals((7.0 + (5.0 + 10.0 + 7.0 + 0.0 + 1.0) / 5.0) / 2.0, aluno.getNF(), 0.01);
    }

    @Test
    public void testNotaFinalComRAANaoValido() {
        Aluno aluno = new Aluno("RA", "Joao Da Silva");

        //6.6 media
        aluno.addNotaAtividadePratica(5.0);
        aluno.addNotaAtividadePratica(10.0);
        aluno.addNotaAtividadePratica(7.0);
        aluno.addNotaAtividadePratica(0.0);
        aluno.addNotaAtividadePratica(1.0);
        double mediaRAA = (5.0 + 10.0 + 7.0 + 0.0 + 1.0) / 5.0;

        aluno.setNotaProjeto(5.0);
        aluno.setNotaRAA(6.6);

        assertFalse(aluno.ValidarRAA());
    }

    @Test
    public void testNotaFinalComRAAValido() {
        Aluno aluno = new Aluno("RA", "Joao Da Silva");

        //4.6 media
        aluno.addNotaAtividadePratica(5.0);
        aluno.addNotaAtividadePratica(10.0);
        aluno.addNotaAtividadePratica(7.0);
        aluno.addNotaAtividadePratica(0.0);
        aluno.addNotaAtividadePratica(1.0);
        double mediaRAA = (5.0 + 0.0 + 7.0 + 0.0 + 1.0) / 5.0;

        aluno.setNotaProjeto(5.0);
        aluno.setNotaRAA(6.0);

        assertTrue(aluno.ValidarRAA());
    }

    @Test
    public void testNomeInvalido() {
        Aluno aluno = new Aluno("RA", "");       
        assertFalse(aluno.ValidarNome());
    }
    
    @Test
    public void testNomeValido() {
        Aluno aluno = new Aluno("RA", "José da Silva");       
        assertTrue(aluno.ValidarNome());
    }    
}
