package com.seoultech.sanEseo.like;

public record GetLikeResponse(
    Long postId,
    int likeCnt
) { }
