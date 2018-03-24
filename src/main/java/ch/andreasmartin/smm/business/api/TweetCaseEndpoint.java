package ch.andreasmartin.smm.business.api;

import ch.andreasmartin.smm.business.dto.ReviewRequest;
import ch.andreasmartin.smm.business.dto.ReviewResponse;
import ch.andreasmartin.smm.business.dto.TweetCaseData;
import ch.andreasmartin.smm.business.service.TweetCaseService;
import ch.andreasmartin.smm.data.domain.Tweet;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreas.martin on 17.04.2017.
 */
@Component
@Path("/api/smm/v1/tweetcase")
@Api
public class TweetCaseEndpoint {
    @Autowired
    private TweetCaseService tweetCaseService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TweetCaseData> findAllOrBy(@QueryParam("projectName") String projectName){
        List<Tweet> tweets = tweetCaseService.findAllOrBy(projectName);
        if(!tweets.isEmpty()){
            List<TweetCaseData> tweetCaseData = new ArrayList<TweetCaseData>();
            for(Tweet tweet:tweets)
            {
                tweetCaseData.add(new TweetCaseData(tweet));
            }
            return tweetCaseData;
        }
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TweetCaseData tweetCaseData, @Context UriInfo uriInfo) {
        TweetCaseData tweetCaseResponse = new TweetCaseData(tweetCaseService.saveTweetCase(tweetCaseData.getTweetText(), tweetCaseData.getTweetDate(), tweetCaseData.getContent(), tweetCaseData.getPersonName(), tweetCaseData.getEmail(), tweetCaseData.getProjectName()));
        return Response.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(tweetCaseResponse.getTweetCaseId())).build()).entity(tweetCaseResponse).build();
    }

    @GET
    @Path("/{tweetCaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TweetCaseData read(@QueryParam("tweetCaseId") Long tweetId){
        return new TweetCaseData(tweetCaseService.read(tweetId));
    }

    @POST
    @Path("/{tweetCaseId}/review")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReviewResponse createReview(@PathParam("tweetCaseId") Long tweetId, ReviewRequest reviewRequest){
        Tweet tweet = tweetCaseService.assignReviewer(reviewRequest.getReviewerName(),tweetId);
        tweet.setReview(reviewRequest.getReview());
        tweet.setApproved(reviewRequest.isApproved());
        tweet = tweetCaseService.saveTweetCase(tweet);
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setReviewerId(tweet.getReviewer().getId());
        return reviewResponse;
    }

    @GET
    @Path("/{tweetCaseId}/review")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReviewResponse readReview(@PathParam("tweetCaseId") Long tweetId){
        return new ReviewResponse(tweetCaseService.read(tweetId));
    }

}
