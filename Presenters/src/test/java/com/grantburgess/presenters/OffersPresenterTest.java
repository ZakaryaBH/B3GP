package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.task.TasksViewModel;
import com.grantburgess.ports.usescases.task.get.TaskResponse;
import com.grantburgess.ports.usescases.task.get.tasks.TasksResponse;
import com.grantburgess.presenters.task.TasksPresenter;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

public class OffersPresenterTest {
    private static final String DESCRIPTION = "description";
    private static final LocalDate START_DATE = LocalDate.of(2018, 01, 01);
    private static final LocalDate END_DATE = LocalDate.of(2018, 01, 31);
    private static final String CURRENCY = "GBP";
    private static final BigDecimal AMOUNT = BigDecimal.TEN;

    private void assertOfferViewModel(TasksViewModel viewModel, String id, String name, String description, String startDate, String endDate) {
        assertThat(viewModel.getTasks(),
                hasItem(both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("name", equalTo(name)))
                        .and(hasProperty("description", equalTo(description)))
                        .and(hasProperty("duration", hasProperty("startDate", equalTo(startDate))))
                        .and(hasProperty("duration", hasProperty("endDate", equalTo(endDate))))
                )
        );
    }

    @Test
    public void can_present_offers() {
        // GIVEN
        TasksPresenter presenter = new TasksPresenter();
        UUID offerId1 = UUID.randomUUID();
        String offerName1 = "name-1";
        UUID offerId2 = UUID.randomUUID();
        String offerName2 = "name-2";
        TasksResponse responseModel = TasksResponse
                .builder()
                .addTask(new TaskResponse(offerId1,offerName1, DESCRIPTION, START_DATE, END_DATE))
                .addTask(new TaskResponse(offerId2, offerName2, DESCRIPTION, START_DATE.plusDays(1), END_DATE))
                .build();
        // WHEN
        presenter.present(responseModel);

        // THEN
        TasksViewModel viewModel = presenter.getViewModel();
        assertThat(viewModel, is(not(nullValue())));
        assertThat(viewModel.getTasks(), hasSize(2));
        assertOfferViewModel(viewModel, offerId1.toString(), offerName1, DESCRIPTION, "2018-01-01", "2018-01-31");
        assertOfferViewModel(viewModel, offerId2.toString(), offerName2, "", "2018-01-02", "2018-01-31");
    }
}