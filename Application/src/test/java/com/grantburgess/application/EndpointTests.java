package com.grantburgess.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grantburgess.application.endpoints.task.addTask.AddTaskEndpoint;
import com.grantburgess.application.endpoints.removeTask.CancelOfferEndpoint;
import com.grantburgess.application.endpoints.task.getTaskById.GetOfferByIdEndpoint;
import com.grantburgess.application.endpoints.task.getTasks.GetTasksEndpoint;
import com.grantburgess.application.exception.ErrorMessageMap;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.presenters.task.TaskCreatedOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskCreatedViewModel;
import com.grantburgess.ports.presenters.task.TaskOutputBoundary;
import com.grantburgess.ports.presenters.task.TaskViewModel;
import com.grantburgess.ports.presenters.task.TasksOutputBoundary;
import com.grantburgess.ports.presenters.task.TasksViewModel;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.task.addtask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.task.get.tasks.GetTaskInputBoundary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {AddTaskEndpoint.class, CancelOfferEndpoint.class, GetOfferByIdEndpoint.class, GetTasksEndpoint.class})
@AutoConfigureJsonTesters
@WebAppConfiguration
public class EndpointTests {
    private final String NAME = "name-1";
    private final String DESCRIPTION = "description-1";
    private final BigDecimal AMOUNT = BigDecimal.TEN;
    private final String PRICE = AMOUNT.toPlainString();
    private final String CURRENCY = "GBP";
    private final String START_DATE = "2018-01-01";
    private final String END_DATE = "2018-01-31";
    private final String STATUS = "ACTIVE";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddTaskInputBoundary addOfferInputBoundary;
    @MockBean
    private TaskCreatedOutputBoundary taskCreatedOutputBoundary;
    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private GetTaskByIdInputBoundary getTaskByIdInputBoundary;
    @MockBean
    private TaskOutputBoundary taskOutputBoundary;
    @MockBean
    private GetTaskInputBoundary getOfferInputBoundary;
    @MockBean
    private TasksOutputBoundary offersOutputBoundary;
    @MockBean
    private Clock clock;

    private TaskViewModel buildOfferViewModel(String id, String name, String description, String price, String currency, String startDate, String endDate, String status) {
        return TaskViewModel
                .builder()
                .id(id)
                .name(name)
                .description(description)
                .duration(
                        TaskViewModel.Duration
                                .builder()
                                .startDate(startDate)
                                .endDate(endDate)
                                .build()
                )
                .build();
    }

    private byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() {
        when(clock.now()).thenReturn(LocalDate.of(2018, 01, 15));
    }

