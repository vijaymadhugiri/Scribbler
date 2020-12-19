package ImageHoster.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	 
	@Autowired
    private ImageService imageService;
	
	//This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives all the details of the comments to be stored in the database, and now the comments will be sent to the business logic to be persisted in the database
    //After storing the comments, this method directs to the logged in user homepage displaying all the images - showImage() page
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@RequestParam("comment") String comment, @PathVariable(name = "imageId") Integer imageId, @PathVariable(name = "imageTitle") String imageTitle, Model model, HttpSession session) throws IOException {
    	User user = (User) session.getAttribute("loggeduser");
    	Image image = imageService.getImage(imageId);
    	
    	Comment newComment = new Comment();
    	newComment.setText(comment);
    	newComment.setUser(user);
    	newComment.setImage(image);
    	newComment.setCreatedDate(LocalDate.now());
    	List<Comment> comments = new ArrayList<Comment>();
    	
    	comments.add(newComment);
    	image.setComments(comments);
    	
    	commentService.createComment(newComment);
    	
    	model.addAttribute("image", image);
        //Addition of new comments associated with a particular image into the model
        model.addAttribute("comments", image.getComments());
        
        return "redirect:/images/" + image.getId() + "/" +image.getTitle();
    }
}
