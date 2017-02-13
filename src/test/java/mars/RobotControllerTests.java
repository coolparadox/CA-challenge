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
			.andExpect(content().string("(0, 0, N)\n"));
    }

    @Test
    public void postRobotShouldReturnInitialPosition() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 0, N)\n"));
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
			.andExpect(content().string("(2, 0, S)\n"));
    }

    @Test
    public void testMoveWithLeftRotation() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MML"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 2, W)\n"));
    }

    @Test
    public void testMoveWithLeftRotationRepeated() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MML"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 2, W)\n"));
    }

    @Test
    public void testInvalidCommand() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/AAA"))
			.andExpect(status().isBadRequest());
    }

    @Test
    public void testInvalidPosition() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM"))
			.andExpect(status().isBadRequest());
    }

    @Test
    public void testCrossNorthBoundary() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MMMMM"))
			.andExpect(status().isBadRequest());
    }

    @Test
    public void testCrossWestBoundary() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/RMMMMM"))
			.andExpect(status().isBadRequest());
    }

    @Test
    public void testCrossEastBoundary() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/LM"))
			.andExpect(status().isBadRequest());
    }

    @Test
    public void testCrossSouthBoundary() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/LLM"))
			.andExpect(status().isBadRequest());
    }

    @Test
    public void test360RightTurn() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/RRRR"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 0, N)\n"));
    }

    @Test
    public void test360LeftTurn() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/LLLL"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 0, N)\n"));
    }

    @Test
    public void testReachNorthEastByDiagonal() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MRMLMRMLMRMLMRM"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(4, 4, E)\n"));
    }

    @Test
    public void testFrontierWalk() throws Exception {
        this.mockMvc
			.perform(post("/rest/mars/MMMMRMMMMRMMMMRMMMM"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
			.andExpect(content().string("(0, 0, W)\n"));
    }

}
