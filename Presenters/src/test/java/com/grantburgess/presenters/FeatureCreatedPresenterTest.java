package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.task.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.task.addtask.NewTaskResponse;
import com.grantburgess.presenters.task.TaskCreatedPresenter;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeatureCreatedPresenterTest {
    @Test
    public void can_present_created_offer() {
        TaskCreatedPresenter presenter = new TaskCreatedPresenter();
        UUID id = UUID.randomUUID();
        // WHEN
        presenter.present(new NewTaskResponse(id));
        // THEN
        TaskCreatedViewModel viewModel = presenter.getViewModel();
        assertThat(viewModel, is(not(nullValue())));
        assertThat(id.toString(), equalTo(viewModel.getId()));
    }
}