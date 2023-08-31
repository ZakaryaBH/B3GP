package com.grantburgess.ports.usescases.addtask;

import com.grantburgess.database.inmemory.InMemoryDatabase;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.usescases.task.addtask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.task.addtask.AddTaskRequest;
import com.grantburgess.usecases.task.addtask.AddTask;
import com.grantburgess.usecases.testdoubles.ClockStub;
import com.grantburgess.usecases.testdoubles.TaskCreatedPresenterSpy;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

public class AddTaskTest {
    private static final String OFFER_REQUEST_NAME = "name";
    private static final String OFFER_REQUEST_DESCRIPTION = "description";
    private static final LocalDate OFFER_REQUEST_START_DATE = LocalDate.of(2018, 1, 1);
    private static final LocalDate OFFER_REQUEST_END_DATE = LocalDate.of(2018, 1, 31);
    private static final LocalDate CURRENT_DATE = OFFER_REQUEST_START_DATE.plusDays(14);

    private Database database;
    private AddTaskInputBoundary useCase;
    private AddTaskRequest request;
    private TaskCreatedOutputBoundary presenter;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        presenter = new TaskCreatedPresenterSpy();
        useCase = new AddTask(presenter, database.taskGateway(), new ClockStub(CURRENT_DATE));
        request = new AddTaskRequest(OFFER_REQUEST_NAME, OFFER_REQUEST_DESCRIPTION, OFFER_REQUEST_START_DATE, OFFER_REQUEST_END_DATE);
    }

    @Test(expected = TaskGateway.OfferNameAlreadyExistsException.class)
    public void cannot_add_offer_with_same_name() {
        // GIVEN
        database.taskGateway().add(
                new Task(
                        OFFER_REQUEST_NAME,
                        OFFER_REQUEST_DESCRIPTION,
                        OFFER_REQUEST_START_DATE,
                        OFFER_REQUEST_END_DATE
                )
        );
        // WHEN
        useCase.execute(request);
    }

    @Test(expected = TaskGateway.OfferStartDateGreaterThanEndDateException.class)
    public void offer_start_date_cannot_be_later_than_end_date() {
        // GIVEN
        LocalDate startDate = LocalDate.of(2018, 1, 2);
        LocalDate endDateEarlierThanStartDate = startDate.minusDays(1);
        // WHEN
        useCase.execute(new AddTaskRequest(OFFER_REQUEST_NAME, OFFER_REQUEST_DESCRIPTION, startDate, endDateEarlierThanStartDate));
    }

    @Test(expected = TaskGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException.class)
    public void cannot_add_offer_with_an_end_date_that_is_before_the_current_date() {
        // GIVEN
        LocalDate endDateEarlierThanCurrentDate = CURRENT_DATE.minusDays(1);
        // WHEN
        useCase.execute(new AddTaskRequest(OFFER_REQUEST_NAME, OFFER_REQUEST_DESCRIPTION, OFFER_REQUEST_START_DATE, endDateEarlierThanCurrentDate));
    }

    @Test
    public void can_add_offer() {
        Pattern uuidPattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
        TaskCreatedPresenterSpy presenterSpy = (TaskCreatedPresenterSpy) presenter;
        // WHEN
        useCase.execute(request);
        // THEN
        assertThat(presenterSpy.isOfferPresented(), equalTo(true));
        UUID offerId = presenterSpy.getResponseModel().getId();
        assertThat(uuidPattern.matcher(offerId.toString()).matches(), equalTo(true));
        Collection<Task> offers = database.taskGateway().getAllExcludingCancelled();
        assertThat(offers, hasSize(1));
        assertThat(offers,
                hasItem(both(hasProperty("id", equalTo(offerId)))
                        .and(hasProperty("name", equalTo(OFFER_REQUEST_NAME)))
                        .and(hasProperty("description", equalTo(OFFER_REQUEST_DESCRIPTION)))
                        .and(hasProperty("startDate", equalTo(OFFER_REQUEST_START_DATE)))
                        .and(hasProperty("endDate", equalTo(OFFER_REQUEST_END_DATE)))
                )
        );
    }
}