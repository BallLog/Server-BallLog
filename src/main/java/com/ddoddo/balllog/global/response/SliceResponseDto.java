package com.ddoddo.balllog.global.response;

import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class SliceResponseDto<T> {

    protected List<T> content;
    protected boolean first;
    protected boolean last;

    private SliceResponseDto(Slice<T> slice) {
        this.content = slice.getContent();
        this.first = slice.isFirst();
        this.last = slice.isLast();
    }

    public static <T> SliceResponseDto<T> from(Slice<T> slice) {
        return new SliceResponseDto<>(slice);
    }
}
