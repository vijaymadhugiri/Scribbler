package ImageHoster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.ImageRepository;

@Service
public class CommentService {
	@Autowired
    private CommentRepository commentRepository;
	
	@Autowired
    private ImageRepository imageRepository;

	//Call the getCommentsByImageId(Integer imageId) method in the Repository and obtain a List of all the comments in the database that are associated with the given image.
	public List<Comment> getCommentsByImageId(Integer imageId) {
		Image image = imageRepository.getImage(imageId);
		return commentRepository.getCommentsByImage(image);
	}
	
	//The method calls the createComment(Comment comment) method in the Repository and passes the comment to be persisted in the database
    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }
}
