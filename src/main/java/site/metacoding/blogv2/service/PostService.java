package site.metacoding.blogv2.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.post.PostRepository;

// 웹브라우저 -> 컨트롤러 -> 서비스 -> 레포지토리 -> 영속성컨텍스트 -> 디비

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IOC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트임.
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void 글삭제하기(Integer id) {
        postRepository.deleteById(id);
    }

    public Page<Post> 글목록보기(Integer page) {
        PageRequest pq = PageRequest.of(page, 3, Sort.by(Direction.DESC, "id"));
        Page<Post> posts = postRepository.findAll(pq);
        return posts;
    }

    public Post 글상세보기(Integer id) {
        Optional<Post> postOp = postRepository.findById(id);
        if (postOp.get() != null) {
            Post postEntity = postOp.get();
            return postEntity;
        } else {
            throw new RuntimeException("게시글을 찾을수 없습니다.");
        }
    }

    // 그냥 안함
    @Transactional
    public Post 글쓰기(Post post) {
        return postRepository.save(post);
    }
}
