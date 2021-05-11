package com.board.board.post.mapper;

import com.board.board.post.domain.Post;
import com.board.board.post.dto.PostTitleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "categoryName", source = "category.name")
    PostTitleResponseDto entityToTitleDto(Post post);
}
