package com.grantburgess.ports.usescases.get.tasks;

import com.grantburgess.database.inmemory.InMemoryDatabase;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.get.tasks.GetTasksRequest;
import com.grantburgess.usecases.task.get.tasks.GetTasks;
import com.grantburgess.usecases.testdoubles.ClockStub;
import com.grantburgess.usecases.testdoubles.TasksPresenterSpy;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;

public class GetFeaturesTest {
    private static final String OFFER_DESCRIPTION = "description";
    private static final LocalDate OFFER_START_DATE = LocalDate.of(2018, 01, 01);
    private static final LocalDate OFFER_END_DATE = LocalDate.of(2018, 01, 31);
    private static final LocalDate CURRENT_DATE = LocalDate.of(2018, 01, 30);

    private Database database;
    private GetTasks useCase;
    private TasksPresenterSpy presenterSpy;

    private void assertOffer(
            UUID id,
            String name,
            LocalDate endDate) {
    }
    private void assertOfferPresented(int offerSize) {
        assertThat(presenterSpy.getResponseModel(), is(not(nullValue())));
        assertThat(presenterSpy.isOffersPresented(), is(true));
    }

    private UUID addOffer(String name, LocalDate endDate) {
        return database.taskGateway().add(new Task(name, GetFeaturesTest.OFFER_DESCRIPTION, GetFeaturesTest.OFFER_START_DATE, endDate));
    }

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        Clock clock = new ClockStub(CURRENT_DATE);
        presenterSpy = new TasksPresenterSpy();
        useCase = new GetTasks(presenterSpy, database.taskGateway(), clock);
    }

    @Test
    public void no_offers_returns_empty_response_list() {
        // WHEN
        useCase.execute(new GetTasksRequest());
        // THEN
        assertOfferPresented(0);
    }

    @Test
    public void can_get_offers() {
        // GIVEN
        UUID offer1Id = addOffer("offer-1", OFFER_END_DATE);
        UUID offer2Id = addOffer("offer-2", OFFER_END_DATE);
        // WHEN
        useCase.execute(new GetTasksRequest());
        // THEN
        assertOfferPresented(2);
        assertOffer(offer1Id, "offer-1", OFFER_END_DATE);
        assertOffer(offer2Id, "offer-2", OFFER_END_DATE);
    }

    @Test
    public void expired_offers_have_the_status_expired() {
        // GIVEN
        LocalDate expiredOfferEndDate = OFFER_END_DATE.minusDays(2);
        UUID offer1Id = addOffer("offer-1", OFFER_END_DATE);
        UUID offer2Id = addOffer("offer-2", expiredOfferEndDate);
        // WHEN
        useCase.execute(new GetTasksRequest());
        // THEN
        assertOfferPresented(2);
        assertOffer(offer1Id, "offer-1", OFFER_END_DATE);
        assertOffer(offer2Id, "offer-2", expiredOfferEndDate);
    }

    @Test
    public void cancelled_offers_do_not_appear_in_list() {
        // GIVEN
        UUID offer1Id = addOffer("offer-1", OFFER_END_DATE);
        UUID offer2Id = addOffer("offer-2", OFFER_END_DATE);
        Task offer2 = database.taskGateway().getByIdExcludingCancelled(offer2Id);
        database.taskGateway().update(offer2);
        // WHEN
        useCase.execute(new GetTasksRequest());
        // THEN
        assertOfferPresented(1);
        assertOffer(offer1Id, "offer-1", OFFER_END_DATE);
    }
}