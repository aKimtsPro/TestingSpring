package be.akimts.test.testingspring.integration;

import be.akimts.test.testingspring.api.model.form.TaskForm;
import be.akimts.test.testingspring.domain.entities.TaskStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void create() throws Exception { // L'idée c'est d'automatiser ce qu'on fait dans postman
        TaskForm form = new TaskForm();

        form.setTitle("title");
        form.setDescription("desc");
        form.setStatus(TaskStatus.TODO);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(form))
        )
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("$.id").value(1L) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.title").value(form.getTitle()) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.description").value(form.getDescription()) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.status").value(form.getStatus().toString()) );
    }
}
