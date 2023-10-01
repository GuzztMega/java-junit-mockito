package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Transaction;
import br.com.guzzmega.financial.domain.builders.TransactionBuilder;
import br.com.guzzmega.financial.repository.TransactionDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//@EnabledIf(value = "isValidTime")
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionDAO transactionDAO;
    @InjectMocks
    TransactionService transactionService;

    /*
    @BeforeEach
    public void checkTime(){
        Assumptions.assumeTrue(LocalDateTime.now().getHour() > 5);
    }
    */

    @Test
    public void mustSaveValidTransaction(){
        Transaction savingTransaction = TransactionBuilder.getOneTransaction().withId(null).build();
        Mockito.when(transactionDAO.save(savingTransaction)).thenReturn(TransactionBuilder.getOneTransaction().build());

        LocalDateTime allowedDate = LocalDateTime.of(2023,10,10,16,30,59);
        System.out.println(allowedDate);

        try(MockedStatic<LocalDateTime> dateTime = Mockito.mockStatic(LocalDateTime.class)){
            dateTime.when(LocalDateTime::now).thenReturn(allowedDate);
            System.out.println(LocalDateTime.now());

            Transaction savedTransaction = transactionService.save(savingTransaction);
            assertEquals(TransactionBuilder.getOneTransaction().build(), savedTransaction);
            assertAll("Transaction",
                    () -> assertEquals(1L, savedTransaction.getId()),
                    () -> assertEquals("Bill Payment NF-e 123456", savedTransaction.getDescription()),
                    () -> {
                        assertAll("Account",
                                () -> assertEquals("account-abc", savedTransaction.getAccount().getName()),
                                () -> {
                                    assertAll("User",
                                            () -> assertEquals("João", savedTransaction.getAccount().getUser().getName()),
                                            () -> assertEquals("joao123", savedTransaction.getAccount().getUser().getPassword()));
                                }
                        );
                    }
            );
            dateTime.verify(LocalDateTime::now, Mockito.times(2));
        }
        System.out.println(LocalDateTime.now());
    }
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
