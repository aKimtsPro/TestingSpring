package be.akimts.test.testingspring.integration;

import be.akimts.test.testingspring.api.model.form.TaskForm;
import be.akimts.test.testingspring.domain.entities.Task;
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


    @Autowired // pas le choix
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        String json = "{\"status\":\"TODO\",\"title\":\"title\",\"description\":\"description\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("taskId", "1")
//                        .header("Authorization", "Bearer ...")
                        .content(json)
        )
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("$.id").exists() )
                .andExpect( MockMvcResultMatchers.jsonPath("$.title").value("title") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.description").value("description") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.status").value(TaskStatus.TODO.toString()) );
    }

}
