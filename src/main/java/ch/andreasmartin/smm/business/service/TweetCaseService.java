package ch.andreasmartin.smm.business.service;

import ch.andreasmartin.smm.data.api.PersonRepository;
import ch.andreasmartin.smm.data.api.ProjectRepository;
import ch.andreasmartin.smm.data.api.TweetRepository;
import ch.andreasmartin.smm.data.domain.Person;
import ch.andreasmartin.smm.data.domain.Project;
import ch.andreasmartin.smm.data.domain.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by andreas.martin on 17.04.2017.
 */
@Service
@Transactional
public class TweetCaseService {
    private final TweetRepository tweetRepository;
    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TweetCaseService(TweetRepository tweetRepository, PersonRepository personRepository, ProjectRepository projectRepository) {
        this.tweetRepository = tweetRepository;
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }

    public Tweet saveTweetCase(String tweetText, Date tweetDate, String content, String personName, String email, String projectName)
    {
        List<Person> personList = personRepository.findByEmail(email);
        Person person = null;
        if(personList.isEmpty())
        {
            person = new Person();
            person.setEmail(email);
            person.setPersonName(personName);
            person = personRepository.save(person);
        }
        else
        {
            person = personList.get(0);
        }
        List<Project> projectList = projectRepository.findByProjectName(projectName);
        Project project = null;
        if(projectList.isEmpty())
        {
            project = new Project();
            project.setProjectName(projectName);
            project = projectRepository.save(project);
        }
        else
        {
            project = projectList.get(0);
        }
        Tweet tweet = new Tweet();
        tweet.setTweetText(tweetText);
        tweet.setTweetDate(tweetDate);
        tweet.setEmailContent(content);
        tweet.setAuthor(person);
        tweet.setProject(project);
        tweet = tweetRepository.save(tweet);
        return tweet;
    }

    public List<Tweet> findAllOrBy(String projectName){
        if(projectName==null)
            return tweetRepository.findAll();
        return tweetRepository.findTweetsByProjectProjectName(projectName);
    }

    public Tweet read(Long id){
        return tweetRepository.findOne(id);
    }

    public Tweet assignReviewer(String reviewerName, Long tweetId)
    {
        List<Person> personList = personRepository.findByPersonName(reviewerName);
        Person person = null;
        if(personList.isEmpty())
        {
            person = new Person();
            person.setPersonName(reviewerName);
            person = personRepository.save(person);
        }
        else
        {
            person = personList.get(0);
        }
        Tweet tweet = tweetRepository.findOne(tweetId);
        tweet.setReviewer(person);
        tweet = tweetRepository.save(tweet);
        return tweet;
    }

    public Tweet saveTweetCase(Tweet tweet){
        tweet = tweetRepository.save(tweet);
        return tweet;
    }
}
