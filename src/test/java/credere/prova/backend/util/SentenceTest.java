package credere.prova.backend.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static credere.prova.backend.util.Sentence.format;

public class SentenceTest {

    @Test
    public void umItem() {
        // given
        var listaComUmItemApenas = List.of("word1");

        // when
        String resultado = format(listaComUmItemApenas);

        // then
        Assert.assertEquals("word1", resultado);
    }

    @Test
    public void doisItems() {
        // given
        var listaComDoisItem = List.of("word1", "word2");

        // when
        String resultado = format(listaComDoisItem);

        // then
        Assert.assertEquals("word1 e word2", resultado);
    }

    @Test
    public void tresItems() {
        // given
        var listaComTresItem = List.of("word1", "word2", "word3");

        // when
        String resultado = format(listaComTresItem);

        // then
        Assert.assertEquals("word1, word2 e word3", resultado);
    }

    @Test
    public void nenhumItem() {
        // given
        var listaComNenhumItemApenas = new ArrayList<String>();

        // when
        String resultado = format(listaComNenhumItemApenas);

        // then
        Assert.assertEquals("", resultado);
    }

}
