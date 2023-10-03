package br.com.guzzmega;

import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mock; // retorna o valor default
import org.mockito.Spy;  // executa o método de fato

@ExtendWith(MockitoExtension.class)
public class CalculatorMockTest {

    @Spy
    private Calculator calc;

    @Spy
    private UserRepository repo;

    @Test
    public void test(){
        Mockito.when(calc.sum(Mockito.anyInt(),Mockito.eq(2)))
                        .thenReturn(5)
                        .thenReturn(6)
                        .thenCallRealMethod();
        Mockito.when(repo.save(Mockito.any()))
                        .thenReturn(UserBuilder.getOneUser().build());
                        //.thenCallRealMethod(); deve lançar erro

        System.out.println(calc.sum(1,2));
        System.out.println(calc.sum(1,2));
        System.out.println(calc.sum(10,2));
        System.out.println(calc.sum(14,2));
        System.out.println(calc.sum(167,2));

        System.out.println(repo.save(null));
        System.out.println(repo.save(UserBuilder.getOneUser().withName("Billy").build()));
    }
}