    @Test
    public void can_create_offer() throws Exception {
        NewOfferRequest request = new NewOfferRequest(
                NAME,
                DESCRIPTION,
                START_DATE,
                END_DATE,
                CURRENCY,
                AMOUNT
        );
        String id = UUID.randomUUID().toString();
        when(taskCreatedOutputBoundary.getViewModel()).thenReturn(new TaskCreatedViewModel(id));
        mockMvc.perform(
                post(URI.create("/api/v1/offers"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(convertObjectToJsonBytes(request))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(equalTo(id))));
    }

    @Test
    public void can_get_offer_by_id() throws Exception {
        String id = UUID.randomUUID().toString();
        TaskViewModel taskViewModel = buildOfferViewModel(id, NAME, DESCRIPTION, PRICE, CURRENCY, START_DATE, END_DATE, STATUS);
        doNothing().when(getTaskByIdInputBoundary).execute(any());
        when(taskOutputBoundary.getViewModel()).thenReturn(taskViewModel);
        mockMvc.perform(
                get("/api/v1/offers/{offerId}", id)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(id))))
                .andExpect(jsonPath("$.name", is(equalTo(NAME))))
                .andExpect(jsonPath("$.description", is(equalTo(DESCRIPTION))))
                .andExpect(jsonPath("$.price", is(equalTo(PRICE))))
                .andExpect(jsonPath("$.currency", is(equalTo(CURRENCY))))
                .andExpect(jsonPath("$.duration.startDate", is(equalTo(START_DATE))))
                .andExpect(jsonPath("$.duration.endDate", is(equalTo(END_DATE))))
                .andExpect(jsonPath("$.status", is(equalTo(STATUS))));
    }

    @Test
    public void cannot_get_offer_by_id_when_offer_does_not_exist() throws Exception {
        doThrow(TaskGateway.OfferNotFoundException.class).when(getTaskByIdInputBoundary).execute(any());
        mockMvc.perform(
                get("/api/v1/offers/{offerId}", UUID.randomUUID())
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.[0]", is(equalTo(ErrorMessageMap.errors.get(TaskGateway.OfferNotFoundException.class)))));
    }

    @Test
    public void can_get_offers() throws Exception {
        String id = UUID.randomUUID().toString();
        TaskViewModel taskViewModel = buildOfferViewModel(id, NAME, DESCRIPTION, PRICE, CURRENCY, START_DATE, END_DATE, STATUS);
        String id2 = UUID.randomUUID().toString();

        TaskViewModel taskViewModel2 = buildOfferViewModel(id2, "name-2", "", BigDecimal.ONE.setScale(2).toPlainString(), CURRENCY, "2018-01-05", "2018-01-31", "EXPIRED");
        when(offersOutputBoundary.getViewModel()).thenReturn(TasksViewModel.builder().addOfferViewModel(taskViewModel).addOfferViewModel(taskViewModel2).build());
        mockMvc.perform(
                get("/api/v1/offers")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.offers", hasSize(2)))

                .andExpect(jsonPath("$.offers.[0].id", is(equalTo(id))))
                .andExpect(jsonPath("$.offers.[0].name", is(equalTo(NAME))))
                .andExpect(jsonPath("$.offers.[0].description", is(equalTo(DESCRIPTION))))
                .andExpect(jsonPath("$.offers.[0].price", is(equalTo(PRICE))))
                .andExpect(jsonPath("$.offers.[0].currency", is(equalTo(CURRENCY))))
                .andExpect(jsonPath("$.offers.[0].duration.startDate", is(equalTo(START_DATE))))
                .andExpect(jsonPath("$.offers.[0].duration.endDate", is(equalTo(END_DATE))))
                .andExpect(jsonPath("$.offers.[0].status", is(equalTo(STATUS))))

                .andExpect(jsonPath("$.offers.[1].id", is(equalTo(id2))))
                .andExpect(jsonPath("$.offers.[1].name", is(equalTo("name-2"))))
                .andExpect(jsonPath("$.offers.[1].description", is(isEmptyString())))
                .andExpect(jsonPath("$.offers.[1].price", is(equalTo("1.00"))))
                .andExpect(jsonPath("$.offers.[1].currency", is(equalTo("GBP"))))
                .andExpect(jsonPath("$.offers.[1].duration.startDate", is(equalTo("2018-01-05"))))
                .andExpect(jsonPath("$.offers.[1].duration.endDate", is(equalTo("2018-01-31"))))
                .andExpect(jsonPath("$.offers.[1].status", is(equalTo("EXPIRED"))));
    }

    @Test
    public void can_cancel_offer() throws Exception {
        String offerId = UUID.randomUUID().toString();
        mockMvc.perform(
                post("/api/v1/offers/{offerId}/cancel", offerId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(status().isNoContent());
    }

    private class NewOfferRequest {
        private String name;
        private String description;
        private Duration duration;
        private String currency;
        private BigDecimal price;

        public NewOfferRequest(String name, String description, String startDate, String endDate, String currency, BigDecimal price) {
            this.name = name;
            this.description = description;
            this.duration = new Duration(startDate, endDate);
            this.currency = currency;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Duration getDuration() {
            return duration;
        }

        public String getCurrency() {
            return currency;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public class Duration {
            private String startDate;
            private String endDate;

            public Duration(String startDate, String endDate) {
                this.startDate = startDate;
                this.endDate = endDate;
            }

            public String getStartDate() {
                return startDate;
            }

            public String getEndDate() {
                return endDate;
            }
        }
    }

}
