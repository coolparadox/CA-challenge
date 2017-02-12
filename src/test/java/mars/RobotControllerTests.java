/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mars;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RobotControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRobotShouldReturnInitialPosition() throws Exception {
        this.mockMvc
			.perform(get("/rest/mars"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 0, N)"));
    }

    @Test
    public void postRobotShouldReturnInitialPosition() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 0, N)"));
    }

    @Test
    public void getRobotCommandShouldReturnMethodNotAllowed() throws Exception {
        this.mockMvc
			.perform(get("/rest/mars/M"))
			.andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testMoveWithRightRotation() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MMRMMRMM"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(2, 0, S)"));
    }

    @Test
    public void testMoveWithLeftRotation() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MML"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 2, W)"));
    }

    @Test
    public void testMoveWithLeftRotationRepeated() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MML"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 2, W)"));
    }

    @Test
    public void testInvalidCommand() throws Exception {
        this.mockMvc
			.perform(get("/rest/mars/AAA"))
			.andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testInvalidPosition() throws Exception {
        this.mockMvc
			.perform(get("/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM"))
			.andExpect(status().isMethodNotAllowed());
    }

}
