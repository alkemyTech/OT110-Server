package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActivityServiceImpl implements IActivityService {


    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        activity.setName(activityRequest.getName());
        activity.setContent(activityRequest.getContent());
        activity.setImage(activityRequest.getImage());
        activity.setDateCreation(LocalDateTime.now());
        activity.setDateUpdate((LocalDateTime.now()));
        Activity createActivity = activityRepository.save(activity);
        return generateActivity(createActivity);
    }

    private ActivityResponse generateActivity(Activity request){
        ActivityResponse activity = new ActivityResponse();
        activity.setName(request.getName());
        activity.setContent(request.getContent());
        activity.setImage(request.getImage());
        activity.setDateCreation(request.getDateCreation().toString());
        activity.setDateUpdate(request.getDateUpdate().toString());
        return activity;
    }
}
