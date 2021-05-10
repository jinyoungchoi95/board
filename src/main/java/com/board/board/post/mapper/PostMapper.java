package com.board.board.post.mapper;

import com.board.board.post.domain.Post;
import com.board.board.post.dto.PostTitleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    PostTitleResponseDto entityToTitleDto(Post post);
    List<PostTitleResponseDto> entityToTitleDto(List<Post> post);
}
