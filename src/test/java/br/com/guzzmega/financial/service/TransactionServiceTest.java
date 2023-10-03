package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Transaction;
import br.com.guzzmega.financial.domain.builders.TransactionBuilder;
import br.com.guzzmega.financial.repository.TransactionDAO;
import br.com.guzzmega.financial.service.external.ClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//@EnabledIf(value = "isValidTime")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionServiceTest {

    @Mock
    ClockService clockService;
    @Mock
    TransactionDAO transactionDAO;
    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    public void setup(){
        /* Assumptions.assumeTrue(LocalDateTime.now().getHour() > 5); */
        Mockito.when(clockService.getCurrentTime()).thenReturn(LocalDateTime.of(2023,10,10,16,30,59));
    }

    @Test
    public void mustSaveValidTransaction(){
        Transaction savingTransaction = TransactionBuilder.getOneTransaction().withId(null).build();
        Mockito.when(transactionDAO.save(savingTransaction)).thenReturn(TransactionBuilder.getOneTransaction().build());

        Transaction savedTransaction = transactionService.save(savingTransaction);
        assertEquals(TransactionBuilder.getOneTransaction().build(), savedTransaction);
        assertAll("Transaction",
                () -> assertEquals(1L, savedTransaction.getId()),
                () -> assertEquals("Bill Payment NF-e 123456", savedTransaction.getDescription()),
                () -> { assertAll("Account",
                        () -> assertEquals("account-abc", savedTransaction.getAccount().getName()),
                        () -> { assertAll("User",
                                () -> assertEquals("João", savedTransaction.getAccount().getUser().getName()),
                                () -> assertEquals("joao123", savedTransaction.getAccount().getUser().getPassword()));
                });
        });
    }

    @Test
    public void mustRejectWorthlessTransaction() throws NoSuchMethodException, IllegalAccessException {
        Transaction transaction = TransactionBuilder.getOneTransaction().withValue(null).build();

        Method method = TransactionService.class.getDeclaredMethod("validateAttributes", Transaction.class);
        method.setAccessible(true);

        Exception exception = assertThrows(Exception.class, () -> method.invoke(new TransactionService(), transaction));
        assertEquals("Value is mandatory.", exception.getCause().getMessage());
    }

    /*
    // developed method using mock constructor technique
    @Test
    public void mustSaveValidTransaction(){
        Transaction savingTransaction = TransactionBuilder.getOneTransaction().withId(null).build();
        Mockito.when(transactionDAO.save(savingTransaction)).thenReturn(TransactionBuilder.getOneTransaction().build());

        try(MockedConstruction<Date> date = Mockito.mockConstruction(
                Date.class, (mock, context) -> {
                    Mockito.when(mock.getHours()).thenReturn(4);
                })){

            Transaction savedTransaction = transactionService.save(savingTransaction);
            assertEquals(TransactionBuilder.getOneTransaction().build(), savedTransaction);
            assertAll("Transaction",
                    () -> assertEquals(1L, savedTransaction.getId()),
                    () -> assertEquals("Bill Payment NF-e 123456", savedTransaction.getDescription()),
                    () -> { assertAll("Account",
                            () -> assertEquals("account-abc", savedTransaction.getAccount().getName()),
                            () -> { assertAll("User",
                                    () -> assertEquals("João", savedTransaction.getAccount().getUser().getName()),
                                    () -> assertEquals("joao123", savedTransaction.getAccount().getUser().getPassword()));
                    });
            });
            assertEquals(2, date.constructed().size());
        }
    }
    */

    /*
    public static boolean isValidTime(){
        return LocalDateTime.now().getHour() > 5;
    }
     */

    /* Está gerando o erro: java.lang.NoSuchMethodError: 'boolean org.junit.platform.commons.util.CollectionUtils.isConvertibleToStream(java.lang.Class)'
    @ParameterizedTest(name = "{6}")
    @MethodSource(value = "buildScenarios")
    public void mustValidateAttributeOnSave(Long id, String description, Double value, LocalDate date, Account account, Boolean status, String message){
        String exceptionMessage = Assertions.assertThrows(ValidationException.class, () -> {
            Transaction transaction = TransactionBuilder.getOneTransaction()
                    .withId(id)
                    .withDescription(description)
                    .withValue(value)
                    .withDate(date)
                    .withAccount(account)
                    .withStatus(status).build();
            transactionService.save(transaction);
        }).getMessage();
        assertEquals(message, exceptionMessage);
    }

    static Stream<Arguments> buildScenarios(){
        return Stream.of(
                Arguments.of(1L, null, 10D, LocalDate.now(), AccountBuilder.getOneAccount().build(), true, "Description is mandatory."),
                Arguments.of(1L, "Bill Payment NF-e 123456", null, LocalDate.now(), AccountBuilder.getOneAccount().build(), true, "Date is mandatory."),
                Arguments.of(1L, "Bill Payment NF-e 123456", 10D, null, AccountBuilder.getOneAccount().build(), true, "Value is mandatory."),
                Arguments.of(1L, "Bill Payment NF-e 123456", 10D, LocalDate.now(), null, true, "Account is mandatory."),
                Arguments.of(1L, "Bill Payment NF-e 123456", 10D, LocalDate.now(), AccountBuilder.getOneAccount().build(), null, "Status is mandatory.")
        );
    }
    */
}
