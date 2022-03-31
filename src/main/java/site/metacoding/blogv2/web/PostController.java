package site.metacoding.blogv2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "post/detail";
    }

    // 페이지를 줘
    @GetMapping("/s/post/writeForm")
    public String wrtieForm() {
        return "post/writeForm";
    }

    @GetMapping({ "/", "/post" })
    public String home() {
        return "post/list";
    }

}
