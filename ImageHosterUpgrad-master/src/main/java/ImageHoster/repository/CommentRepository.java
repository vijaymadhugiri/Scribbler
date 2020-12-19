package ImageHoster.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;

import org.springframework.stereotype.Repository;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {
	
	//Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
	@PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

	//The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
	public Comment createComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }
	
	//The method creates an instance of EntityManager
    //Executes JPQL query to fetch the comments from the database with corresponding image
    //Returns the comments in case the comments are found in the database
    //Returns null if no comments are found in the database
	public List<Comment> getCommentsByImage(Image image) {
        EntityManager em = emf.createEntityManager();
        try {
        	TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.image =:image", Comment.class).setParameter("image", image);
        	List<Comment> resultList = typedQuery.getResultList();
            return resultList;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
