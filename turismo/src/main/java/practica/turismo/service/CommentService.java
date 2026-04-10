package practica.turismo.service;

import java.util.List;
import practica.turismo.entity.Comment;

public interface CommentService {
    Comment createComment(Comment comment);
    List<Comment> findByPhotoId(Long photoId);
}
