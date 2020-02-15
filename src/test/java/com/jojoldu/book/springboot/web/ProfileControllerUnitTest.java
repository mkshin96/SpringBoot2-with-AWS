package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다() {
        //given
        String expectedProfile = "real";
        MockEnvironment mockEnv = new MockEnvironment();
        mockEnv.addActiveProfile(expectedProfile);
        mockEnv.addActiveProfile("oauth");
        mockEnv.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(mockEnv);

        //when
        String profile = profileController.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없으면_첫번째가_조회된다() {
        String expectedProfile = "oauth";
        MockEnvironment mockEnv = new MockEnvironment();
        mockEnv.addActiveProfile(expectedProfile);
        mockEnv.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(mockEnv);

        String profile = profileController.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() {
        String expectedProfile = "default";
        MockEnvironment mockEnv = new MockEnvironment();

        ProfileController profileController = new ProfileController(mockEnv);

        String profile = profileController.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }
}