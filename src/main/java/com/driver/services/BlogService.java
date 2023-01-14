package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;
    @Autowired
    private ImageRepository imageRepository;

    public List<Blog> showBlogs(){
        //find all blogs
       return blogRepository1.findAll();

    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        Blog blog = new Blog(title,content,new Date(),userRepository1.findById(userId).get());
blogRepository1.save(blog);
        //updating the blog details

        //Updating the userInformation and changing its blogs

    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Image image = new Image(description,dimensions);
        List<Image> list = blogRepository1.getOne(blogId).getImageList();
        list.add(image);
        blogRepository1.getOne(blogId).setImageList(list);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        List<Image> list = blogRepository1.getOne(blogId).getImageList();
        for(Image image:list){
            imageRepository.delete(image);
        }
        blogRepository1.deleteById(blogId);
    }
}
