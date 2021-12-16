package com.alkemy.ong.config.seeder;

import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivitiesSeeder implements CommandLineRunner {

    private final ActivityRepository activityRepository;
    private static final String IMAGE = "https://somosmas.jpg";

    @Override
    public void run(String... args) throws Exception {
        loadActivities();
    }

    private void loadActivities() {
        if (activityRepository.count() == 0) {
            loadActivitiesSeed();
        }
    }

    private void loadActivitiesSeed(){
        activityRepository.save(buildActivity("Family Support", "Support"));
        activityRepository.save(buildActivity("School Support", "Support"));
        activityRepository.save(buildActivity("Carpentery's Workshop", "Workshop"));
        activityRepository.save(buildActivity("Clothes's Collect", "Collect"));
        activityRepository.save(buildActivity("Art's Workshop", "Workshop"));
        activityRepository.save(buildActivity("Musical's Workshop", "Workshop"));
        activityRepository.save(buildActivity("Dance's Workshop", "Workshop"));
        activityRepository.save(buildActivity("Sport's Encounter", "Encounter"));
        activityRepository.save(buildActivity("Food's Encounter", "Encount"));
        activityRepository.save(buildActivity("Family's Workshop", "Workshop"));
    }

    private Activity buildActivity(String name, String content) {
        Activity activity = new Activity();
        activity.setName(name);
        activity.setContent(content);
        activity.setImage(IMAGE);
        return activity;
    }
}
