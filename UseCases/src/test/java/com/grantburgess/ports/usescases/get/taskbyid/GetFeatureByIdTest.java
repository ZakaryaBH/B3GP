package com.grantburgess.ports.usescases.get.taskbyid;

import com.grantburgess.database.inmemory.InMemoryDatabase;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskRequest;
import com.grantburgess.usecases.task.get.taskbyid.GetTaskById;
import com.grantburgess.usecases.testdoubles.ClockStub;
import com.grantburgess.usecases.testdoubles.TaskPresenterSpy;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

public class GetFeatureByIdTest {
    private static final String OFFER_NAME = "offer-1";
    private static final String OFFER_DESCRIPTION = "description";
    private static final LocalDate OFFER_START_DATE = LocalDate.of(2018, 01, 01);
    private static final LocalDate OFFER_END_DATE = LocalDate.of(2018, 01, 31);
    private static final BigDecimal OFFER_PRICE_AMOUNT = BigDecimal.TEN;
    private static final String OFFER_PRICE_CURRENCY = "GBP";
    private static final LocalDate CURRENT_DATE = LocalDate.of(2018, 01, 30);

    private Database database;
    private GetTaskById useCase;
    private TaskPresenterSpy presenterSpy;

    private void assertOffer(
            UUID id,
            LocalDate endDate) {
        assertThat(presenterSpy.getResponseModel(),
                both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("name", equalTo(GetFeatureByIdTest.OFFER_NAME)))
                        .and(hasProperty("description", equalTo(GetFeatureByIdTest.OFFER_DESCRIPTION)))
                        .and(hasProperty("startDate", equalTo(GetFeatureByIdTest.OFFER_START_DATE)))
                        .and(hasProperty("endDate", equalTo(endDate)))
                        .and(hasProperty("currency", equalTo(GetFeatureByIdTest.OFFER_PRICE_CURRENCY)))
                        .and(hasProperty("amount", is(closeTo(GetFeatureByIdTest.OFFER_PRICE_AMOUNT, BigDecimal.ZERO))))
        );
        assertThat(presenterSpy.isOfferPresented(), is(true));
    }

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        Clock clock = new ClockStub(CURRENT_DATE);
        presenterSpy = new TaskPresenterSpy();
        useCase = new GetTaskById(presenterSpy, database.taskGateway(), clock);
    }

    @Test(expected = TaskGateway.OfferNotFoundException.class)
    public void cannot_get_offer_that_does_not_exist() {
        useCase.execute(GetTaskRequest.builder().id(UUID.randomUUID()).build());
    }

    @Test
    public void can_get_offer_by_id() {
        // GIVEN
        UUID offerId = database.taskGateway().add(new Task(OFFER_NAME, OFFER_DESCRIPTION, OFFER_START_DATE, OFFER_END_DATE));

        // WHEN
        useCase.execute(GetTaskRequest.builder().id(offerId).build());

        // THEN
        assertThat(presenterSpy.getResponseModel(), is(not(nullValue())));
        assertOffer(offerId, OFFER_END_DATE);
    }
}