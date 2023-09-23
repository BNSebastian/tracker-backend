//package com.bsebastian.tracker.security.user;
//
//import com.bsebastian.tracker.app.entities.Activity.model.Activity;
//import com.bsebastian.tracker.app.entities.Activity.repository.ActivityRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class UserEntityServiceImpl implements UserEntityService{
//
//    private final UserRepository userRepository;
//    private final ActivityRepository activityRepository;
//    public UserEntityServiceImpl(UserRepository repository, ActivityRepository activityRepository) {
//        this.userRepository = repository;
//        this.activityRepository = activityRepository;
//    }
//
//    @Override
//    public UserEntity addActivity(Long userId, Long activityId) {
//        UserEntity user = userRepository.findById(userId.intValue()).orElseThrow();
//        List<Activity> activityList = user.getActivities();
//        activityList.add(activityRepository.findById(activityId).orElseThrow());
//        user.setActivities(activityList);
//        userRepository.save(user);
//        return user;
//    }
//
//    @Override
//    public void delete(Long id) {
//        userRepository.deleteById(id.intValue());
//    }
//}
