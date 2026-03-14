package practica.turismo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.turismo.entity.Comment;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.CommentRepository;
import practica.turismo.repository.PhotoRepository;
import practica.turismo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Comment createComment(Comment comment) {
        if (!photoRepository.existsById(comment.getPhoto().getId())) {
            throw new ApiException("Foto no encontrada.");
        }
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByPhotoId(Long photoId) {
        return commentRepository.findByPhotoId(photoId);
    }
}
